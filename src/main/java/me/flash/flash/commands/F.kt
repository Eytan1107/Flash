package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class F : FlashCommand("broadcastf|sudof|bcf") {

    init {
        description = "F"
    }

    private fun isVanished(player: Player): Boolean {
        for (meta in player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true
        }
        return false
    }

    override fun run() {
        checkPlayer()
        if (args.isEmpty()) {
            checkPerm("flash.f")
            (sender as Player).world.players.filter { p -> !isVanished(sender as Player) || !p.hasPermission("*") || p != sender}.forEach { player ->
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
