package me.flash.flash.commands

import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.ChatColor
//import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Item
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.*
import org.bukkit.plugin.java.JavaPlugin


class Menu : CommandExecutor, Listener {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val inventory = Bukkit.createInventory(null, 27, "server selector")
        val dirt = ItemStack(Material.DIRT, 1)
        var dirtMeta = dirt.itemMeta
        dirtMeta.displayName = "&6Test".colour()
        dirt.itemMeta = dirtMeta
        inventory.setItem(0, dirt)
        val player = Bukkit.getPlayer(sender.name)
        player.openInventory(inventory)

        @EventHandler
        fun onInventoryClick(event: InventoryClickEvent) {
            val player = event.whoClicked
            if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name)) {
                if (event.viewers.contains(player))
                    event.isCancelled = true
            }
        }
        return true
    }
}















    


