package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.usage
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

class Suggestions : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Flash.notPlayer)
            return true
        }
        if (args.isEmpty()) {
            sender.sendMessage("Here is the suggestions list :".prefix())
            Bukkit.getScheduler().runTaskAsynchronously(Flash.instance) {
                Flash.suggestionsdb.prepareStatement("select * from suggestion").executeQuery().let { result ->
                    var on = 0
                    while (result.next()) {
                        var suggestion = result.getString("text")
                        if (suggestion.length > 50) suggestion = suggestion.substring(0, 50) + "..."
                        sender.sendMessage("&e${++on}. &9${Bukkit.getOfflinePlayer(UUID.fromString(result.getString("uuid"))).name} - &e${suggestion}".color())
                    }
                }
            }
        } else {
            if (args.size > 2) sender.sendMessage("Too many arguments".error()).run { return true }
            else {
                if (args[0] == "clear") {
                    if (args[1].isEmpty()) sender.sendMessage("You need to insert a number".error()).run { return true }
                    if (args[1].toIntOrNull() is Int) {
                        val args1 = args.toMutableList().apply { removeAt(0) }.joinToString (" ")
                        if (args[1] !== "1") sender.sendMessage("Suggestion number $args1 doesn't exist.".error()).run { return true } //change 1 by "if args1 exists as a suggestion, like suggestion number 1)
                        val player = Bukkit.getPlayer("FastAs_Flash")// replace to the player that suggested
                        sender.sendMessage("Suggestion &c$args1 &6by &c${player.name} has been deleted.".prefix())
                    }
                    else sender.sendMessage("You need to insert a number.".error()).run { return true }
                }
                else sender.sendMessage("suggestions [clear] [number]".usage()).run { return true }
            }
        }
        return true
    }
}