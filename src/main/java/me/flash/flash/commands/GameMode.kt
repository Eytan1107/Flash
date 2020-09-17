package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.playersInWorlds
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GameMode : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) sender.sendMessage("&cUsage : /gamemode [gamemode] [player]").run { return true }
            else sender.sendMessage("&cUsage : /gamemode [gamemode] <player>").run { return true }
        } else if (args.size == 1) {
            if (sender !is Player) sender.sendMessage("&cUsage : /gamemode [gamemode] [player]").run { return true }
            if (!sender.hasPermission("flash.gamemode")) sender.sendMessage(Flash.noPermission).run { return true }
            else {
                val gameMode = parseGamemode(args.first()) ?: sender.sendMessage("That is not a valid gamemode!".error()).run { return true }
                sender.gameMode = gameMode
                Flash.staffMessage(sender, "Set their gamemode to ${gameMode.name.toLowerCase()} mode.")
            }
        } else {
            val gameMode = parseGamemode(args.first()) ?: sender.sendMessage("That is not a valid gamemode!".error()).run { return true }
            val player = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            if (!sender.hasPermission("flash.gamemode.others")) sender.sendMessage(noPermission).run { return true }
            player.gameMode = gameMode
            Flash.staffMessage(sender, "Set ${player.name}'s gamemode to ${gameMode.name.toLowerCase()} mode.", player)
            player.sendMessage("Your gamemode was set to ${gameMode.name.toLowerCase()} mode by ${sender.name}")
            sender.sendMessage("You set ${player.name}'s gamemode to ${gameMode.name.toLowerCase()}")
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