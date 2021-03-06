package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.notPlayer
import me.flash.flash.utils.FlashUtil.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

// soon to be check player
class stats : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            val player = Bukkit.getPlayer(sender.name) ?: sender.sendMessage(notPlayer).let { return true }
            val uuid = player.uniqueId.toString()
                if (player.uniqueId.toString() == uuid) {
                    val result = Flash.playerdata.createStatement().executeQuery("SELECT * FROM data WHERE uuid = '$uuid'")
                    while (result.next()) {
                        player.sendMessage("&6Your stats are:".color())
                        player.sendMessage("&eKills: ".color() + result.getInt("kills"))
                        player.sendMessage("&eDeaths: ".color() + result.getInt("deaths"))
                    }
                }
        } else if (args.first().isNotEmpty()) {
            if (args.first().toString() == "resetstats".toLowerCase()) {
                if (args[2].isEmpty()) sender.sendMessage("Usage: /stats resetstats <name>".error()).let { return true }
                val player = Bukkit.getPlayer(args[2]) ?: sender.sendMessage(targetOffline).let { return true }
                if (sender.hasPermission("flash.stats.reset")) {
                    Flash.playerdata.prepareStatement("update data set kills=0 where uuid=?").apply {
                        setString(1, player.uniqueId.toString())
                        executeUpdate()
                    }
                    Flash.playerdata.prepareStatement("update data set deaths=0 where uuid=?").apply {
                        setString(1, player.uniqueId.toString())
                        executeUpdate()
                    }
                }
            } else {
                val player2 = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
                val uuid2 = player2.uniqueId.toString()
                if (player2.uniqueId.toString() == uuid2) {
                    if (!sender.hasPermission("flash.staff")) sender.sendMessage(noPermission).let { return true }
                    val result2 = Flash.playerdata.createStatement().executeQuery("SELECT * FROM data WHERE uuid = '$uuid2'")
                    while (result2.next()) {
                        sender.sendMessage("&6The stats of: ${args.first()} are:".color())
                        sender.sendMessage("&eKills: ".color() + result2.getInt("kills"))
                        sender.sendMessage("&eDeaths: ".color() + result2.getInt("deaths"))
                    }
                }
            }
        }
        return true
    }
}
