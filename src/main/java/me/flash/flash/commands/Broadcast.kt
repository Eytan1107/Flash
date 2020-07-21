package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Broadcast : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("core.broadcast")) {
            sender.sendMessage(Flash.noPermission)
        } else if (args.isEmpty()){
            sender.sendMessage(Flash.formatMessage("&cError: /broadcast <text>"))
        } else {
            Bukkit.broadcastMessage(Flash.formatMessage(args.joinToString { " " }))
        }
        return true
    }
}
