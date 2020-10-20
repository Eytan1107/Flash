package me.flash.flash.listeners

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.color
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent


class Compass : Listener {
    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if ((event.action.name.toLowerCase().contains("right")) && Flash.instance.config.getStringList("hub").contains(event.player.world.name)) {
            if (event.player?.itemInHand?.type == Material.COMPASS && event.player?.itemInHand?.itemMeta?.displayName ?: false == "&6Flash's Server Selector".color()) {
                event.player.chat("/menu")
                return
            }
        }
    }
}
