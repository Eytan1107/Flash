package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Mutechat : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("skript.mutechat")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        Flash.chatMuted = !Flash.chatMuted
        Flash.staffMessage(sender.name, if (Flash.chatMuted) "Muted the chat" else "Unmuted the chat")
        return true
    }
}
