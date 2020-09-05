package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Sudo : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.sudo")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (args.size < 2) {
            sender.sendMessage("&cUsage : /sudo <user> <text>".prefix())
            return true
        }
        val player = Bukkit.getPlayer(args[0])
        if (player==null) {
            sender.sendMessage(Flash.targetOffline)
            return true
        }
        player.chat(args.toMutableList().minus(0).joinToString(" "))
        Flash.staffMessage(player, "Ran the sudo command")
        return true
    }
}
