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
            sender.sendMessage("Usage: /kill <player>".prefix())
            return true
        }
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
        player.health = 0.0
        sender.sendMessage("Killed ${player.name}")
        return true
    }
}
