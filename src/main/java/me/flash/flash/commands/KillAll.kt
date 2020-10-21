package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import java.util.*


class KillAll : FlashCommand("killall|slayall") {

    init {
        description = "Kill all players"
    }
    override fun run() {
        if (!sender.hasPermission("flash.killall")) sender.sendMessage(noPermission).run { return }
        if (args.isNotEmpty()) sender.sendMessage("Too many arguments".error()).run { return }
        if (sender is Player) {
            val playerss = getPlayer().world.players.filter { p -> !p.hasPermission("*") }
            (sender as Player).world.players.filter { p -> !p.hasPermission("*") }.forEach { players ->
                players.health = 0.00
                players.sendMessage("You were killed by ${nice()}${sender.name}".prefix())
            }
            when {
                playerss.size == 1 -> {
                    sender.sendMessage("You have killed ${nice()}${playerss.size} &6player".prefix())
                    FlashUtil.staffMessage(sender,"Used /killall on &l${playerss.size} &r&dplayer")
                }
                playerss.size > 1 -> {
                    sender.sendMessage("You have killed ${nice()}${playerss.size} &6players".prefix())
                    FlashUtil.staffMessage(sender,"Used /killall on &l${playerss.size} &r&dplayers")
                }
                else -> {
                    sender.sendMessage("No players killed".prefix())
                }
            }
            return
        }
        else {
            val online: List<Player> = ArrayList(Bukkit.getOnlinePlayers()).filter { p -> !p.hasPermission("*") }
            online.filter { p -> !p.hasPermission("*") }.forEach { onlinePlayers ->
                onlinePlayers.health = 0.00
                onlinePlayers.sendMessage("You were killed by Console.".prefix())
            }
            when {
                online.size == 1 -> {
                    sender.sendMessage("You have killed ${nice()}${online.size} &6player".prefix())
                    FlashUtil.staffMessage(sender,"Used /killall on &l${online.size} &r&dplayer")
                }
                online.size > 1 -> {
                    sender.sendMessage("You have killed ${nice()}${online.size} &6players".prefix())
                    FlashUtil.staffMessage(sender,"Used /killall on &l${online.size} &r&dplayers")
                }
                else -> {
                    sender.sendMessage("No players killed".prefix())
                }
            }
            return
        }
    }
}