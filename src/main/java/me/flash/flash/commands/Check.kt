package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Check : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("core.check")) {
            sender.sendMessage(Flash.noPermission)
        } else if (args.isEmpty()) {
            sender.sendMessage(Flash.formatMessage("&cPlease specify a player."))
        } else {
            val player = Bukkit.getPlayer(args.first())
            if (player == null) {
                sender.sendMessage(Flash.targetOffline)
                return true
            }
            val name: String
            name = when (player.world.name) {
                "world" -> "Hub"
                "kitpvp" -> "KitPvP"
                "island_normal_world" -> "SkyBlock"
                "event" -> "Event"
                "tntrun" -> "TnTRun"
                else -> "a non registered world"
            }
            sender.sendMessage(Flash.formatMessage("${player.name} is in $name"))
        }
        return true
    }
}
