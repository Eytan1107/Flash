package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.event.Listener


class Server : CommandExecutor, Listener, TabCompleter {
    
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender.hasPermission("flash.staff")) sender.sendMessage("&c/server <Server Name> [Player]".prefix()) else sender.sendMessage("&c/server <Server Name>".prefix())
            if (sender.hasPermission("multiverse.access.*")) sender.sendMessage("&cServers: KitPvP, Skyblock, Hub, Event, Builds".prefix()) else sender.sendMessage("&cServers: KitPvP, Skyblock, Hub, Event".prefix())
            return true
        }
        if (args.size > 2) sender.sendMessage("Too many arguments".error()).run { return true }
        val player = Bukkit.getPlayer(sender.name)
        val target = Bukkit.getPlayer(args.last())
        val w = Bukkit.getWorld(args.first()) ?: if (sender.hasPermission("multiverse.access.*")) sender.sendMessage("&cServers: KitPvP, Skyblock, Hub, Event, Builds".prefix()).run { return true } else sender.sendMessage("&cServers: KitPvP, Skyblock, Hub, Event".prefix()).run { return true }
        if (args.size == 1 || args.size == 2 && player == target) {
            when {
                args.first() == "hub" || args.first() == "lobby" -> {
                    if (player.world.name == "world") sender.sendMessage("You are already in Hub, do &e/spawn &cto return to spawn".error()).run { return true }
                    sender.sendMessage("Teleporting you to &cHub".prefix())
                    player.teleport(Bukkit.getWorld("world").spawnLocation)
                    //player.teleport(Bukkit.getServer("hub").spawnLocation)
                    return true
                }
                args.first() == "kitpvp" -> {
                    if (player.world.name == "kitpvp") sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                    sender.sendMessage("Teleporting you to &cKitPvP".prefix())
                    player.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                    //player.teleport(Bukkit.getServer("kitpvp").spawnLocation)
                    return true
                }
                args.first() == "skyblock" -> {
                    if (player.world.name == "island_normal_world" || player.world.name == "skyblock_spawn") sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                    sender.sendMessage("Teleporting you to &cSkyBlock".prefix())
                    player.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
                    //player.teleport(Bukkit.getServer("skyblock").spawnLocation)
                    return true
                }
                args.first() == "builds" -> {
                    if (!sender.hasPermission("flash.access.builds")) {
                        sender.sendMessage(noPermission)
                        return true
                    }
                    if (!sender.hasPermission("multiverse.access.*")) Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user" + sender.name + "permission")
                    if (player.world.name == "builds") sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                    if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
                    sender.sendMessage("Teleporting you to &cBuilds".prefix())
                    player.teleport(Bukkit.getWorld("builds").spawnLocation)
                    //player.teleport(Bukkit.getServer("builds").spawnLocation)
                    return true
                }
                args.first() == "tntrun" -> {
                    if (player.world.name == "tntrun") sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                    sender.sendMessage("Teleporting you to &cTnTRun".prefix())
                    player.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                    //player.teleport(Bukkit.getServer("tntrun").spawnLocation)
                    return true
                }
                args.first() == "event" -> {
                    if (!sender.hasPermission("flash.access.event")) {
                        sender.sendMessage(noPermission)
                        return true
                    }
                    if (player.world.name == "event") sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                    sender.sendMessage("Teleporting you to &cEvent".prefix())
                    player.teleport(Bukkit.getWorld("event").spawnLocation)
                    //player.teleport(Bukkit.getServer("event").spawnLocation)
                    return true
                }
                else -> {
                    if (player.hasPermission("multiverse.access.*")) {
                        if (player.world.name == w.name) sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                        player.teleport(w.spawnLocation)
                        sender.sendMessage("Teleporting you to &c".prefix() + w.name)
                        return true
                    }
                }
            }
        } else if (args.size == 2) {
            if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
             when {
                 args[0] == "hub" || args[0] == "lobby" -> {
                     if (target.world.name == "world") sender.sendMessage("This player is already in Hub, do &e/spawn <player> &cto teleport them back to spawn !".error()).run { return true }
                     sender.sendMessage("You Teleported &c${target.name} &6to &lHub".prefix())
                     target.teleport(Bukkit.getWorld("world").spawnLocation)
                     target.sendMessage("You have been teleported to &cHub &6by &c${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("hub").spawnLocation)
                     return true
                 }
                 args[0] == "kitpvp" -> {
                     if (target.world.name == "kitpvp") sender.sendMessage("This player is already on this server, do &e/spawn <player> &cto teleport this player back to spawn".error()).run { return true }
                     sender.sendMessage("You Teleported &c${target.name} &6to &cKitPvP".prefix())
                     target.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                     target.sendMessage("You have been teleported to &cKitPvP &6by &c${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("kitpvp").spawnLocation)
                     return true
                 }
                 args[0] == "skyblock" -> {
                     if (target.world.name == "island_normal_world" || target.world.name == "skyblock_spawn") sender.sendMessage("This player is already on this server, do &e/spawn <player> &cto teleport this player back to spawn".error()).run { return true }
                     sender.sendMessage("You Teleported &c${target.name} &6to &cSkyBlock".prefix())
                     target.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
                     target.sendMessage("You have been teleported to &cSkyBlock &6by &c${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("skyblock").spawnLocation)
                     return true
                 }
                 args[0] == "builds" -> {
                     if (target.world.name == "builds") sender.sendMessage("This player is already on this server, do &e/spawn <player> &cto teleport this player back to spawn".error()).run { return true }
                     if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
                     if (!target.hasPermission("flash.staff")) target.sendMessage(noPermission).run { return true }
                     if (!target.hasPermission("flash.access.builds")) {
                         sender.sendMessage("This player doesn't have permission to go to this server.".error())
                         return true
                     }
                     sender.sendMessage("You Teleported &c${target.name} &6to &cBuilds".prefix())
                     target.teleport(Bukkit.getWorld("builds").spawnLocation)
                     target.sendMessage("You have been teleported to &cBuilds &6by &c${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("builds").spawnLocation)
                     return true
                 }
                 args[0] == "tntrun" -> {
                     if (target.world.name == "tntrun") sender.sendMessage("This player is already on this server, do &e/spawn <player> &cto teleport this player back to spawn".error()).run { return true }
                     sender.sendMessage("You Teleported &c${target.name} &6to &cTnTRun".prefix())
                     target.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                     target.sendMessage("You have been teleported to &cTnTRun &6by &c${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("tntrun").spawnLocation)
                     return true
                 }
                 args[0] == "event" -> {
                     if (target.world.name == "event") sender.sendMessage("This player is already on this server, do &e/spawn <player> &cto teleport this player back to spawn".error()).run { return true }
                     if (!target.hasPermission("flash.access.event")) {
                         sender.sendMessage("This player doesn't have permission to go to this server.".error())
                         return true
                     }
                     sender.sendMessage("You Teleported &c${target.name} &6to &cEvent".prefix())
                     target.teleport(Bukkit.getWorld("event").spawnLocation)
                     target.sendMessage("You have been teleported to &cEvent &6by &c${sender.name}".prefix())
                     //player.teleport(Bukkit.getServer("event").spawnLocation)
                     return true
                 }
                 else -> {
                     if (player.hasPermission("multiverse.access.*")) {
                         if (target.world.name == w.name) sender.sendMessage("You are already on this server, do &e/spawn &cto go back to spawn".error()).run { return true }
                         target.teleport(w.spawnLocation)
                         sender.sendMessage("Teleporting &c${target.name} &6to &c".prefix() + w.name)
                         target.sendMessage("You have been teleported to &c" + w.name + " &6by &c${sender.name}".prefix())
                         return true
                     }
                 }
             }
        }
        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        //if (args.size == 1) mutableListOf("hub", "kitpvp", "skyblock", "builds", "tntrun", "event")
        val possible = mutableListOf("hub", "kitpvp", "skyblock", "builds", "tntrun", "event")
        return if (args.size == 1) {
            val toCollection = possible.filter { i -> i.startsWith(args.first())}
            toCollection.toMutableList()
        } else {
            val array = mutableListOf<String>()
            Bukkit.getOnlinePlayers().forEach {
                array.add(it.name)
            }
            array
        }
    }
}