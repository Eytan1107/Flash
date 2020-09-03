package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TpHere : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage("&cYou must be a player".prefix()).let { return true }
        if (args.isEmpty()) sender.sendMessage("&cUsage: /tphere <player>".prefix()).let { return true }
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).let { return true }
        if (player == sender) sender.sendMessage("You cannot teleport yourself to yourself...".error()).let { return true }
        val target = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).let { return true }
        target.teleport(sender)
        target.sendMessage("${sender.name} teleported you to them".prefix())
        sender.sendMessage("You teleported ${target.name} to you".prefix())
        return true
    }
}