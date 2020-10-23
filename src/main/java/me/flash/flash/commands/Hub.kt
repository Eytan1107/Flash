package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Location

class Hub : FlashCommand("hub|lobby|l") {

    init {
        usage = "[player]"
        description = "send a player to the hub"
    }

    override fun run() {
        val hubX = Flash.instance.config.getString("hub.location.x").removeSurrounding("[", "]")
        val hubY = Flash.instance.config.getString("hub.location.y").removeSurrounding("[", "]")
        val hubZ = Flash.instance.config.getString("hub.location.z").removeSurrounding("[", "]")
        val hub = Location(Bukkit.getWorld("world"), hubX.toDouble(), hubY.toDouble(), hubZ.toDouble())
        if (args.isEmpty()) {
            checkPlayer()
            if (getPlayer().world.name != "world") {
                getPlayer().teleport(hub)
                msg("Teleporting to you to &cHub&6...".prefix())
            } else
                msg("You are already in Hub, do &e/spawn &cto return to spawn!".error())
            return
        }
        checkPerm("flash.hub.others")
        val player = getTarget(0)
        if (getPlayer() == player) {
            if (player.world.name == "world") msg("You are already in Hub, do &e/spawn &cto return to spawn!".error()).run { return }
            msg("Teleporting you to &cHub&6...".prefix())
        } else {
            if (player.world.name == "world") msg("This player is already in Hub, do &e/spawn <player> &cto teleport them back to spawn!".error()).run { return }
            msg("Teleporting &c${player.name} &6to &cHub&6...".prefix())
            msg(player, "&c${player.name} &6teleported you to &cHub !".prefix())
        }
        player.teleport(hub)
    }
}