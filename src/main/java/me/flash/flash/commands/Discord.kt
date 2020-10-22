package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil.Companion.error
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent

class Discord : FlashCommand("discord|dc") {

    init {
    description = "Send discord link"
    }

    override fun run() {
        checkPlayer()
        if (args.isNotEmpty()) sender.sendMessage("Too many arguments".error()).run { return }
        getPlayer().spigot().sendMessage(TextComponent("Click on this message to join The Flash Discord server !").apply {
            color = ChatColor.DARK_AQUA
            isBold = true
            clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/3RHYQNd")
        })

    }
}
