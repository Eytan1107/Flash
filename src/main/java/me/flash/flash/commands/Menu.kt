package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
class Menu : CommandExecutor, Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name)) {
            if (tagged.contains(event.inventory)) {
                event.isCancelled = true
            }
        }
    }

    @EventHandler
    fun close(event: InventoryCloseEvent) {
        if (tagged.contains(event.inventory)) tagged.remove(event.inventory)
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage("Noob").run { return true }
        var sbPlayers = 0
        Flash.instance.config.getStringList("skyblock").forEach {
            sbPlayers += Bukkit.getWorld(it)?.players?.size ?: 0
        }
        val inventory = Bukkit.createInventory(null, 27, "Server selector".color())
        val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply {
            itemMeta = itemMeta.apply {
                displayName = "&8[&7Flash's server&8]&r".color()
            }
        }
        for (i in 0..26) {
            inventory.setItem(i, empty)
        }


        val skyblock = ItemStack(Material.GRASS, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lSkyBlock".color()
                lore = listOf("&7Click to teleport to SkyBlock".color(), "&7Players online: $sbPlayers".color())
            }
            inventory.setItem(0, this)
        }
        val tntrun = ItemStack(Material.TNT, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&4&lTnT Run".color()
                lore = listOf("&7Click to teleport to &4&lTnT Run".color())
            }
            inventory.setItem(8, this)
        }
        val parkour = ItemStack(Material.GOLD_BOOTS, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6&lParkour".color()
                lore = listOf("&7Click to teleport to &6&lParkour".color())
            }
            inventory.setItem(18, this)
        }
        val kitpvp = ItemStack(Material.DIAMOND_SWORD, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6&lKitPvP".color()
                lore = listOf("&7Click to teleport to &9&lKitpvp".color())
            }
            inventory.setItem(13, this)
        }
        val builds = ItemStack(Material.BRICK, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&b&lBuilds".color()
                lore = listOf("&7Click to teleport to builds".color())
            }
            inventory.setItem(22, this)
        }
        val event = ItemStack(Material.REDSTONE, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lEvent".color()
                lore = listOf("&7Click to teleport to &a&lEvent".color())
            }
            inventory.setItem(26, this)
        }
        sender.openInventory(inventory)
        return true

    }
    companion object {
        val tagged = mutableListOf<Inventory>()
    }
}















    


