package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent

class Back : CommandExecutor, Listener {
    companion object {
        var table = mutableMapOf<Player, Location>()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (!sender.hasPermission("flash.back")) {
                sender.sendMessage(Flash.noPermission)
                return true
            }
            table[sender].apply {
                if (this != null) {
                    sender.teleport(this)
                    sender.sendMessage("You were teleported to your last known location".prefix())
                } else {
                    sender.sendMessage("You do not have a last known location".error())
                }
            }
        } else {
            sender.sendMessage(Flash.notPlayer)
        }
        return true
    }

    @EventHandler(ignoreCancelled = true)
    fun teleport(event: PlayerTeleportEvent) {
        table[event.player] = event.from
    }

    @EventHandler(ignoreCancelled = true)
    fun death(event: EntityDamageEvent) {
        val player = if (event.entity is Player) event.entity as Player else return
        if ((player.health - event.damage) <= 0) {
            table[player] = player.location
        }
    }
}
