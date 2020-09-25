package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Hub : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size > 1) sender.sendMessage("Too many arguments".error())
        if (args.isEmpty()) {
            if (sender !is Player) sender.sendMessage("Are you dumb stupid or dumb ?".error()).run { return true }
            if (sender.world.name != "world") {
                sender.teleport(Bukkit.getWorld("world").spawnLocation)
                sender.sendMessage("Teleporting to you to Hub...".prefix())
            } else {
                sender.sendMessage("You are already in Hub, do &e/spawn &cto return to spawn !".error())
            }
            return true
        }
        else {
            if (!sender.hasPermission("flash.hub.others")) sender.sendMessage(noPermission).run { return true }
            val player = if (args.first() == sender.name) sender as Player else Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            if (sender == player) {
                if (player.world.name == "world") sender.sendMessage("You are already in Hub, do &e/spawn &cto return to spawn !".error()).run { return true }
                sender.sendMessage("Teleporting you to Hub...".prefix())
            }
            else {
                if (player.world.name == "world") sender.sendMessage("This player is already in Hub, do &e/spawn <player> &cto teleport them back to spawn !".error()).run { return true }
                sender.sendMessage("Teleporting &c${player.name} &6to Hub...".prefix())
                player.sendMessage("&c${player.name} &6teleported you to Hub !".prefix())
            }
            player.teleport(Bukkit.getWorld("world").spawnLocation)

        }
        return true
    }
}