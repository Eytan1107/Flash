package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class MuteChat : CommandExecutor, Listener {
    @EventHandler
    fun chat(event: AsyncPlayerChatEvent) {
        if (Flash.chatMuted && !event.player.hasPermission("staff.chat")) {
            event.isCancelled = true
            event.player.sendMessage("The chat is currently muted, only staff members can talk.".error())
        }
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        Flash.chatMuted = !Flash.chatMuted
        if (!sender.hasPermission("flash.mutechat")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (Flash.chatMuted) {
            Bukkit.broadcastMessage(("&cThe chat has been muted by " + sender.name).prefix())
            Flash.staffMessage(sender.name, "muted the chat")
        } else {
            Bukkit.broadcastMessage(("&aThe chat has been unnmuted by " + sender.name).prefix())
            Flash.staffMessage(sender.name, "unmuted the chat")
        }
        return true
    }
}