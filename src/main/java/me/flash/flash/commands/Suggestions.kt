package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Suggestions : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("skript.suggestions")) {
            Suggest.suggestions.forEachIndexed { index, suggestion ->
                sender.sendMessage("${index + 1} - ${suggestion.sender}: ${suggestion.suggestion}")
            }
        } else {
            sender.sendMessage(Flash.noPermission)
        }
        return true
    }
}