package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TpHere : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.tphere")) sender.sendMessage(noPermission).run { return true }
        if (sender !is Player) sender.sendMessage("&cYou must be a player".prefix()).let { return true }
        if (args.isEmpty()) sender.sendMessage("&cUsage: /tphere <player>".prefix()).let { return true }
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).let { return true }
        if (player == sender) sender.sendMessage("You cannot teleport yourself to yourself...".error()).let { return true }
        if (player == Bukkit.getPlayer("FastAs_Flash")) sender.sendMessage("You cannot teleport Flash to you... You would need to ask him.".error()).let { return true }
        val target = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).let { return true }
        target.teleport(sender)
        if (!target.hasPermission("flash.msg.nice")) target.sendMessage("&c${sender.name} &6teleported you to them".prefix()) else target.sendMessage("&l${sender.name} &6teleported you to them".prefix())
        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting &c${target.name} &6to you...".prefix()) else sender.sendMessage("Teleporting &l${target.name} &6to you...".prefix())
        Flash.staffMessage(sender, "teleported &l${target.name} &dto them.")
        return true
    }
}