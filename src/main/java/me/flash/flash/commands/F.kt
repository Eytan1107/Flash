package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class F : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.f")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (args.isEmpty()){
            Bukkit.getOnlinePlayers().filter { p->Vanish.vanishedPlayers.contains(p) }.forEach { player->
                player.chat("F")
            }
        } else {
            Bukkit.getOnlinePlayers().forEach { player->
                player.chat(args.joinToString(" "))
            } // I will change the command (so like /f will be /friends) /f -> "/allf"
            // /allf = make everyone say /f except vanished people, the sender J can you make a place to add TODO messages?
        }
        return true
    }
}
