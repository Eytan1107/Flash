package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Sudo : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.sudo")) {
            sender.sendMessage(FlashUtil.noPermission)
            return true
        }
        if (args.size < 2) {
            sender.sendMessage("&cUsage : /sudo <user> <text>".prefix())
            return true
        }
        val player = Bukkit.getPlayer(args[0])
        if (!sender.hasPermission("*")) if (player == Bukkit.getPlayer("DarrenSanders") || player == Bukkit.getPlayer("JGamingz")) sender.sendMessage("You cannot sudo this player").run { return true }
        if (player == Bukkit.getPlayer("FastAs_Flash")) sender.sendMessage(noPermission).run { return true }
        if (player == sender) sender.sendMessage("You cannot sudo yourself.".error()).let { return true }
        if (player == null) {
            sender.sendMessage(FlashUtil.targetOffline)
            return true
        }
        val message = args.toMutableList().apply { removeAt(0) }.joinToString(" ")
        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Sudo'ing &c${player.name} &6to say \"&c${message}&6\"".prefix()) else sender.sendMessage("Sudo'ing &l${player.name} &6to say \"&l${message}&6\"".prefix())
        player.chat(args.toMutableList().apply {
            removeAt(0)
        }.joinToString(" "))
        FlashUtil.staffMessage(sender, "Sudo'd &l${player.name}")
        return true
    }
}

