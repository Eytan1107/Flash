package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
import org.bukkit.entity.Player

class Reply : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = Msg.lastMessaged[sender]?.let {
            if (it is ConsoleCommandSender) sender.sendMessage("You cannot reply to the console".error()).run { return true }
            else if (it !is Player) sender.sendMessage("Pretty interesting that you got a message from an animal, can't reply to it though :)".error()).run { return true }
            else it
        } ?: sender.sendMessage("You have no one to reply to. :(".error()).run { return true }
        val senderPrefix = if (sender is Player) Flash.vaultChat.getPlayerPrefix(sender) else "".color()
        val playerPrefix = Flash.vaultChat.getPlayerPrefix(player)
        val suffix = Flash.vaultChat.getPlayerSuffix(player)
        val sendersuffix = if (sender is Player) Flash.vaultChat.getPlayerSuffix(sender) else ""
        sender.sendMessage("&bTo $playerPrefix${player.name}&8:$suffix ".color() + args.toMutableList().joinToString (" "))
        player.sendMessage("&bFrom $senderPrefix${sender.name}&8:$sendersuffix ".color() + args.toMutableList().joinToString (" "))
        return true
    }
}