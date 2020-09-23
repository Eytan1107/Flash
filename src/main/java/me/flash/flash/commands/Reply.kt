package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Reply : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = Msg.lastMessaged[sender].let {
            if (it !is Player) sender.sendMessage("You cannot reply to the console".error()).run { return true }
            else it
        }
        val senderPrefix = if (sender is Player) Flash.vaultChat.getPlayerPrefix(sender) else "&4".color()
        val playerPrefix = Flash.vaultChat.getPlayerPrefix(player)
        sender.sendMessage("&7(&aTo $playerPrefix${player.name}&7)&a ".color() + args.toMutableList().joinToString (" "))
        player.sendMessage("&7(&aFrom &4$senderPrefix${sender.name}&7)&a ".color() + args.toMutableList().joinToString (" "))
        return true
    }
}