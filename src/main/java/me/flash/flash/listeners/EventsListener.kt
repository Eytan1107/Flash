package me.flash.flash.listeners

import me.flash.flash.Flash
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerKickEvent
import org.bukkit.event.player.PlayerQuitEvent

class EventsListener : Listener {
    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        event.player.world.players.forEach { player->
            player.sendMessage(Flash.colour("&6[&3-&6] ${player.displayName}"))
        }
        event.quitMessage = null // Take away the default (player) left the game
    }

    @EventHandler
    fun world(event: PlayerChangedWorldEvent) {
        event.player.world.players.forEach { player->
            player.sendMessage(Flash.colour("&6[&3-&6] ${player.displayName}"))
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.player.world.players.forEach {player->
            player.sendMessage(Flash.colour("&6[&3+&6] ${player.displayName}"))
        }
        event.joinMessage = null
    }
    fun c(string: String) : String = ChatColor.translateAlternateColorCodes('&', string)

}
