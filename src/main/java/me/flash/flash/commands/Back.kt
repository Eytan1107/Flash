package me.flash.flash.commands

import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerTeleportEvent

class Back : FlashCommand("back"), Listener {
    companion object {
        var table = mutableMapOf<Player, Location>()
    }

    init {
        description = "Go back to your previous location."
    }

    override fun run() {
        checkPlayer()
        checkPerm("flash.back")
        val player = getPlayer();
        table[player].apply {
            if (this != null) {
                player.teleport(this)
                player.sendMessage("You were teleported to your last known location".prefix())
                return
            }
            player.sendMessage("You do not have a last known location".error())
        }
    }
//doesn't work, it says: sender cannot be null


    @EventHandler(ignoreCancelled = true)
    fun teleport(event: PlayerTeleportEvent) {
        table[event.player] = event.from
    }

    @EventHandler(ignoreCancelled = true)
    fun death(event: EntityDamageEvent) {
        val player = if (event.entity is Player) event.entity as Player else return
        if ((player.health - event.damage) <= 0)
            table[player] = player.location
    }
}
