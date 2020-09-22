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
        val result = Flash.sql.createStatement().execute("SELECT * FROM data WHERE uuid = '$uuid'")
        if (result) {
            val result2 = Flash.sql.createStatement().executeUpdate("select deaths from data where uuid = '$uuid'")
            val result3 = result2.toString()
            sender.sendMessage(result3)
        }
        return true
    }
}
