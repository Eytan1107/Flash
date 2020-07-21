package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class KKick : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("kick.noob")) {
            if (args.isEmpty()) {
                sender.sendMessage("${ChatColor.RED}You do not have permission to use this command.")
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player == null) {
                    sender.sendMessage("${ChatColor.RED}Please specify a player to kick")
                    return true
                } else {
                    var reason = ""
                    if (args.size > 1) {
                        reason = args.joinToString { " " }.replaceFirst(args.first() + " ", "")
                    }
                    if (reason.isEmpty()) {
                        player.kickPlayer("No reason was provided\nKicked by: ${sender.name}")
                    } else {
                        player.kickPlayer(reason + "\nKicked by: ${sender.name}")
                    }
                }
            }
        }
        return true
    }

}
