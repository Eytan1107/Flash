package me.flash.flash.commands

import me.flash.flash.Flash
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
                sender.hasPermission("core.tp.others") -> sender.sendMessage("/tp <player> [player]".prefix())
                sender.hasPermission("core.tp") -> sender.sendMessage("/tp <player>".prefix())
                else -> sender.sendMessage(noPermission)
            }
        } else if (args.size == 1) {
            if (sender !is Player) sender.sendMessage(notPlayer).let { return true }
            if (!sender.hasPermission("core.tp")) sender.sendMessage(noPermission).let { return true }
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
            if (player == sender) sender.sendMessage("You cannot teleport to yourself.".prefix()).let { return true }
            sender.teleport(player)
            player.sendMessage("${sender.name} teleported to you.".prefix())
            sender.sendMessage("You teleported to ${player.name}".prefix())
            if (command.name != "ksilentteleport") Flash.staffMessage(sender.name, "teleported to ${player.name}")
        } else {
            if (!sender.hasPermission("core.tp.others")) sender.sendMessage(noPermission).let { return true }
            val from = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
            val to = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(targetOffline).let { return true }
            if (from == to) sender.sendMessage("You cannot teleport the same player to themself".prefix())
            from.teleport(to)
            if (command.name != "ksilentteleport") from.sendMessage("You were teleported to ${to.name} by ${sender.name}".prefix())
            if (command.name != "ksilentteleport") to.sendMessage("${from.name} was teleported to you by ${sender.name}".prefix())
            if (command.name != "ksilentteleport") Flash.staffMessage(sender.name, "teleported ${from.name} to ${to.name}")
        }
        return true
    }
}
