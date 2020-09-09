package me.flash.flash.commands


import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.Bukkit


class Server : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("/server <ServerName> [Player]".prefix())
            sender.sendMessage("Servers: kitpvp, skyblock, hub, event, builds".prefix())
            return true
        }
        val player = Bukkit.getPlayer(sender.name)
        val target = Bukkit.getPlayer(args.last())
        if (args.size == 1) {
            when {
                args.contains("hub") -> {
                    sender.sendMessage("Teleporting you to &lHub".prefix())
                    player.teleport(Bukkit.getWorld("world").spawnLocation)
                    //player.teleport(Bukkit.getServer("hub").spawnLocation)
                    return true
                }
                args.contains("kitpvp") -> {
                    sender.sendMessage("Teleporting you to &lKitPvP".prefix())
                    player.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                    //player.teleport(Bukkit.getServer("kitpvp").spawnLocation)
                    return true
                }
                args.contains("skyblock") -> {
                    sender.sendMessage("Teleporting you to &lSkyBlock".prefix())
                    player.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
                    //player.teleport(Bukkit.getServer("skyblock").spawnLocation)
                    return true
                }
                args.contains("builds") -> {
                    if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
                    sender.sendMessage("Teleporting you to &lBuilds".prefix())
                    player.teleport(Bukkit.getWorld("builds").spawnLocation)
                    //player.teleport(Bukkit.getServer("builds").spawnLocation)
                    return true
                }
                args.contains("tntrun") -> {
                    sender.sendMessage("Teleporting you to &lTnTRun".prefix())
                    player.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                    //player.teleport(Bukkit.getServer("tntrun").spawnLocation)
                    return true
                }
                args.contains("event") -> {
                    sender.sendMessage("Teleporting you to &lEvent".prefix())
                    player.teleport(Bukkit.getWorld("event").spawnLocation)
                    //player.teleport(Bukkit.getServer("event").spawnLocation)
                    return true
                }
            }
        } else if (args.size == 2) {
            if (player == target) player.sendMessage("Sorry, that's not how it works".error()).run { return true }
            if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
             when {
                 args.contains("hub") -> {
                     sender.sendMessage("You Teleported &l${target.name} &r &6to &lHub".prefix())
                     target.teleport(Bukkit.getWorld("world").spawnLocation)
                     target.sendMessage("You have been teleported to &lHub&r &6by &l${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("hub").spawnLocation)
                     return true
                 }
                 args.contains("kitpvp") -> {
                     sender.sendMessage("You Teleported &l${target.name}&r &6to &lKitPvP".prefix())
                     target.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                     target.sendMessage("You have been teleported to &lKitPvP&r &6by &l${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("kitpvp").spawnLocation)
                     return true
                 }
                 args.contains("skyblock") -> {
                     sender.sendMessage("You Teleported &l${target.name}&r &6to &lSkyBlock".prefix())
                     target.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
                     target.sendMessage("You have been teleported to &lSkyBlock&r &6by &l${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("skyblock").spawnLocation)
                     return true
                 }
                 args.contains("builds") -> {
                     if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
                     if (!target.hasPermission("flash.staff")) target.sendMessage(noPermission).run { return true }
                     sender.sendMessage("You Teleported &l${target.name}&r &6to &lBuilds".prefix())
                     target.teleport(Bukkit.getWorld("builds").spawnLocation)
                     target.sendMessage("You have been teleported to &lBuilds&r &6by &l${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("builds").spawnLocation)
                     return true
                 }
                 args.contains("tntrun") -> {
                     sender.sendMessage("You Teleported &l${target.name}&r &6to &lTnTRun".prefix())
                     target.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                     target.sendMessage("You have been teleported to &lTnTRun&r &6by &l${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("tntrun").spawnLocation)
                     return true
                 }
                 args.contains("event") -> {
                     sender.sendMessage("You Teleported &l${target.name}&r &6to &lEvent".prefix())
                     target.teleport(Bukkit.getWorld("event").spawnLocation)
                     target.sendMessage("You have been teleported to &lEvent&r &6by &l${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("event").spawnLocation)
                     return true
                 }
             }
        }
        return true
    }
}