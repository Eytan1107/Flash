package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.util.concurrent.TimeoutException

class Compass : Listener {
    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if ((event.action.name.toLowerCase().contains("right")) && Flash.instance.config.getStringList("hub").contains(event.player.world.name)) {
            if (event.player?.itemInHand?.type == Material.COMPASS && event.player?.itemInHand?.itemMeta?.displayName ?: false == "&6Flash's Server Selector".color()) {
                event.player.chat("/menu")
            }
        }
    }
}
