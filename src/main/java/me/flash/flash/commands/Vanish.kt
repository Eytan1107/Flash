package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.color
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Vanish : CommandExecutor {
    companion object {
        var vanishedPlayers = emptyList<Player>().toMutableList()
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(FlashUtil.notPlayer)
        } else {
            if (!sender.hasPermission("flash.vanish")) {
                sender.sendMessage(FlashUtil.noPermission)
                sender.world.players.forEach { player  ->
                    player.sendMessage("&6[&3-&6]&6 ${player.name.removePrefix("Member")}".color())
                }
                return true
            }
            if (args.isEmpty()) {
                if (vanishedPlayers.contains(sender)) {
                    Bukkit.getOnlinePlayers().forEach { player ->
                        player.showPlayer(sender)
                    }
                } else {
                    Bukkit.getOnlinePlayers().forEach { player->
                        if (!player.hasPermission("flash.vanish.see")) {
                            player.hidePlayer(sender)
                        }
                    }
                }

            }

        }
        return true
    }
}
