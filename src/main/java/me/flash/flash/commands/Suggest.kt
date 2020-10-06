package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.notPlayer
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Suggest : CommandExecutor {
    /*
    companion object {
        var suggestions = emptyList<Suggestion>().toMutableList()
    }*/

    //unused...?

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(notPlayer)
            return true
        }
        if (args.isEmpty()) {
            sender.sendMessage("You did not provide a suggestion".error())
            return true
        }
        Bukkit.getScheduler().runTaskAsynchronously(Flash.instance) {
            Flash.suggestionsdb.prepareStatement("insert into suggestion values (?, ?)").apply {
                setString(1, sender.uniqueId.toString())
                setString(2, args.joinToString(" "))
            }.executeUpdate()
            sender.sendMessage("Suggestion submitted. Thank you for your suggestion !".prefix())
        }
        return true
    }
}