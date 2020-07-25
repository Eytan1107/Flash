package me.flash.flash.commands

import me.flash.flash.Flash.Companion.colour
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Kick : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("kick.noob")) {
            if (args.isEmpty()) {
                sender.sendMessage("&cYou do not have permission to use this command.".colour())
                return true
            }
            val player = Bukkit.getPlayer(args.first())
            if (player == null) {
                sender.sendMessage("&cPlease specify a player to kick".colour())
                return true
            }
            var reason = ""
            if (args.size > 1) {
                reason = args.clone().toMutableList().apply { this.removeAt(0) }.joinToString { " " }
            }
            if (reason.isEmpty()) {
                player.kickPlayer("No reason was provided\nKicked by: ${sender.name}")
            } else {
                player.kickPlayer(reason + "\nKicked by: ${sender.name}")
            }

        }
        return true
    }

}
