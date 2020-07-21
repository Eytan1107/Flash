package me.flash.flash.listeners

import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent

class EventsListener : Listener {
    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        event.player.world.players.forEach { t: Player? ->
            t!!.sendMessage("&6[&3+&6] ${t.displayName}")
        }
        event.quitMessage = null // Take away the default (player) left the game
    }

    @EventHandler
    fun kick(event: PlayerKickEvent) {
        event.leaveMessage = null
    }
    fun c(string: String) : String = ChatColor.translateAlternateColorCodes('&', string)

}
