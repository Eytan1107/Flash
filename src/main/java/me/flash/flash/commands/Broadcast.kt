package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit

class Broadcast : FlashCommand("broadcast|bc") {

    init {
        usage = "/broadcast <text>"
        description = "Broadcast a message to players on the server."
    }

    override fun run() {
        checkPlayer()
        if (args.isEmpty()) {
            sender.sendMessage(usage.error())
            return
        }
        Bukkit.broadcastMessage(("&c&l" + args.joinToString(" ")).prefix())
        FlashUtil.staffMessage(sender, "Broadcasted")
    }
}
