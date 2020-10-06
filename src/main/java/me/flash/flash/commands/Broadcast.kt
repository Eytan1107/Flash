package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit

class Broadcast : FlashCommand("broadcast|bc") {

    init {
        usage = "<text>"
        description = "Broadcast a message to players on the server."
        setMinArgs(1)
    }

    override fun run() {
        checkPlayer()
        Bukkit.broadcastMessage(("&c&l" + args.joinToString(" ")).prefix())
        FlashUtil.staffMessage(sender, "Broadcasted")
    }
}
