package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.notPlayer
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

// Needs redoing, messy.
class Teleport : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            when {
                sender.hasPermission("flash.tp.others") -> sender.sendMessage("&c/tp <player> [player]".prefix())
                sender.hasPermission("flash.tp") -> sender.sendMessage("&c/tp <player>".prefix())
                else -> sender.sendMessage(noPermission)
            }
        } else if (args.size == 1) {
            if (sender !is Player) sender.sendMessage(notPlayer).let { return true }
            if (!sender.hasPermission("flash.tp")) sender.sendMessage(noPermission).let { return true }
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
            if (player == sender) sender.sendMessage("You cannot teleport to yourself.".error()).let { return true }
            sender.teleport(player)
            if (command.name != "stp") if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&c${sender.name} &6teleported to you.".prefix()) else player.sendMessage("&l${sender.name} &6teleported to you.".prefix())
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting you to &c${player.name}".prefix()) else sender.sendMessage("Teleporting you to &l${player.name}".prefix())
            //sender.sendMessage(.prefix())
            Flash.staffMessage(sender, "teleported to &l${player.name}", player)
        } else {
            if (!sender.hasPermission("flash.tp.others")) sender.sendMessage(noPermission).let { return true }
            val from = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
            val to = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(targetOffline).let { return true }
            if (from == to) sender.sendMessage("You cannot teleport the same player to themself".error()).let { return true }
            if (from == sender) {
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting you to &c${to.name}".prefix()) else sender.sendMessage("Teleporting you to &l${to.name}".prefix())
                if (!to.hasPermission("flash.msg.nice")) to.sendMessage("&c${sender.name} &6teleported to you.".prefix()) else to.sendMessage("&l${sender.name} &6teleported to you.".prefix())
                Flash.staffMessage(sender, "teleported to &l${to.name}", to)
                from.teleport(to)
                return true
            } else if (to == sender) {
                if (!sender.hasPermission("*")) if (from == Bukkit.getPlayer("FastAs_Flash") || from == Bukkit.getPlayer("DarrenSanders") || from == Bukkit.getPlayer("JGamingz")) sender.sendMessage("You cannot teleport this player to you.".error()).let { return true }
                if (!from.hasPermission("flash.msg.nice")) from.sendMessage("&c${sender.name} &6teleported you to them".prefix()) else from.sendMessage("&l${sender.name} &6teleported you to them".prefix())
                if (!to.hasPermission("flash.msg.nice")) to.sendMessage("Teleporting &c${from.name} &6to you".prefix()) else to.sendMessage("Teleporting &l${from.name} &6to you".prefix())
                Flash.staffMessage(sender, "Teleported &l${from.name} &dto them.")
                from.teleport(to)
                return true
            } else {
                if (!sender.hasPermission("*")) if (from == Bukkit.getPlayer("FastAs_Flash") || from == Bukkit.getPlayer("DarrenSanders") || from == Bukkit.getPlayer("JGamingz")) sender.sendMessage("You cannot teleport this player to anyone.".error()).let { return true }
                if (!from.hasPermission("flash.msg.nice")) from.sendMessage("You were teleported to &c${to.name} &6by &c${sender.name}".prefix()) else from.sendMessage("You were teleported to &l${to.name} &6by &l${sender.name}".prefix())
                if (!to.hasPermission("flash.msg.nice")) to.sendMessage("&c${from.name} &6was teleported to you by &c${sender.name}".prefix()) else to.sendMessage("&l${from.name} &6was teleported to you by &l${sender.name}".prefix())
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting &c${from.name} &6to &c${to.name}".prefix()) else sender.sendMessage("Teleporting &l${from.name} &6to &l${to.name}".prefix())
                from.teleport(to)
                Flash.staffMessage(sender, "teleported &l${from.name} &dto &l${to.name}", to, from)
                return true
            }
        }
        return true
    }
}
