package me.flash.flash.commands

import me.flash.flash.Flash.Companion.vaultChat
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Msg : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val senderprefix = if (sender is Player) vaultChat.getPlayerPrefix(sender) else ""
        val sendersuffix = if (sender is Player) vaultChat.getPlayerSuffix(sender) else ""
        if (args.isEmpty()) sender.sendMessage("You need to set a player!".error()).let { return true }
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(FlashUtil.targetOffline).let { return true }
        if (player == sender) sender.sendMessage("You can't send a message to yourself!".error()).let { return true }
        if (args.size == 1) sender.sendMessage("You need to enter a message!".error()).let { return true }
        val prefix = vaultChat.getPlayerPrefix(player)
        val suffix = vaultChat.getPlayerSuffix(player)
        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("&a(To $prefix${player.name}&a) ".color() + args.toMutableList().apply { removeAt(0) }.joinToString (" ").color()) else sender.sendMessage("&bTo $prefix${player.name}&8:$suffix ".color() + args.toMutableList().apply { removeAt(0) }.joinToString (" ").color())
        if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&a(From $senderprefix${sender.name}&a) ".color() + args.toMutableList().apply { removeAt(0) }.joinToString (" ").color()) else player.sendMessage("&bFrom $senderprefix${sender.name}&8:$sendersuffix ".color() + args.toMutableList().apply { removeAt(0) }.joinToString (" ").color())
        lastMessaged[sender] = player
        lastMessaged[player] = sender
        return true
    }
    companion object{
        val lastMessaged = mutableMapOf<CommandSender, CommandSender>()
    }
}