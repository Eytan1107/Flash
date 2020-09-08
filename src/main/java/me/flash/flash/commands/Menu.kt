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
        val a = ItemStack(Material.DIRT, 1)
        val b = ItemStack(Material.STAINED_GLASS_PANE,1, 0, 14)



        var bMeta = b.itemMeta
        bMeta.displayName = "&6Test".colour()
        b.itemMeta = bMeta
        inventory.setItem(0, b)
        val player = Bukkit.getPlayer(sender.name)
        player.openInventory(inventory)



        return true

        @EventHandler
        fun onInventoryClick(event: InventoryClickEvent) {
            val players = Bukkit.getPlayer(sender.name)
            if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(players.world.name)) {
                if (inventory.viewers.contains(player))
                event.isCancelled = true
            }
        }
    }
}















    


