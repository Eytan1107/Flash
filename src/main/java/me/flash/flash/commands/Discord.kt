package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.prefix
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Discord : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val message = TextComponent()
            TextComponent.fromLegacyText("Click ".prefix()).forEach { a->message.addExtra(a) }
            TextComponent.fromLegacyText("&bhere".colour()).forEach { a->
                a.clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "https://discord.gg/3RHYQNd")
                message.addExtra(a)
            }
            TextComponent.fromLegacyText(" &6to join the discord server").forEach { a->
                message.addExtra(a)
            }
            sender.spigot().sendMessage(message)
        } else {
            sender.sendMessage(Flash.notPlayer)
        }
        return true
    }
}
