package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.notPlayer
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
        if (sender !is Player) {
            sender.sendMessage(notPlayer)
            return true
        }
        if (args.isEmpty()) {
            sender.sendMessage("You did not provide a suggestion".error())
            return true
        }
        sender.sendMessage("Suggestion submitted. Thank you for your suggestion !".prefix())
        Flash.async.execute {
            Flash.suggestionsdb.prepareStatement("insert into suggestion values (?, ?)").apply {
                setString(1, sender.uniqueId.toString())
                setString(2, args.joinToString(" "))
            }.executeUpdate()
        }
        return true
    }
}