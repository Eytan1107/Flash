package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
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
            if (command.name == "clearchat") Bukkit.broadcastMessage("${sender.name} cleared the chat".prefix())
            else if (command.name == "silentclearchat") Bukkit.broadcastMessage("The chat was cleared".prefix())
        }
        return true
    }
}

