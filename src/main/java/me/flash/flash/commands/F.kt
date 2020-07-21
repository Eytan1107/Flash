package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class F : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("core.f")) {
            sender.sendMessage(Flash.noPermission)
        } else if (args.isEmpty()){
            Bukkit.getOnlinePlayers().forEach { player->
                player.chat("F")
            }
        } else {
            Bukkit.getOnlinePlayers().forEach { player->
                player.chat(args.joinToString { " " })
            }
        }
        return true
    }
}
