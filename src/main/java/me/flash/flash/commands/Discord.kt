package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent

class Discord : FlashCommand("discord|dc") {

    override fun run() {
        checkPlayer()
        getPlayer().spigot().sendMessage(TextComponent("Click here to join our Discord server !").apply {
            color = ChatColor.DARK_AQUA
            clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/3RHYQNd")
        })

    }
}
