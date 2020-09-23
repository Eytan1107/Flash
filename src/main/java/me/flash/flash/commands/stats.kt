package me.flash.flash.commands

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.sql.Connection
import java.util.*

class stats : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = Bukkit.getPlayer(sender.name)
        val uuid = player.uniqueId.toString()
        val result = Flash.sql.createStatement().executeQuery("SELECT * FROM data WHERE uuid = '$uuid'")
        while (result.next()) {
            val next = result.next()
            player.sendMessage("Kills: " + result.getString("kills"))
            player.sendMessage("Deaths: " + result.getString("deaths"))
        }
        return true
    }
}
