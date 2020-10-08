package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Hub : FlashCommand("hub|lobby") {

    init {
        usage = "[player]"
        description = "send a player to the hub"
    }

    override fun run() {
        if (args.isEmpty()) {
            checkPlayer()
            if (getPlayer().world.name != "world") {
                getPlayer().teleport(Bukkit.getWorld("world").spawnLocation)
                msg("Teleporting to you to Hub...".prefix())
            } else
                msg("You are already in Hub, do &e/spawn &cto return to spawn!".error())
            return
        }
        checkPerm("flash.hub.others")
        val player = getTarget(0)
        if (getPlayer() == player) {
            if (player.world.name == "world") msg("You are already in Hub, do &e/spawn &cto return to spawn!".error()).run { return }
            msg("Teleporting you to Hub...".prefix())
        } else {
            if (player.world.name == "world") msg("This player is already in Hub, do &e/spawn <player> &cto teleport them back to spawn!".error()).run { return }
            msg("Teleporting &c${player.name} &6to Hub...".prefix())
            msg(player, "&c${player.name} &6teleported you to Hub !".prefix())
        }
        player.teleport(Bukkit.getWorld("world").spawnLocation)
    }
}