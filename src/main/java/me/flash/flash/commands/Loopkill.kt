package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Loopkill : CommandExecutor {
    companion object {
        var tagged = ArrayList<Player>()
        val runnable = Runnable {
            tagged.forEach { player ->
                player.health = 0.00
            }
        }
    }

    fun start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Flash.instance, runnable, 20L, 20L)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("kick.noob")) {
            if (args.isEmpty()) {
                sender.sendMessage("${ChatColor.RED}Please specify a player")
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player == null) {
                    sender.sendMessage("${ChatColor.RED}Player ${args.first()} was not found, please check for any spelling errors and try again.")
                } else {
                    if (tagged.contains(player)) {
                        sender.sendMessage("${ChatColor.AQUA}No longer loopkilling ${player.name}")
                        tagged.remove(player)
                    } else {
                        sender.sendMessage("${ChatColor.AQUA}Loopkilling ${player.name}")
                        tagged.add(player)
                    }
                }
            }
        } else {
            sender.sendMessage("${ChatColor.RED}You do not have permission to use this command!")
        }
        return true
    }
}