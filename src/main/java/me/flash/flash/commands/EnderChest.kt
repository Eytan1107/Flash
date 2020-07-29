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

class EnderChest : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (!sender.hasPermission("core.enderchest")) {
                sender.sendMessage(noPermission)
                return true
            } else {
                if (sender !is Player) {
                    sender.sendMessage(notPlayer)
                    return true
                }
                if (sender.enderChest.viewers.size > 0) {
                    sender.sendMessage("Your enderchest is being viewed, please wait.".prefix())
                }
                sender.openInventory(sender.enderChest)
                sender.sendMessage("You have opened your enderchest".prefix())
            }
        } else {
            if (!sender.hasPermission("core.enderchest.others")) {
                sender.sendMessage(noPermission)
                return true
            }
            if (sender !is Player) {
                sender.sendMessage(notPlayer)
                return true
            }
            val player = Bukkit.getPlayer(args.first())
            if (player == null) {
                sender.sendMessage(targetOffline)
                return true
            }
            if (player.enderChest.viewers.contains(player)) {
                sender.sendMessage("This player has their enderchest open, please wait.".prefix())
                return true
            }
            sender.openInventory(player.enderChest)
            sender.sendMessage("You have opened ${player.enderChest}'s enderchest.".prefix())
        }
        return true
    }
}