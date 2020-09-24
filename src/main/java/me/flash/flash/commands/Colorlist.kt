package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Colorlist : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.colorslist")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        sender.sendMessage("Here is a list of all the colors available in minecraft:".prefix())
        sender.sendMessage("&1&&11&r, &2&&22&r, &3&&33&r, &4&&44&r, &5&&55&r ,&6&&66&r ,&7&&77&r ,&8&&88&r ,&9&&99&r ,&0&&00&r ,&d&&dd&r ,&e&&ee&r ,&f&&ff&r ,&&fk&kg&r ,&l&&ll&r, &m&&mm&r, &n&&nn&r, &o&&oo&r, &&rr (reset)".color())
        return true
    }
}
