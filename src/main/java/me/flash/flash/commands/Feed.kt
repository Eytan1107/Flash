package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.notPlayer
import me.flash.flash.Flash.Companion.prefix
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
                sender.sendMessage(Flash.noPermission)
            } else {
                sender.foodLevel = Int.MAX_VALUE
                sender.sendMessage("You have been fed".prefix())
            }
            Flash.staffMessage(sender, "Fed themself.")
        } else {
            val player = Bukkit.getPlayer(args.first())
            if (sender.hasPermission("flash.feed.others")) {
                if (player == null) {
                    sender.sendMessage(Flash.targetOffline)
                } else if (player == sender) {
                    player.foodLevel = Int.MAX_VALUE
                    sender.sendMessage("You have been fed".prefix())
                } else {
                    player.foodLevel = Int.MAX_VALUE
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You were fed by &c${sender.name}&r".prefix()) else sender.sendMessage("You were fed by &l${sender.name}&r")
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have fed &c${player.name}&r".prefix()) else sender.sendMessage("You have fed &l${player.name}&r")
                }
                Flash.staffMessage(sender, "fed ${player.name}")
            } else {
                sender.sendMessage(Flash.noPermission)
            }
        }
        return true
    }
}