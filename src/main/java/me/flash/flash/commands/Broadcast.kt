package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Broadcast : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.broadcast")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (args.isEmpty()){
            sender.sendMessage("/broadcast <text>".error())
        } else {
            Bukkit.broadcastMessage(("&c&l" + args.joinToString(" ")).prefix())
            Flash.staffMessage(sender, "Broadcasted")
        }
        return true
    }
}
