package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class Fly : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender.hasPermission("flash.fly")) {
                val player = Bukkit.getPlayer(sender.name) ?: sender.sendMessage(targetOffline).let { return true }
                if (player.allowFlight) {
                    player.allowFlight = false
                    sender.sendMessage("You turned &loff &6flight".prefix())
                    Flash.staffMessage(sender, "Disabled flight for &l${player.name}")
                } else {
                    player.allowFlight = true
                    sender.sendMessage("You turned &con&r &6flight".prefix())
                    Flash.staffMessage(sender, "Enabled flight for &l${player.name}")
                }

            } else sender.sendMessage(noPermission).let { return true }
        }
        if (args.size == 1) {
            if (sender.hasPermission("flash.fly.others")) {
                val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
                if (player.allowFlight) {
                    player.allowFlight = false
                    if (player !== sender) {
                        sender.sendMessage("You turned flight &loff &6for &l${player.name}".prefix())
                        player.sendMessage("&c${sender.name}&r &6Turned &loff &6your flight".prefix())
                    } else {
                        sender.sendMessage("You turned &loff &6flight".prefix())
                    }
                    Flash.staffMessage(sender, "Disabled flight for &l${player.name}")
                } else {
                    player.allowFlight = true
                    if (player !== sender) {
                        sender.sendMessage("You turned &lon &6flight".prefix())
                        player.sendMessage("&l${sender.name} &6Turned &lon &6your flight".prefix())
                    } else {
                        sender.sendMessage("You turned &lon &6flight".prefix())
                    }
                    Flash.staffMessage(sender, "enabled flight for &l${player.name}")
                }

            } else sender.sendMessage(noPermission).let { return true }
        }
        return true
    }
}