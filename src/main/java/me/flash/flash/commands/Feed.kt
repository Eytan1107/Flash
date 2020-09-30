package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.notPlayer
import me.flash.flash.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Feed : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) {
                sender.sendMessage(notPlayer)
            } else if (!sender.hasPermission("flash.feed")){
                sender.sendMessage(FlashUtil.noPermission)
            } else {
                sender.foodLevel = Int.MAX_VALUE
                sender.sendMessage("You have been fed".prefix())
            }
            FlashUtil.staffMessage(sender, "Fed themself.")
        } else {
            val player = Bukkit.getPlayer(args.first())
            if (sender.hasPermission("flash.feed.others")) {
                if (player == null) {
                    sender.sendMessage(FlashUtil.targetOffline)
                } else if (player == sender) {
                    player.foodLevel = Int.MAX_VALUE
                    sender.sendMessage("You have been fed".prefix())
                } else {
                    player.foodLevel = Int.MAX_VALUE
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You were fed by &c${sender.name}&r".prefix()) else sender.sendMessage("You were fed by &l${sender.name}&r")
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have fed &c${player.name}&r".prefix()) else sender.sendMessage("You have fed &l${player.name}&r")
                }
                FlashUtil.staffMessage(sender, "fed ${player.name}")
            } else {
                sender.sendMessage(FlashUtil.noPermission)
            }
        }
        return true
    }
}