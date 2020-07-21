package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.formatMessage
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Feed : CommandExecutor {
    private val permission = "skript.feed"
    private val permissionOther = "skript.feed.others"
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) {
                sender.sendMessage(ChatColor.RED.toString() + "You must be a player to use this command on yourself.")
                return true
            }
            if (!sender.hasPermission(permission)) {
                sender.sendMessage(Flash.noPermissionMessage)
            } else {
                sender.sendMessage(formatMessage("You have been fed"))
                sender.foodLevel = Int.MAX_VALUE
            }
        } else {
            if (sender.hasPermission(permissionOther)) { //but im still not pro lol
                val target = Bukkit.getPlayer(args[0])
                if (target == null) {
                    sender.sendMessage(ChatColor.RED.toString() + "The target player was not found.")
                } else {
                    sender.sendMessage(formatMessage("You have fed &c" + target.name))
                    target.sendMessage(formatMessage("&c" + sender.name + "&6 fed you"))
                    target.foodLevel = Int.MAX_VALUE
                }
            } else {
                sender.sendMessage(Flash.noPermissionMessage)
            }
        }
        return true
    }
}