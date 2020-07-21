package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Pro : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.size == 0) {
            sender.sendMessage(ChatColor.RED.toString() + "Me Pwo")
        } else {
            val player = Bukkit.getPlayer(args[0])
            if (player == null) {
                sender.sendMessage(ChatColor.RED.toString() + "PLAYER NOT ONLINE U NOT PWO")
            } else {
                player.sendMessage(ChatColor.RED.toString() + "You Pwo by " + ChatColor.DARK_RED + sender.name)
                sender.sendMessage(ChatColor.RED.toString() + "Sent a Pwo to " + ChatColor.DARK_RED + player.name)
            }
        } //wanna do skribbl?
        return true
    }
}