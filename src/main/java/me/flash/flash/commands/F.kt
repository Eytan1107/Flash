package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class F : FlashCommand("broadcastf|sudof|bcf") {

    init {
        description = "F"
    }

    override fun run() {
        checkPlayer()
        if (args.isEmpty()) {
            checkPerm("flash.f")
            (sender as Player).world.players.filter { p -> !Vanish.vanishedPlayers.contains(p) && !p.hasPermission("*") }.forEach { player ->
                player.chat("F")
                FlashUtil.staffMessage(sender, "Ran the F command")
            }
            return
        }
        else {
            checkPerm("flash.sudo")
            (sender as Player).world.players.filter { player -> !player.hasPermission("*") }.forEach { player ->
                player.chat(args.joinToString(" "))
            }
            FlashUtil.staffMessage(sender, "Ran the sudo command on all players")
        }
    }
}
