package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Players : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).run { return true }
        if (args.size > 1) sender.sendMessage("Too many arguments".error()).run { return true }
        if (args.isEmpty()) {
            var players = arrayOf<String>()
            Bukkit.getServer().onlinePlayers.forEach { p ->
                players += p.name
            }
            if (players.isEmpty()) sender.sendMessage("There is no one online".error()).run { return true }
            if (players.size == 1) sender.sendMessage("&c${players.size} &6player".prefix()) else sender.sendMessage("&c${players.size} &6players".prefix())
            sender.sendMessage("Online players are :\n ".prefix() + players.joinToString("&f, &a".color(), "&f[&a".color(), "&f] &a".color()))
        } else {
            val pworld = args.first()
            val players = mutableListOf<Player>()
            listOf("island_normal_world", "skyblock_spawn", "skyblock").let { list ->
                if (list.contains(pworld)) {
                    players.addAll(Bukkit.getWorld("skyblock_spawn").players)
                    players.addAll(Bukkit.getWorld("island_normal_world").players)
                    if (players.isEmpty()) sender.sendMessage("There is no one online".error()).run { return true }
                    if (players.size == 1) sender.sendMessage("&c${players.size} &6player".prefix()) else sender.sendMessage("&c${players.size} &6players".prefix())
                    sender.sendMessage("Online players are :\n ".prefix() + players.joinToString("&f, &a".color(), "&f[&a".color(), "&f] &a".color()) { it.name })
                    return true
                }
            }
            listOf("lobby", "hub", "l").let { list ->
                if (list.contains(pworld)) {
                    players.addAll(Bukkit.getWorld("world").players)
                    if (players.isEmpty()) sender.sendMessage("There is no one online".error()).run { return true }
                    if (players.size == 1) sender.sendMessage("&c${players.size} &6player".prefix()) else sender.sendMessage("&c${players.size} &6players".prefix())
                    sender.sendMessage("Online players are :\n ".prefix() + players.joinToString("&f, &a".color(), "&f[&a".color(), "&f] &a".color()) { it.name })
                    return true
                }
            }
            Bukkit.getWorld(args.first()) ?: sender.sendMessage("There is no server matching: \"&c".error() + args.first().color() + "\"".color()).run { return true }
            players.addAll(Bukkit.getWorld(args.first()).players)
            if (players.isEmpty()) sender.sendMessage("There is no one online".error()).run { return true }
            if (players.size == 1) sender.sendMessage("&c${players.size} &6player".prefix()) else sender.sendMessage("&c${players.size} &6players".prefix())
            sender.sendMessage("Online players are :\n ".prefix() + players.joinToString("&f, &a".color(), "&f[&a".color(), "&f] &a".color()) { it.name })
        }
        return true
    }
}