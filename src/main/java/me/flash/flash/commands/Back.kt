package me.flash.flash.commands

import me.flash.flash.Flash
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
        var table = emptyMap<Player, Location>()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (!sender.hasPermission("skript.back")) {
                sender.sendMessage(Flash.noPermission)
                return true
            }
            table[sender].apply {
                if (this != null) {
                    sender.teleport(this)
                    sender.sendMessage("You were teleported to your last known location".prefix())
                } else {
                    sender.sendMessage("You do not have a last known location".prefix())
                }
            }
        } else {
            sender.sendMessage(Flash.notPlayer)
        }
        return true
    }

    @EventHandler
    fun teleport(event: PlayerTeleportEvent) {
        table.plus(Pair(event.player, event.from))
    }

    @EventHandler
    fun death(event: EntityDamageEvent) {
        val entity = event.entity
        if (entity is Player && entity.health - event.damage < 1) {
            table.plus(Pair(event.entity, event.entity.location))
        }
    }
}
