package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil.Companion.color
import org.bukkit.Bukkit

class Broadcast : FlashCommand("broadcast|bc") {

    init {
        usage = "<text>"
        description = "Broadcast a message to players on the server."
        setMinArgs(1)
    }

    override fun run() {
        Bukkit.broadcastMessage(("&c&l ").prefix())
        Bukkit.broadcastMessage(("&c&l" + args.joinToString(" ")).prefix())
        Bukkit.broadcastMessage(("&c&l ").prefix())
        FlashUtil.staffMessage(sender, "Broadcasted")
    }
}
