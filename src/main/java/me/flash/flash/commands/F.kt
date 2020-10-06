package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit

class F : FlashCommand("broadcastf|sudof|bcf") {

    init {
        description = "F"
    }

    override fun run() {
        checkPlayer()
        checkPerm("flash.f")
        if (args.isEmpty()) {
            Bukkit.getOnlinePlayers().filter { p -> !Vanish.vanishedPlayers.contains(p) }.forEach { player ->
                player.chat("F")
                FlashUtil.staffMessage(sender, "Ran the F command")
            }
            return
        }
        Bukkit.getOnlinePlayers().forEach { player ->
            player.chat(args.joinToString(" "))
            FlashUtil.staffMessage(sender, "Ran the sudo command on all players")
        }
    }
}
