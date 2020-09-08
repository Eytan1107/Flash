package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Check : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.check")) {
            sender.sendMessage(Flash.noPermission)
        } else if (args.isEmpty()) {
            sender.sendMessage("Please specify a player.".error())
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
                "skyblock_spawn" -> "SkyBlock"
                "event" -> "Event"
                "tntrun" -> "TnTRun"
                else -> player.world.name
            }
            sender.sendMessage("${player.name} is in &c$name".prefix())
            sender.sendMessage("${player.name} has &c${player.health} &6health".prefix())
        }
        return true
    }
}
