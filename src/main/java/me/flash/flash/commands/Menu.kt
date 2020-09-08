package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.*
import org.bukkit.inventory.Inventory

class Menu : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val inventory = Bukkit.createInventory(null, 27, "server selctor")
        inventory.setItem(0, ItemStack(Material.GOLD_BLOCK, 1))
        val player = Bukkit.getPlayer(sender.name)
        player.openInventory(inventory)
        return true
        }
    }

