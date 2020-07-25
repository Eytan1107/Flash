package me.flash.flash.commands

import me.flash.flash.Flash.Companion.colour
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Pro : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("&cMe Pwo".colour())
        } else {
            val player = Bukkit.getPlayer(args[0])
            if (player == null) {
                sender.sendMessage("PLAYER NOT ONLINE U NOT PWO".colour())
            } else {
                player.sendMessage("&cYou Pwo by $4${sender.name}".colour())
                sender.sendMessage("&cSent a Pwo to $4${player.name}".colour())
            }
        }
        return true
    }
}