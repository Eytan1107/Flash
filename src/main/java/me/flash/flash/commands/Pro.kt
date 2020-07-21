package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Pro : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("${ChatColor.RED}Me Pwo")
        } else {
            val player = Bukkit.getPlayer(args[0])
            if (player == null) {
                sender.sendMessage("${ChatColor.RED}PLAYER NOT ONLINE U NOT org.bukkit.ChatColor")
            } else {
                player.sendMessage("${ChatColor.RED}You Pwo by ${ChatColor.DARK_RED}${sender.name}")
                sender.sendMessage("${ChatColor.RED}Sent a Pwo to ${ChatColor.DARK_RED}${player.name}")
            }
        }
        return true
    }
}