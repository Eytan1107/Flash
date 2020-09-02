package me.flash.flash.commands
// https://discord.gg/3RHYQNd
import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.prefix
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.awt.Component

class Discord : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val message = TextComponent()
            message.apply {
                TextComponent.fromLegacyText("Click ".prefix()).forEach {
                    this.addExtra(it)
                }
                TextComponent.fromLegacyText("&bhere".colour()).forEach {
                    this.addExtra(it)
                }
                TextComponent.fromLegacyText("&6 to join the discord.".colour()).forEach {
                    this.addExtra(it)
                }
            }
            sender.spigot().sendMessage(message)
        } else {
            sender.sendMessage(Flash.notPlayer)
        }
        return true
    }
}
