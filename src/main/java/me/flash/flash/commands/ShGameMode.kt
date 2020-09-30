package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.noPermission
import me.flash.flash.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.GameMode
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ShGameMode : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.gamemode")) sender.sendMessage(FlashUtil.noPermission).run { return true }
        if (args.isEmpty()) {
            if (sender !is Player) sender.sendMessage("&cUsage: /gamemode [gamemode] [player]".prefix()).run { return true }
            else {
                val gameMode = parseGamemode(command.name)
                        ?: sender.sendMessage("The command you ran is not a valid gamemode command.".error()).run { return true }
                sender.gameMode = gameMode
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You set your gamemode to &c${gameMode.name.toLowerCase()}&6.".prefix()) else sender.sendMessage("You set your gamemode to &l${gameMode.name.toLowerCase()}&6.".prefix())
                FlashUtil.staffMessage(sender, "Set their gamemode to &l${gameMode.name.toLowerCase()}&d.")
            }
        } else {
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(FlashUtil.targetOffline).run { return true }
            val gameMode = parseGamemode(command.name)
                    ?: sender.sendMessage("The command you ran is not a valid gamemode command.".error()).run { return true }
            if (!sender.hasPermission("flash.gamemode.others")) sender.sendMessage(noPermission).run { return true }
            player.gameMode = gameMode
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You set &c${player.name}'s&6 gamemode to &c${gameMode.name.toLowerCase()}&6.".prefix()) else sender.sendMessage("You set &l${player.name}'s&6 gamemode to &l${gameMode.name.toLowerCase()}&6.".prefix())
            if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&c${sender.name} &6set your gamemode to &c${gameMode.name.toLowerCase()}&6.".prefix()) else player.sendMessage("&l${sender.name} &6set your gamemode to &l${gameMode.name.toLowerCase()}&6.".prefix())
            FlashUtil.staffMessage(sender, "Set &l${player.name}&d's gamemode to &l${gameMode.name.toLowerCase()}&d.", player)
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
