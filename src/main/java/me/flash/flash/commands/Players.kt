package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.World
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
            sender.sendMessage("Online players are :\n ".prefix() + players.joinToString("&f, &a".color(), "&f[&a".color(), "&f] &a".color()))
        } else {
            val pworld = args.first()
            val players = getPlayersForWorld(pworld)
            if (players == null) sender.sendMessage("f").run { return true }
            if (players.isEmpty()) sender.sendMessage("There is no one online".error()).run { return true }
            sender.sendMessage("Online players are :\n ".prefix() + players.joinToString("&f, &a".color(), "&f[&a".color(), "&f] &a".color()) { it.name })
        }
        return true
    }

    private fun getPlayersForWorld(pworld: String): MutableList<Player> {
        val players = mutableListOf<Player>()
        listOf("island_normal_world", "skyblock_spawn", "skyblock").let { list ->
            if (list.contains(pworld)) {
                players.addAll(Bukkit.getWorld("skyblock_spawn").players)
                players.addAll(Bukkit.getWorld("island_normal_world").players)
                return players
            }
        }
        listOf("lobby", "hub", "l").let { list ->
            if (list.contains(pworld)) {
                players.addAll(Bukkit.getWorld("world").players)
                return players
            }
        }
        players.addAll(Bukkit.getWorld(pworld).players)
        return players
    }
}