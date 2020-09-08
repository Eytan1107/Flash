package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.error
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.time.Instant
import java.util.*

class Kits : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = if (sender is Player) sender else sender.sendMessage(Flash.notPlayer).run { return true }
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) {
            if (args.isNotEmpty()) sender.sendMessage("Flash told me to not let you use arguments, sorry.".error()).run { return true }
            sender.sendMessage("&2&nKits&r".colour())
            sender.sendMessage("")
            sender.sendMessage("&aPvP &7- &8/kit pvp &7(Every 10 seconds)".colour())
            player.sendMessage(if (player.hasPermission("flash.kit.fast")) "&aFast &7- &8/kit fast &7(Every hour)".colour() else "&7&mFast".colour())
            player.sendMessage(if (player.hasPermission("flash.kit.speedster")) "&aSpeedster &7- &8/kit speedster &7(Every 3 hours)".colour() else "&7&mSpeedster".colour())
            player.sendMessage(if (player.hasPermission("flash.kit.godspeed")) "&aGodSpeed &7- &8/kit godspeed &7(Every 12 hours)".colour() else "&7&mGodSpeed".colour())
            player.sendMessage(if (player.hasPermission("flash.kit.speedforce")) "&aSpeedForce &7- &8/kit speedforce &7(Every 24 hours)".colour() else "&7&mSpeedForce".colour())
        } else {
            sender.sendMessage("You need to be in KitPvP".error())
        }
        return true
    }
    companion object {
        val kitpvpCooldowns = mapOf<Player, Instant>()
    }
}
