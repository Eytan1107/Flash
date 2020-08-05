package me.flash.flash.commands

import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.variables.Suggestion
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.ArrayList

class Suggest : CommandExecutor {
    companion object {
        var suggestions = emptyList<Suggestion>().toMutableList()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (args.isEmpty()) {
                sender.sendMessage("&cYou're not very helpful are you".prefix())
            } else {
                if (sender.hasPermission("skript.staffteam")) {
                    suggestions.add(Suggestion(sender.name, args.joinToString(" "), true))
                } else {
                    suggestions.add(Suggestion(sender.name, args.joinToString(" "), false))
                }
            }
        }
        return true
    }
}