package me.flash.flash.commands

import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import java.lang.invoke.MethodHandles.loop

class Players : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("flash.staff")) {
            val player = Bukkit.getPlayer(sender.name)
            var players = arrayOf<String>()
            player.world.players.forEach{p->
                players+=p.name
            }
            if (players.isEmpty()) sender.sendMessage("there is noo one online".error())
            player.sendMessage("Online players are:\n ".prefix() + players.joinToString("&f, ".color(), "&f[&a".color(), "&f]".color() ))
            }
        return true
    }
}