package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TpAll : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.tpall")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (sender !is Player) {
            sender.sendMessage(Flash.notPlayer)
            return true
        }
        if (args.isEmpty()) {
            val players = sender.world.players
            if (players.size < 2) {
                sender.sendMessage("&cThere are no other players in your world to teleport to you.".error())
            } else {
                players.forEach { player->
                    player.teleport(sender)
                }
                sender.sendMessage("Ok, &l${players.size}&r &6players were teleported to you.".prefix())
            }
        } else if (args.first() == "all") {
            val onlinePlayers = Bukkit.getOnlinePlayers()
            onlinePlayers.forEach { it.teleport(sender) }
            sender.sendMessage("Ok, &l${onlinePlayers.size}&r &6players were teleported to you.".prefix())
        } else {
            sender.sendMessage("Unrecognized argument. (${args.first()})".error())
        }
        return true
    }
}
