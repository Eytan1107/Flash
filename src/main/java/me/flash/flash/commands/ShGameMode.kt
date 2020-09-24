package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
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
                sender.sendMessage(("You set your gamemode to &c${gameMode.name.toLowerCase()}&6.").prefix())
                Flash.staffMessage(sender, "Set their gamemode to &l${gameMode.name.toLowerCase()}&d.")
            }
        } else {
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            val gameMode = parseGamemode(command.name)
                    ?: sender.sendMessage("The command you ran is not a valid gamemode command.".error()).run { return true }
            if (!sender.hasPermission("flash.gamemode.others")) sender.sendMessage(noPermission).run { return true }
            player.gameMode = gameMode
            sender.sendMessage("You set &c${player.name}&6's gamemode to &c${gameMode.name.toLowerCase()}&6.".prefix())
            player.sendMessage("&c${sender.name} &6set your gamemode to &c${gameMode.name.toLowerCase()}&6.".prefix())
            Flash.staffMessage(sender, "Set &l${player.name}&d's gamemode to &l${gameMode.name.toLowerCase()}&d.", player)
        }
        return true
    }

    private fun parseGamemode(arg: String): GameMode? {
        return when (arg) {
            "gmsp", "gm3", "gmspectator" -> GameMode.SPECTATOR
            "gmc", "gm1", "gmcreative" -> GameMode.CREATIVE
            "gms", "gm0", "gmsurvival" -> GameMode.SURVIVAL
            "gma", "gm2", "gmadventure" -> GameMode.ADVENTURE
            else -> null
        }
    }

}
