package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameMode : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) sender.sendMessage("&cUsage : /gamemode [gamemode] [player]".color()).run { return true }
            else sender.sendMessage("&cUsage : /gamemode [gamemode] <player>").run { return true }
        } else if (args.size == 1) {
            if (sender !is Player) sender.sendMessage("&cUsage : /gamemode [gamemode] [player]".color()).run { return true }
            if (!sender.hasPermission("flash.gamemode")) sender.sendMessage(FlashUtil.noPermission).run { return true }
            else {
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("".prefix()) else sender.sendMessage("".prefix())
                val gameMode = parseGamemode(args.first()) ?: sender.sendMessage("That is not a valid gamemode!".error()).run { return true }
                sender.gameMode = gameMode
                FlashUtil.staffMessage(sender, "Set their gamemode to &l${gameMode.name.toLowerCase()}&d.")
            }
        } else {
            val gameMode = parseGamemode(args.first()) ?: sender.sendMessage("That is not a valid gamemode!".error()).run { return true }
            val player = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(FlashUtil.targetOffline).run { return true }
            if (!sender.hasPermission("flash.gamemode.others")) sender.sendMessage(noPermission).run { return true }
            player.gameMode = gameMode
            FlashUtil.staffMessage(sender, "Set &l${player.name}&d's gamemode to &l${gameMode.name.toLowerCase()}&d.", player)
            if (!player.hasPermission("flash.msg.nice")) player.sendMessage("Your gamemode was set to &c${gameMode.name.toLowerCase()} &6by &c${sender.name}&6.".prefix()) else player.sendMessage("Your gamemode was set to &l${gameMode.name.toLowerCase()} &6by &l${sender.name}&6.".prefix())
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You set &c${player.name}'s&6 gamemode to &c${gameMode.name.toLowerCase()}&6.".prefix()) else sender.sendMessage("You set &l${player.name}'s&6 gamemode to &l${gameMode.name.toLowerCase()}&6.".prefix())
        }
        return true
    }

    private fun parseGamemode(arg: String) : GameMode? {
        return when (arg) {
            "creative", "c", "1" -> {
                GameMode.CREATIVE
            }
            "survival", "s", "0" -> {
                GameMode.SURVIVAL
            }
            "adventure", "a", "2" -> {
                GameMode.ADVENTURE
            }
            "spectator", "sp", "3" -> {
                GameMode.SPECTATOR
            }
            else -> {
                null
            }
        }
    }
}