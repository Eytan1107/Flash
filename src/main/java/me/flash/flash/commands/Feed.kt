package me.flash.flash.commands

import me.flash.flash.Flash
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
                sender.sendMessage("&cYou must be a player to feed yourself.".prefix())
            } else if (!sender.hasPermission("core.feed.self")){
                sender.sendMessage(Flash.noPermission)
            } else {
                sender.foodLevel = Int.MAX_VALUE
                sender.sendMessage("You have been fed".prefix())
            }
        } else {
            val player = Bukkit.getPlayer(args.first())
            if (player == null) {
                sender.sendMessage(Flash.targetOffline)
            } else {
                player.foodLevel = Int.MAX_VALUE
                player.sendMessage("You were fed by ${sender.name}".prefix())
                sender.sendMessage("You have fed ${player.name}".prefix())
            }
        }
        return true
    }
}