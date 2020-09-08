package me.flash.flash.commands

import me.flash.flash.Flash.Companion.color
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Pro : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("&cMe Pwo".color())
        } else {
            val player = Bukkit.getPlayer(args[0])
            if (player == null) {
                sender.sendMessage("PLAYER NOT ONLINE U NOT PWO".color())
            } else {
                player.sendMessage("&cYou Pwo by &4${sender.name}".color())
                sender.sendMessage("&cSent a Pwo to &4${player.name}".color())
            }
        }
        return true
    }
}