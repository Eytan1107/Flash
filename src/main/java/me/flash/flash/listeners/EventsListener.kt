package me.flash.flash.listeners

import me.flash.flash.Flash.Companion.colour
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class EventsListener : Listener {
    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        event.player.world.players.forEach { player->
            player.sendMessage("&6[&3-&6] ${player.displayName}".colour())
        }
        event.quitMessage = null // Take away the default (player) left the game
    }

    @EventHandler
    fun world(event: PlayerChangedWorldEvent) {
        event.player.world.players.forEach { player->
            player.sendMessage("&6[&3-&6] ${player.displayName}".colour())
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.player.world.players.forEach {player->
            player.sendMessage("&6[&3+&6] ${player.displayName}".colour())
        }
        event.joinMessage = null
    }
}
