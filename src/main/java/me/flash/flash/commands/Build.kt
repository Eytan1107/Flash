package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import java.util.*

class Build : CommandExecutor, Listener {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.size > 1) sender.sendMessage("Too many arguments").let { return true }
        if (args.isNotEmpty()) Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).let { return true }
        if (sender !is Player) sender.sendMessage("You are Stuphead".error())
        if (sender.hasPermission("flash.staff")) {
            if (sender.hasPermission("worldguard.region.bypass.*")) {
                val player = if (args.isEmpty()) sender as Player else Bukkit.getPlayer(args.first()) ?: sender.sendMessage(targetOffline).run { return true }
                if (listOf("island_normal_world", "builds").contains(player.world.name)) {
                    return if (player == sender) {
                        sender.sendMessage("You can't enable / disable build in this server".error())
                        true
                    } else {
                        sender.sendMessage("You can't enable / disable this player's build in their server".error())
                        true
                    }
                }
                if (toggled.contains(player.uniqueId)) {
                    toggled.remove(player.uniqueId)
                    if (sender == player) if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Build mode turned &cOFF".prefix()) else sender.sendMessage("Build mode turned &lOFF".prefix())
                    else {
                        if (!sender.hasPermission("flash.build.others")) sender.sendMessage(noPermission).let { return true }
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage(("Build mode turned &cOFF &6for &c" + player.name).prefix()) else sender.sendMessage(("Build mode turned &lOFF &6for &l" + player.name).prefix())
                        if (!player.hasPermission("flash.msg.nice")) player.sendMessage(("Build mode turned &cOFF &6by &c" + sender.name).prefix()) else player.sendMessage(("Build mode turned &lOFF &6by &l" + sender.name).prefix())  // on join, if player has permissions 1 and 2 : turn off build (doesnt work in worlds island_normal_world and builds)
                    }
                } else {
                    toggled.add(player.uniqueId)
                    if (sender == player) sender.sendMessage("Build mode turned &lON".prefix())
                    else {
                        if (!sender.hasPermission("flash.build.others")) sender.sendMessage(noPermission).let { return true }
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage(("Build mode turned &aON &6for &a" + player.name).prefix()) else sender.sendMessage(("Build mode turned &lON &6for &l" + player.name).prefix())
                        if (!player.hasPermission("flash.msg.nice")) player.sendMessage(("Build mode turned &aON &6by &a" + sender.name).prefix()) else player.sendMessage(("Build mode turned &lON &6by &l" + sender.name).prefix())
                    }
                }
            }
        }
        return true
    }

    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        toggled.remove(event.player.uniqueId) // other players will have never been there in the first place :/
    }

    @EventHandler
    fun interact(event: PlayerInteractEvent) {
        if (!listOf("island_normal_world", "builds").contains(event.player.world.name)) {
            if (event.clickedBlock.type != Material.AIR && !toggled.contains(event.player.uniqueId) && event.player.hasPermission("flash.build") && event.player.hasPermission("worldguard.region.bypass.*")) {
                event.isCancelled = true
                if (!wasteOfMemory.contains(event.player.uniqueId)) if(event.player.hasPermission("flash.staff") && event.player.hasPermission("worldguard.region.bypass.*")) event.player.sendMessage("You must do &e/build &cto enable build/break".error()) else event.player.sendMessage("You must ask a high staff member to enable build/break".error())
                wasteOfMemory.add(event.player.uniqueId)
                Bukkit.getScheduler().runTaskLater(Flash.instance, {
                    wasteOfMemory.remove(event.player.uniqueId)
                }, 10L)
            }
        }
    }

    companion object {
        val toggled = mutableListOf<UUID>()
        val wasteOfMemory = mutableListOf<UUID>()
    }
}