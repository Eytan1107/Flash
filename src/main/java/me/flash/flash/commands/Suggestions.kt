package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
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
        sender.sendMessage("Here is the suggestions list :".prefix())
        Flash.async.execute{
            Flash.suggestionsdb.prepareStatement("select * from suggestion").executeQuery().let { result ->
                var on = 0
                while (result.next()) {
                    var suggestion = result.getString("text")
                    if (suggestion.length > 50) suggestion = suggestion.substring(0, 50) + "..."
                    sender.sendMessage("&e${++on}. &9${Bukkit.getOfflinePlayer(UUID.fromString(result.getString("uuid"))).name} - &e${suggestion}".color())
                }
            }
        }
        return true
    }
}