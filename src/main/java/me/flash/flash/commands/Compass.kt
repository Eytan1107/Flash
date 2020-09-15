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
    fun world(event: PlayerChangedWorldEvent) {
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("world").contains(event.player.world.name)) {
            event.player.inventory.armorContents = emptyArray()
            event.player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&6Flash Server Selector".color()
                    lore = listOf("&7Click me to open the selector".color())
                }
            })
        }
    }

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if ((event.action.name.toLowerCase().contains("right")) && Flash.instance.config.getStringList("hub").contains(event.player.world.name)) {
            if (event.player?.itemInHand?.type?.equals(Material.COMPASS) ?: return) {
                event.player.chat("/menu")
            }
        }
    }
}
