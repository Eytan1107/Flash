package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

class Build : FlashCommand("build|break"), Listener {

    init {
        description = "Toggle build on or off."
    }

    override fun run() {
        checkPlayer()
        checkPerm("flash.staff")
        checkPerm("worldguard.region.bypass.*")
        if (args.size > 1) sender.sendMessage("Too many arguments.".error())
        val player = if (args.isEmpty()) getPlayer() else getTarget(0)
        if (listOf("island_normal_world", "builds").contains(player.world.name))
            return if (player == sender)
                msg("You can't enable / disable build in this server".error())
            else
                msg("You can't enable / disable this player's build in the server they are in".error())

        if (toggled.contains(player.uniqueId))
            toggled.remove(player.uniqueId)
        else
            toggled.add(player.uniqueId)

        if (sender == player) {
            msg(("Build mode turned " + state(player)).prefix())
            return
        }
        checkPerm("flash.build.others")
        msg("Build mode turned ${state(player)} &6for ${nice()}${player.name}".prefix())
        msg(player, "Build mode turned ${state(player)} &6by ${nice()}${sender.name}".prefix())
    }

    private fun state(p: Player) : String {
        return nice() + ternary(toggled.contains(p.uniqueId), "ON", "OFF")
    }

    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        toggled.remove(event.player.uniqueId) // other players will have never been there in the first place :/
    }

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if (!listOf("island_normal_world", "builds").contains(event.player.world.name)) {
            if (event.clickedBlock !== null) {
                if (event.clickedBlock.type != Material.AIR && toggled.contains(event.player.uniqueId) && event.player.hasPermission("flash.staff") && event.player.hasPermission("worldguard.region.bypass.*")) {
                    event.isCancelled = true
                    if (!wasteOfMemory.contains(event.player.uniqueId)) if (event.player.hasPermission("flash.staff") && event.player.hasPermission("worldguard.region.bypass.*")) event.player.sendMessage("You must do &e/build &cto enable build/break".error()) else event.player.sendMessage("You must ask an admin to enable your build/break".error())
                    wasteOfMemory.add(event.player.uniqueId)
                    Bukkit.getScheduler().runTaskLater(Flash.instance, {
                        wasteOfMemory.remove(event.player.uniqueId)
                    }, 30L)
                }
            }
        }
    }

    companion object {
        val toggled = mutableListOf<UUID>()
        val wasteOfMemory = mutableListOf<UUID>()
    }
}