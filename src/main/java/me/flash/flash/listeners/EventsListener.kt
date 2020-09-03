package me.flash.flash.listeners

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class EventsListener : Listener {
    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        event.player.world.players.forEach { player ->
            player.sendMessage("&6[&3-&6] ${event.player.displayName}".colour())
        }
        event.quitMessage = null // Take away the default (player) left the game
    }

    @EventHandler(ignoreCancelled = true)
    fun chat(event: AsyncPlayerChatEvent) {
        if (Flash.chatMuted) {
            event.isCancelled = true
            event.player.sendMessage("&cThe chat is currently muted.".prefix())
        }
    }

    @EventHandler
    fun world(event: PlayerChangedWorldEvent) {
        event.from.players.forEach { player ->
            player.sendMessage("&6[&3-&6] ${event.player.displayName}".colour())
            if (player.hasPermission("Flash.fly")) {
                player.allowFlight = true
            }
        }
        event.player.world.players.forEach { player ->
            player.sendMessage("&6[&3+&6] ${event.player.displayName}".colour())
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.player.world.players.forEach { player ->
            player.sendMessage("&6[&3+&6] ${event.player.displayName}".colour())
            if (player.hasPermission("Flash.fly")) {
                player.allowFlight = true
            }
            event.joinMessage = null
        }
    }
}
