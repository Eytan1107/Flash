package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class StaffChat : CommandExecutor, Listener{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.staff")) sender.sendMessage(Flash.noPermission).let { return true }
        if (args.isEmpty()) {
            if (Flash.scEnabled.contains(sender)) {
                sender.sendMessage("You have &aenabled &6staffchat.".prefix())
                Flash.scEnabled.minus(sender)
            }
            else {
                sender.sendMessage("You have &cdisabled &6staffchat.".prefix())
                Flash.scEnabled.plus(sender)
            }
        }
        return true
    }

    @EventHandler(ignoreCancelled = true)
    fun chat(event: AsyncPlayerChatEvent) {
        if (!event.player.hasPermission("flash.staff")) return
        var message = event.message
        val ov = message.startsWith("# ")
        if (Flash.scEnabled.contains(event.player) || ov) {
            if (ov) message = message.replaceFirst("# ", "")
            event.isCancelled = true
            Bukkit.getOnlinePlayers().filter { player -> player.hasPermission("flash.staff") }.forEach {
                it.sendMessage("&b[S] &3${event.player.name}: &b$message".colour())
            }
        }
    }
}