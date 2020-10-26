package me.flash.flash.listeners

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryMoveItemEvent
import org.bukkit.event.player.PlayerDropItemEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


class Compass : Listener {
    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if (event.player?.itemInHand?.type == Material.COMPASS && event.player?.itemInHand?.itemMeta?.displayName ?: false == "&6Flash's Server Selector".color()) { // less events?
            if ((event.action.name.toLowerCase().contains("right")) && Flash.instance.config.getStringList("worlds.hub").contains(event.player.world.name)) {
                event.player.chat("/menu")
                return
            }
        }
    }

    @EventHandler
    fun close(event: InventoryCloseEvent) {
        tagged.remove(event.inventory)
    }

    @EventHandler
    fun click(event: InventoryClickEvent) {
        val player = event.whoClicked // Creates a value of the player that clicked in the inventory
        if (!(JavaPlugin.getPlugin(Flash::class.java).config.getStringList("worlds.hub").contains(player.world.name) && tagged.contains(event.clickedInventory))) {
            return
        }

        when {
            event.currentItem.type == Material.GRASS && event.currentItem.itemMeta.displayName == "&a&lSkyBlock".color() -> {
                event.isCancelled = true
                player.sendMessage("Sending you to SkyBlock".prefix())
                player.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
            }
            event.currentItem.type == Material.TNT && event.currentItem.itemMeta.displayName == "&4&lTnTRun".color() -> {
                event.isCancelled = true
                player.sendMessage("Sending you to TnTRun".prefix())
                player.teleport(Bukkit.getWorld("tntrun").spawnLocation)
            }
            event.currentItem.type == Material.DIAMOND_CHESTPLATE && event.currentItem.itemMeta.displayName == "&6&lKitPvP".color() -> {
                event.isCancelled = true
                player.sendMessage("Sending you to KitPvP".prefix())
                player.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
            }
            event.currentItem.type == Material.BRICK && event.currentItem.itemMeta.displayName == "&b&lBuilds".color() -> {
                event.isCancelled = true
                player.sendMessage("Sending you to Builds".prefix())
                player.teleport(Bukkit.getWorld("builds").spawnLocation)
            }
            event.currentItem.type == Material.REDSTONE && event.currentItem.itemMeta.displayName == "&a&lEvent".color() -> {
                event.isCancelled = true
                player.sendMessage("Sending you to Event".prefix())
                player.teleport(Bukkit.getWorld("event").spawnLocation)
            }
            event.currentItem.type == Material.STAINED_GLASS_PANE && event.currentItem.durability == 14.toShort() && event.currentItem.itemMeta.displayName == "&f[&6Flash's server&f]&r".color() -> {
                event.isCancelled = true
            }
            event.currentItem.type == Material.GOLD_BOOTS && event.currentItem.itemMeta.displayName == "&6&lParkour".color() -> {
                event.isCancelled = true
                Bukkit.dispatchCommand(player, "startparkour")
            }
        }
    }

    companion object {
        val tagged = mutableListOf<Inventory>()

        fun genInventory(player: Player): Boolean {
            val inventory = Bukkit.createInventory(null, 27, "&6Server Selector".color())
            tagged.add(inventory) // Enables interaction for the inventory
            val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply { // The default inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&f[&6Flash's server&f]&r".color()
                }
            }
            for (i in 0..26) {
                inventory.setItem(i, empty) // Sets the inventory slots to the default item if they dont have a used block
            }
            ItemStack(Material.GRASS, 1).apply { // Creates the skyblock inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&a&lSkyBlock".color()
                    lore = listOf("&7Click to teleport to &a&lSkyBlock".color(), "&7Players online: &e".color() + (FlashUtil.playersInWorlds("skyblock1") + FlashUtil.playersInWorlds("skyblock2")).size)
                }
                inventory.setItem(0, this) // Sets the item to the right slot
            }
            ItemStack(Material.TNT, 1).apply { // Creates the TntRun inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&4&lTnTRun".color()
                    lore = listOf("&7Click to teleport to &4&lTnTRun".color(), "&7Players online: &e".color() + FlashUtil.playersInWorlds("tntrun").size)
                }
                inventory.setItem(8, this) // Sets the item to the right slot
            }
            ItemStack(Material.GOLD_BOOTS, 1).apply { // Creates the Parkour inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&6&lParkour".color()
                    lore = listOf("&7Click to teleport to &6&lParkour".color())
                }
                inventory.setItem(18, this) // Sets the item to the right slot
            }
            ItemStack(Material.DIAMOND_CHESTPLATE, 1).apply { // Creates the kitpvp inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&6&lKitPvP".color()
                    lore = listOf("&7Click to teleport to &6&lKitPvP".color(), "&7Players online: &e".color() + FlashUtil.playersInWorlds("kitpvp").size)
                }
                inventory.setItem(13, this) // Sets the item to the right slot
            }
            ItemStack(Material.BRICK, 1).apply { // Creates the builds inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&b&lBuilds".color()
                    lore = listOf("&7Click to teleport to &b&lBuilds".color(), "&7Players online: &e".color() + FlashUtil.playersInWorlds("builds").size)
                }
                inventory.setItem(22, this) // Sets the item to the right slot
            }
            ItemStack(Material.REDSTONE, 1).apply { // Creates the event inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&a&lEvent".color()
                    lore = listOf("&7Click to teleport to &a&lEvent".color(), "&7Players online: &e".color() + FlashUtil.playersInWorlds("event").size)
                }
                inventory.setItem(26, this) // Sets the item to the right slot
            }
            player.openInventory(inventory) // Opens the inventory if the player is in the world Hub
            return true
        }
    }

    @EventHandler
    fun playerDropCompassEvent(e: PlayerDropItemEvent) {  // when player drop any item
        val p = e.player
        if (p.itemInHand.type == Material.COMPASS && p.itemInHand.itemMeta?.displayName == "&6Flash's Server Selector".color() || p.itemOnCursor.type == Material.COMPASS && p.itemOnCursor.itemMeta?.displayName == "&6Flash's Server Selector".color()) {
            e.itemDrop.remove()
            p.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&6Flash's Server Selector".color()
                    lore = listOf("&7Click me to open the selector".color())
                }
            })
        }
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.whoClicked.world.name == "world" && event.currentItem.type == Material.COMPASS && event.currentItem.itemMeta.displayName == "&6Flash's Server Selector".color()) {
            event.isCancelled = true
        }
    }
}

