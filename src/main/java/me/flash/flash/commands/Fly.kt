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
                    sender.sendMessage("You turned &loff&r &6flying".prefix())
                    Flash.staffMessage("flying turned off for ", player.name)
                } else {
                    player.allowFlight = true
                    sender.sendMessage("You turned &lon&r &6flying".prefix())
                    Flash.staffMessage("Flying turned on for ", player.name)
                }

            } else sender.sendMessage(noPermission).let { return true }
        }
        if (args.size == 1) {
            if (sender.hasPermission("flash.fly.others")) {
                val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
                if (player.allowFlight) {
                    player.allowFlight = false
                    sender.sendMessage("You turned flying &loff&r &6for &l${player.name}".prefix())
                    Flash.staffMessage("flying turned off for ", player.name)
                } else {
                    player.allowFlight = true
                    sender.sendMessage("You turned flying &lon&r &6for &l${player.name}".prefix())
                    Flash.staffMessage("Flying turned on for ", player.name)
                }

            } else sender.sendMessage(noPermission).let { return true }
        }
        return true
    }
}