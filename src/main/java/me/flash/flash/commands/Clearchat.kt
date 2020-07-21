package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Clearchat : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("core.clearchat")) {
            sender.sendMessage(Flash.noPermission)
        } else {
            for (i in 1..255) {
                Bukkit.broadcastMessage(" ")
            }
            if (command.name == "clearchat") Bukkit.broadcastMessage(Flash.formatMessage("${sender.name} cleared the chat"))
            else if (command.name == "silentclearchat") Bukkit.broadcastMessage(Flash.formatMessage("The chat was cleared"))
        }
        return true
    }
}

