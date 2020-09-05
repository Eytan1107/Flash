package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.GameMode
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ShGameMode : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.gamemode")) sender.sendMessage(Flash.noPermission).run { return true }
        if (args.isEmpty()) {
            if (sender !is Player) sender.sendMessage("&cUsage: /gamemode [gamemode] [player]".prefix()).run { return true }
            else {
                val gameMode = parseGamemode(command.name)
                        ?: sender.sendMessage("The command you ran is not a valid gamemode command.".error()).run { return true }
                sender.gameMode = gameMode
                sender.sendMessage(("You set your gamemode to ${gameMode.name.toLowerCase()} mode.").prefix())
                Flash.staffMessage(sender.name, "Set their gamemode to ${gameMode.name.toLowerCase()} mode")
            }
        } else {
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            val gameMode = parseGamemode(command.name)
                    ?: sender.sendMessage("The command you ran is not a valid gamemode command.".error()).run { return true }
            player.gameMode = gameMode
            sender.sendMessage("You set ${player.name}'s gamemode to ${gameMode.name.toLowerCase()} mode.".prefix())
            player.sendMessage("${sender.name} set your gamemode to ${gameMode.name.toLowerCase()} mode.".prefix())
            Flash.staffMessage(sender.name, "Set ${player.name}'s gamemode to ${gameMode.name.toLowerCase()} mode")
        }
        return true
    }

    private fun parseGamemode(arg: String): GameMode? {
        return when (arg) {
            "gmsp" -> GameMode.SPECTATOR
            "gmc" -> GameMode.CREATIVE
            "gms" -> GameMode.SURVIVAL
            "gma" -> GameMode.ADVENTURE
            else -> null
        }
    }

}
