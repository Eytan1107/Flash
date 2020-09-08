package me.flash.flash.commands

import org.bukkit.Bukkit
//import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.*
import org.bukkit.event.Listener

class Menu : CommandExecutor, Listener {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val inventory = Bukkit.createInventory(null, 27, "server selector")
        val dirt = ItemStack(Material.DIRT,1)
        val dirtMeta = dirt.itemMeta
        dirtMeta.lore[0] = "test"
        dirt.itemMeta = dirtMeta

        inventory.setItem(0, dirt)
        val player = Bukkit.getPlayer(sender.name)
        player.openInventory(inventory)

        return true
        }
    }



    


