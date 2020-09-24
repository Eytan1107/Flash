package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
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
        if (player == Bukkit.getPlayer("FastAs_Flash")) sender.sendMessage("You cannot sudo Flash... Nice try.".error()).let { return true }
        if (player == sender) sender.sendMessage("You cannot sudo yourself.".error()).let { return true }
        if (player == null) {
            sender.sendMessage(Flash.targetOffline)
            return true
        }
        val message = args.toMutableList().apply { removeAt(0) }.joinToString (" ")
        sender.sendMessage("Sudo'ing &c${player.name} &6to say \"&c${message}&6\"".prefix())
        player.chat(args.toMutableList().apply {
            removeAt(0)
        }.joinToString (" "))
        Flash.staffMessage(sender, "Sudo'd &l${player.name}")
        return true
    }
}
