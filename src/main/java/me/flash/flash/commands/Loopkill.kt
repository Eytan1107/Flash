package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
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
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Flash::class.java), runnable, 20L, 20L)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("kick.noob")) {
            if (args.isEmpty()) {
                sender.sendMessage("&cPlease specify a player".colour())
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player == null) {
                    sender.sendMessage("&cPlayer ${args.first()} was not found, please check for any spelling errors and try again.".colour())
                } else {
                    if (tagged.contains(player)) {
                        sender.sendMessage("&aNo longer loopkilling ${player.name}".colour())
                        tagged.remove(player)
                    } else {
                        sender.sendMessage("&aLoopkilling ${player.name}".colour())
                        tagged.add(player)
                    }
                }
            }
        } else {
            sender.sendMessage("&cYou do not have permission to use this command!".colour())
        }
        return true
    }
}