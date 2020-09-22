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
                sender.hasPermission("flash.tp.others") -> sender.sendMessage("/tp <player> [player]".prefix())
                sender.hasPermission("flash.tp") -> sender.sendMessage("/tp <player> [s]".prefix())
                else -> sender.sendMessage(noPermission)
            }
        } else if (args.size == 1) {
            if (sender !is Player) sender.sendMessage(notPlayer).let { return true }
            if (!sender.hasPermission("flash.tp")) sender.sendMessage(noPermission).let { return true }
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
            if (player == sender) sender.sendMessage("You cannot teleport to yourself.".error()).let { return true }
            sender.teleport(player)
            if (command.name != "stp") player.sendMessage("&c${sender.name}&r &6teleported to you.".prefix())
            sender.sendMessage("Teleporting you to &c${player.name}&6...".prefix())
            Flash.staffMessage(sender, "teleported to ${player.name}", player)
        } else {
            if (!sender.hasPermission("flash.tp.others")) sender.sendMessage(noPermission).let { return true }
            val from = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
            val to = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(targetOffline).let { return true }
            if (from == to) sender.sendMessage("You cannot teleport the same player to themself".error()).let { return true }
            if (from == sender) {
                sender.sendMessage("Teleporting you to &c${to.name}&6...".prefix())
                to.sendMessage("&c${sender.name}&r &6teleported to you.".prefix())
                Flash.staffMessage(sender, "teleported to ${to.name}", to)
                from.teleport(to)
                return true
            } else if (to == sender) {
                if (from == Bukkit.getPlayer("FastAs_Flash")) sender.sendMessage("You cannot teleport Flash to you... You would need to ask him.".error()).let { return true }
                from.sendMessage("&c${sender.name}&r &6teleported you to them".prefix())
                sender.sendMessage("Teleporting &c${from.name}&r &6to you...".prefix())
                Flash.staffMessage(sender, "teleported ${from.name} to them.")
                from.teleport(to)
                return true
            } else {
                from.sendMessage("You were teleported to &c${to.name}&r &6by &c${sender.name}&r".prefix())
                to.sendMessage("&c${from.name}&r &6was teleported to you by &c${sender.name}&6".prefix())
                sender.sendMessage("Teleporting &c${from.name}&r &6to &c${to.name}&6...".prefix())
                from.teleport(to)
                Flash.staffMessage(sender, "teleported ${from.name} to ${to.name}", to, from)
                return true
            }
        }
        return true
    }
}
