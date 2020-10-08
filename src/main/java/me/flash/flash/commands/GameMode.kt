package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
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

class GameMode : FlashCommand("gamemode|gm|gmode") {

    init {
        usage = "<gamemode> [player]"
        description = "Change a player's gamemode"
        setMinArgs(1)
    }

    override fun run() {
        if (args.size == 1) {
            checkPlayer()
            checkPerm(("flash.gamemode"))
            val gameMode = parseGamemode(args.first())
                    ?: msg("That is not a valid gamemode!".error()).run { return }
            getPlayer().gameMode = gameMode
           msg("Your gamemode was set to ${nice()}${gameMode.name.toLowerCase()}&6.".prefix())
            FlashUtil.staffMessage(sender, "Set their gamemode to &l${gameMode.name.toLowerCase()}&d.")
            return
        }
        checkPerm("flash.gamemode.others")
        val gameMode = parseGamemode(args.first())
                ?: msg("That is not a valid gamemode!".error()).run { return }
        val player = getTarget(1)
        player.gameMode = gameMode
        FlashUtil.staffMessage(sender, "Set &l${player.name}&d's gamemode to &l${gameMode.name.toLowerCase()}&d.", player)
        msg(player, "Your gamemode was set to ${nice()}${gameMode.name.toLowerCase()} &6by ${nice()}${sender.name}&6.".prefix())
        msg("You set ${nice()}${player.name}'s&6 gamemode to ${nice()}${gameMode.name.toLowerCase()}&6.".prefix())
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