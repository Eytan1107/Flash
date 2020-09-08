package me.flash.flash.listeners

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.plugin.java.JavaPlugin

class EventsListener : Listener {
    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        event.player.world.players.forEach { player ->
            player.sendMessage("&6[&3-&6] ${event.player.displayName}".color())
        }
        event.quitMessage = null // Take away the default (player) left the game
    }

    @EventHandler
    fun world(event: PlayerChangedWorldEvent) {
        event.from.players.forEach { player ->
            player.sendMessage("&6[&3-&6] ${event.player.displayName}".color())
            if (player.hasPermission("Flash.fly")) {
                player.allowFlight = true
                player.isFlying = true
            }
        }
        event.player.world.players.forEach { player ->
            player.sendMessage("&6[&3+&6] ${event.player.displayName}".color())
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        event.player.world.players.forEach { player ->
            player.sendMessage("&6[&3+&6] ${event.player.displayName}".color())
            if (player.hasPermission("Flash.fly")) {
                player.allowFlight = true
                player.isFlying = true
            }
            event.joinMessage = null
        }
    }
    @EventHandler
    fun colors(event: AsyncPlayerChatEvent) {
        if (event.player.hasPermission("flash.colors")) {
            event.message = event.message.color()
        }
        event.format = "%s" + "&7".color() + ": &f".color() + "%s"
    }

    @EventHandler
    fun motd(event: ServerListPingEvent) {
        //val motd = JavaPlugin.getPlugin(Flash::class.java).config.getStringList("motd")
        event.motd = "         \u00A76\u00A7lFlash's Server \u00A7c◀ 1.8 - 1.16 ▶\u00A7r\n                  \u00A7a\u00A7lKitPvP ◊ SkyBlock"
    }
    @EventHandler
    fun onInventoryClick(event:InventoryClickEvent) {
        val player = event.whoClicked
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name))  {
            if (event.viewers.contains(player))
            event.isCancelled = true
        }
    }
}
