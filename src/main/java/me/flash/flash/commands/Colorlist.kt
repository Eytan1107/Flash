package me.flash.flash.commands

import me.flash.flash.Flash
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
        sender.sendMessage("&1&&1&r, &2&&2&r, &3&&3&r, &4&&4&r, &5&&5&r ,&6&&6&r ,&7&&7&r ,&8&&8&r ,&9&&9&r ,&0&&0&r ,&d&&d&r ,&e&&e&r ,&f&&f&r ,&&k:&ka&r ,&l&&l&r, &m&&m&r, &n&&n&r, &o&&o&r, &&r (reset)".prefix())
        return true
    }
}
