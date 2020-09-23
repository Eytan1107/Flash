package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.vaultChat
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Msg : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val senderprefix = if (sender is Player) vaultChat.getPlayerPrefix(sender) else ""
        if (args.isEmpty()) sender.sendMessage("You need to set a player !".error()).let { return true }
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).let { return true }
        if (player == sender) sender.sendMessage("You can't send a message to yourself !".error()).let { return true }
        if (args.size == 1) sender.sendMessage("You need to enter a message !".error()).let { return true }
        val prefix = vaultChat.getPlayerPrefix(player)
        sender.sendMessage("&7(&aTo $prefix${player.name}&7)&a ".color() + args.toMutableList().apply { removeAt(0) }.joinToString (" "))
        player.sendMessage("&7(&aFrom &4$senderprefix${sender.name}&7)&a ".color() + args.toMutableList().apply { removeAt(0) }.joinToString (" "))
        lastMessaged[sender] = player
        lastMessaged[player] = sender
        return true
    }
    companion object{
        val lastMessaged = mutableMapOf<CommandSender, CommandSender>()
    }
}