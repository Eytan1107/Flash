package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Hub : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            sender.teleport(Bukkit.getWorld("world").spawnLocation)
            sender.sendMessage("You were teleported to the hub".prefix())
        } else {
            sender.sendMessage(Flash.notPlayer)
        }
        return true
    }
}