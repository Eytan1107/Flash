package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Hub : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            sender.teleport(Bukkit.getWorld("world").spawnLocation)
            sender.sendMessage("${ChatColor.GREEN}You were teleported to the hub.")
        } else {
            sender.sendMessage("${ChatColor.RED}Only players can use this command")
        }
        return true
    }
}