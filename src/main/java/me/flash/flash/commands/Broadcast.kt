package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

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
        Flash.staffMessage(sender, "Broadcasted")
    }
}
