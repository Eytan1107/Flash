package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Kit : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = if (sender is Player) sender else sender.sendMessage(Flash.notPlayer).run { return true }
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) {
            sender.sendMessage("Soon TM".prefix())
        } else {
            player.sendMessage("You need to be in KitPvP".error())
        }
        return true
    }

}
