package me.flash.flash.commands

import me.flash.flash.FlashUtil
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class F : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.f")) {
            sender.sendMessage(FlashUtil.noPermission)
            return true
        }
        if (args.isEmpty()){
            Bukkit.getOnlinePlayers().filter { p->!Vanish.vanishedPlayers.contains(p) }.forEach { player->
                player.chat("F")
                FlashUtil.staffMessage(sender, "Ran the F command")
            }
        } else {
            Bukkit.getOnlinePlayers().forEach { player->
                player.chat(args.joinToString(" "))
                FlashUtil.staffMessage(sender, "Ran the sudo command on all players")
            }
        }
        return true
    }
}
