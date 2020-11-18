package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
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
        checkPerm("flash.broadcast")
        Bukkit.broadcastMessage(("&c&l ").color())
        Bukkit.broadcastMessage(("[&6Flash's Server&f] &c&l".color() + args.joinToString(" ")).color())
        Bukkit.broadcastMessage(("&c&l ").color())
        FlashUtil.staffMessage(sender, "Broadcasted")
    }
}
