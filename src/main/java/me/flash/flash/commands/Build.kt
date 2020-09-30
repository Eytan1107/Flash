package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.prefix
import me.flash.flash.FlashUtil.Companion.targetOffline
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
        if (args.isNotEmpty()) Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return }
        checkPerm("flash.staff")
        checkPerm("worldguard.region.bypass.*")
        val player = if (args.isEmpty()) sender as Player else Bukkit.getPlayer(args.first())
                ?: sender.sendMessage(targetOffline).run { return }
        if (listOf("island_normal_world", "builds").contains(player.world.name)) {
            return if (player == sender)
                sender.sendMessage("You can't enable / disable build in this server".error())
            else
                sender.sendMessage("You can't enable / disable this player's build in the server they are in".error())
        }
        if (toggled.contains(player.uniqueId)) {
            toggled.remove(player.uniqueId)
            if (sender == player) if (!hasPerm("flash.msg.nice")) sender.sendMessage("Build mode turned &cOFF".prefix()) else sender.sendMessage("Build mode turned &lOFF".prefix())
            else {
                checkPerm("flash.build.others")
                if (!hasPerm("flash.msg.nice")) sender.sendMessage(("Build mode turned &cOFF &6for &c" + player.name).prefix()) else sender.sendMessage(("Build mode turned &lOFF &6for &l" + player.name).prefix())
                if (!player.hasPermission("flash.msg.nice")) player.sendMessage(("Build mode turned &cOFF &6by &c" + sender.name).prefix()) else player.sendMessage(("Build mode turned &lOFF &6by &l" + sender.name).prefix())  // on join, if player has permissions 1 and 2 : turn off build (doesnt work in worlds island_normal_world and builds)
            }
        } else {
            toggled.add(player.uniqueId)
            if (sender == player) if (!hasPerm("flash.msg.nice")) sender.sendMessage("Build mode turned &aON".prefix()) else sender.sendMessage("Build mode turned &lON".prefix())
            else {
                checkPerm("flash.build.others")
                if (!hasPerm("flash.msg.nice")) sender.sendMessage(("Build mode turned &aON &6for &a" + player.name).prefix()) else sender.sendMessage(("Build mode turned &lON &6for &l" + player.name).prefix())
                if (!player.hasPermission("flash.msg.nice")) player.sendMessage(("Build mode turned &aON &6by &a" + sender.name).prefix()) else player.sendMessage(("Build mode turned &lON &6by &l" + sender.name).prefix())
            }
        }
    }

    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        toggled.remove(event.player.uniqueId) // other players will have never been there in the first place :/
    }

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if (!listOf("island_normal_world", "builds").contains(event.player.world.name)) {
            if (event.clickedBlock == null) return
            if (event.clickedBlock.type != Material.AIR && !toggled.contains(event.player.uniqueId) && event.player.hasPermission("flash.build") && event.player.hasPermission("worldguard.region.bypass.*")) {
                event.isCancelled = true
                if (!wasteOfMemory.contains(event.player.uniqueId)) if(event.player.hasPermission("flash.staff") && event.player.hasPermission("worldguard.region.bypass.*")) event.player.sendMessage("You must do &e/build &cto enable build/break".error()) else event.player.sendMessage("You must ask a high staff member to enable build/break".error())
                wasteOfMemory.add(event.player.uniqueId)
                Bukkit.getScheduler().runTaskLater(Flash.instance, {
                    wasteOfMemory.remove(event.player.uniqueId)
                }, 30L)
            }
        }
    }

    companion object {
        val toggled = mutableListOf<UUID>()
        val wasteOfMemory = mutableListOf<UUID>()
    }
}