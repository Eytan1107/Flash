package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit

class Check : FlashCommand("check") {

    init {
        usage = "<player>"
        description = "Shows information about a player."
        setMinArgs(1)
    }

    override fun run() {
        checkPerm("flash.check")
        val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(FlashUtil.targetOffline).run { return }
        val name: String = when (player.world.name) {
            "world" -> "Hub"
            "kitpvp" -> "KitPvP"
            "island_normal_world" -> "SkyBlock"
            "skyblock_spawn" -> "SkyBlock"
            "event" -> "Event"
            "tntrun" -> "TnTRun"
            else -> player.world.name
        }
        msg("${player.name} is in ${nice()}$name".prefix())
        msg("${player.name} has ${nice()}${player.health} &6health".prefix())
        msg("${player.name} is in gamemode ${nice()}${player.gameMode}".prefix())
    }


}
