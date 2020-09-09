package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin


class Server : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
    val player = Bukkit.getPlayer(sender.name) ?: sender.sendMessage(targetOffline).run { return true }
    val target1 = args.last()
    val target = Bukkit.getPlayer(target1) ?: sender.sendMessage(targetOffline).run { return true }
    if (args.size == 1) {
        when {
            args.equals("hub") -> {
                sender.sendMessage("Teleporting you to Hub".prefix())
                player.teleport(Bukkit.getWorld("world").spawnLocation)
                //player.teleport(Bukkit.getServer("hub").spawnLocation)
                return true
            }
            args.equals("kitpvp") -> {
                sender.sendMessage("Teleporting you to KitPvP".prefix())
                player.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                //player.teleport(Bukkit.getServer("kitpvp").spawnLocation)
                return true
            }
            args.equals("skyblock") -> {
                sender.sendMessage("Teleporting you to SkyBlock".prefix())
                player.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
                //player.teleport(Bukkit.getServer("skyblock").spawnLocation)
                return true
            }
            args.equals("tntrun") -> {
                sender.sendMessage("Teleporting you to TntRun".prefix())
                player.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                //player.teleport(Bukkit.getServer("tntrun").spawnLocation)
                return true
            }
            args.equals("event") -> {
                sender.sendMessage("Teleporting you to Event".prefix())
                player.teleport(Bukkit.getWorld("event").spawnLocation)
                //player.teleport(Bukkit.getServer("event").spawnLocation)
                return true
            }
            args.equals("builds") -> {
                sender.sendMessage("Teleporting you to Builds".prefix())
                player.teleport(Bukkit.getWorld("builds").spawnLocation)
                //player.teleport(Bukkit.getServer("builds").spawnLocation)
                return true
            }
            args.equals("kitpvp") -> {
                sender.sendMessage("Teleporting you to KitPvP".prefix()).run { return true }
            }
        }
    } else if (args.size == 2) {
        when {
            args.contains("hub") -> {
                sender.sendMessage("Teleporting &l$target&r &6to &lHub".prefix())
                target.sendMessage("You have been teleported to &lHub".prefix())
                target.teleport(Bukkit.getWorld("world").spawnLocation)
                //target.teleport(Bukkit.getServer("hub").spawnLocation)
                return true
            }
            args.contains("kitpvp") -> {
                sender.sendMessage("Teleporting &l$target&r &6to &lKitPvP".prefix())
                target.sendMessage("You have been teleported to &KitPvP".prefix())
                target.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                //target.teleport(Bukkit.getServer("kitpvp").spawnLocation)
                return true
            }
            args.contains("skyblock") -> {
                sender.sendMessage("Teleporting &l$target&r &6to &lSkyBlock".prefix())
                target.sendMessage("You have been teleported to &lSkyBlock".prefix())
                target.teleport(Bukkit.getWorld("skyblock_island").spawnLocation)
                //target.teleport(Bukkit.getServer("skyblock").spawnLocation)
                return true
            }
            args.contains("tntrun") -> {
                sender.sendMessage("Teleporting &l$target&r &6to &lTnTRun".prefix())
                target.sendMessage("You have been teleported to &lTnTRun".prefix())
                target.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                //target.teleport(Bukkit.getServer("tntrun").spawnLocation)
                return true
            }
            args.contains("event") -> {
                sender.sendMessage("Teleporting &l$target&r &6to &lEvent".prefix())
                target.sendMessage("You have been teleported to &lEvent".prefix())
                target.teleport(Bukkit.getWorld("event").spawnLocation)
                //target.teleport(Bukkit.getServer("event").spawnLocation)
                return true
            }
            args.contains("builds") -> {
                sender.sendMessage("Teleporting &l$target&r &6to &lBuilds".prefix())
                target.sendMessage("You have been teleported to &lBuilds".prefix())
                target.teleport(Bukkit.getWorld("builds").spawnLocation)
                //target.teleport(Bukkit.getServer("builds").spawnLocation)
                return true
            }
        }
    } else player.sendMessage("error".error())

    return true
    }
}