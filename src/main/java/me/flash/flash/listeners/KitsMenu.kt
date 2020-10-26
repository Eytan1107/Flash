package me.flash.flash.listeners

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin

class KitsMenu : Listener {
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
    fun click(event: InventoryClickEvent) {
        val player = event.whoClicked as Player
        if (!JavaPlugin.getPlugin(Flash::class.java).config.getStringList("worlds.kitpvpworld").contains(player.world.name) && tagged.contains(event.clickedInventory)) {
            return
        }
        event.view.close()
        tagged.remove(event.inventory)
        event.isCancelled = true
        when {
            event.currentItem.type == Material.CHAINMAIL_CHESTPLATE && event.currentItem.itemMeta.displayName == "&7Kit PvP".color() -> {
                player.chat("/kit pvp")
            }
            event.currentItem.type == Material.GOLD_CHESTPLATE && event.currentItem.itemMeta.displayName == "&1Kit Fast".color() -> {
                if (player.hasPermission("flash.fast")) player.chat("/kit pvp") else player.sendMessage(noPermission)
            }
            event.currentItem.type == Material.IRON_CHESTPLATE && event.currentItem.itemMeta.displayName == "&eKit Speedster".color() -> {
                if (player.hasPermission("flash.speedster")) player.chat("/kit speedster") else player.sendMessage(noPermission)
            }
            event.currentItem.type == Material.DIAMOND_CHESTPLATE && event.currentItem.itemMeta.displayName == "&b&lKit GodSpeed".color() -> {
                if (player.hasPermission("flash.godspeed")) player.chat("/kit godspeed") else player.sendMessage(noPermission)
            }
            event.currentItem.type == Material.DIAMOND_CHESTPLATE && event.currentItem.itemMeta.displayName == "&6&lKit SpeedForce".color() -> {
                if (player.hasPermission("flash.speedforce")) player.chat("/kit speedforce") else player.sendMessage(noPermission)
            }
        }
    }

    @EventHandler
    fun close(event: InventoryCloseEvent) {
        tagged.remove(event.inventory)
    }

    companion object {
        val tagged = mutableListOf<Inventory>()

        fun genInventory(sender: Player) {
            val inventory = Bukkit.createInventory(null, 27, "&6Kit Menu".color()) // Creates the inventory
            tagged.add(inventory)
            val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply { // The default inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&8[&6Flash's server&8]&r".color()
                }
            }
            for (i in 0..26) {
                inventory.setItem(i, empty) // Sets the inventory slots to the default item if they dont have a used block
            }
            ItemStack(Material.CHAINMAIL_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&7Kit PvP".color()
                    lore = listOf("&5Click to get the PvP kit".color())
                }
                inventory.setItem(9, this)
            }
            ItemStack(Material.GOLD_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&1Kit Fast".color()
                    lore = listOf("&5Click to get the Fast kit".color())
                }
                inventory.setItem(11, this)
            }
            ItemStack(Material.IRON_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&eKit Speedster".color()
                    lore = listOf("&5Click to get the Speedster kit".color())
                }
                inventory.setItem(13, this)
            }
            ItemStack(Material.DIAMOND_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&b&lKit GodSpeed".color()
                    lore = listOf("&5Click to get the GodSpeed kit".color())
                }
                inventory.setItem(15, this)
            }
            ItemStack(Material.DIAMOND_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&6&lKit SpeedForce".color()
                    lore = listOf("&5Click to get the SpeedForce kit".color())
                    addEnchant(Enchantment.DURABILITY, 3, true)
                }
                inventory.setItem(17, this)
            }
            if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("worlds.kitpvpworld").contains(sender.world.name)) { // Checks if the player is in the world KitPvP
                sender.openInventory(inventory) // Opens the inventory if the player is in the world KitPvP
            } else sender.sendMessage("Sorry but you can only use this in KitPvP".error()) // If the player isn't in the world KitPvP send this messager
        }
    }
}