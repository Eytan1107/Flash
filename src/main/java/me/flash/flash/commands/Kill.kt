package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Kill : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.kill")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (args.isEmpty()) {
            sender.sendMessage("&cUsage: /kill <player>".prefix())
            return true
        }
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
        player.health = 0.0
        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Killed &c${player.name}".prefix()) else sender.sendMessage("Killed &l${player.name}")
        Flash.staffMessage(sender, "Killed &l${player.name}")
        return true
    }
}
