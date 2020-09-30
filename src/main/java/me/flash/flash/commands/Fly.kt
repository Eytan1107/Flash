package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.noPermission
import me.flash.flash.FlashUtil.Companion.prefix
import me.flash.flash.FlashUtil.Companion.targetOffline
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
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You turned &coff &6flight".prefix()) else sender.sendMessage("You turned &loff &6flight")
                    FlashUtil.staffMessage(sender, "Disabled flight for &l${player.name}")
                } else {
                    player.allowFlight = true
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You turned &con &6flight".prefix()) else sender.sendMessage("You turned &lon &6flight")
                    FlashUtil.staffMessage(sender, "Enabled flight for &l${player.name}")
                }

            } else sender.sendMessage(noPermission).let { return true }
        }
        if (args.size == 1) {
            if (sender.hasPermission("flash.fly.others")) {
                val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
                if (player.allowFlight) {
                    player.allowFlight = false
                    if (player !== sender) {
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You turned flight &coff &6for &c${player.name}".prefix()) else sender.sendMessage("You turned flight &loff &6for &l${player.name}")
                        if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&c${sender.name} &6Turned &coff &6your flight".prefix()) else player.sendMessage("&l${sender.name} &6Turned &loff &6your flight")
                    } else {
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You turned &coff &6flight".prefix()) else sender.sendMessage("You turned &loff &6flight")
                    }
                    FlashUtil.staffMessage(sender, "Disabled flight for &l${player.name}")
                } else {
                    player.allowFlight = true
                    if (player !== sender) {
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You turned flight &con for &c${player.name}".prefix()) else sender.sendMessage("You turned &lon &6flight")
                        if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&c${sender.name} &6Turned &con &6your flight".prefix()) else player.sendMessage("&l${sender.name} &6Turned &lon &6your flight")
                    } else {
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You turned &con &6flight".prefix()) else sender.sendMessage("You turned &lon &6flight")
                    }
                    FlashUtil.staffMessage(sender, "enabled flight for &l${player.name}")
                }

            } else sender.sendMessage(noPermission).let { return true }
        }
        return true
    }
}