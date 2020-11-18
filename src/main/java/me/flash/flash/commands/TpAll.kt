package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TpAll : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.tpall")) {
            sender.sendMessage(FlashUtil.noPermission)
            return true
        }
        if (sender !is Player) {
            sender.sendMessage(FlashUtil.notPlayer)
            return true
        }
        if (args.isEmpty()) {
            val players = sender.world.players
            if (players.size < 2) {
                sender.sendMessage("&cThere are no other players in your world to teleport to you.".error())
            } else {
                players.filter { player -> player != sender }.forEach { player->
                    player.teleport(sender)
                }
                if (players.size > 2) {
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting &c${players.size - 1} &6players to you...".prefix()) else sender.sendMessage("Teleporting &l${players.size - 1} &6players to you...".prefix())
                    FlashUtil.staffMessage(sender, "teleported all players in their world (&c${players.size - 1} &dplayers) to them.")
                }
                else {
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting &c${players.size - 1} &6player to you...".prefix()) else sender.sendMessage("Teleporting &l${players.size - 1} &6player to you...".prefix())
                }
            }
        } else if (args.first() == "all") {
            val onlinePlayers = Bukkit.getOnlinePlayers()
            onlinePlayers.forEach { it.teleport(sender) }
            if (onlinePlayers.size > 2) {
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting &c${onlinePlayers.size - 1} &6players to you...".prefix()) else sender.sendMessage("Teleporting &l${onlinePlayers.size - 1} &6players to you...".prefix())
                FlashUtil.staffMessage(sender, "teleported all players (&l${onlinePlayers.size - 1} &dplayer) to them.")
            }
            else {
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Teleporting &c${onlinePlayers.size - 1} &6player to you...".prefix()) else sender.sendMessage("Teleporting &l${onlinePlayers.size - 1} &6player to you...".prefix())
            }
        } else {
            sender.sendMessage("Unrecognized argument. (${args.first()})".error())
        }
        return true
    }
}
