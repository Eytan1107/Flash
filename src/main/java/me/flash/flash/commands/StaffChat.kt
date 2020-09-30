package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.color
import me.flash.flash.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class StaffChat : CommandExecutor, Listener{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(sender !is Player) sender.sendMessage(FlashUtil.notPlayer).let { return true }
        if (!sender.hasPermission("flash.staffchat")) sender.sendMessage(FlashUtil.noPermission).let { return true }
        if (args.isEmpty()) {
            if (FlashUtil.scEnabled.contains(sender)) {
                sender.sendMessage("You have &ldisabled &6staffchat.".prefix())
                FlashUtil.scEnabled.remove(sender)
            }
            else {
                sender.sendMessage("You have &lenabled &6staffchat.".prefix())
                FlashUtil.scEnabled.add(sender)
            }
        }
        return true
    }

    @EventHandler(ignoreCancelled = true)
    fun chat(event: AsyncPlayerChatEvent) {
        if (!event.player.hasPermission("flash.staffchat")) return
        val senderprefix = Flash.vaultChat.getPlayerPrefix(event.player)
        var message = event.message
        val ov = message.startsWith("# ")
        if (FlashUtil.scEnabled.contains(event.player) || ov) {
            if (ov) message = message.replaceFirst("# ", "")
            event.isCancelled = true
            Bukkit.getOnlinePlayers().filter { player -> player.hasPermission("flash.staffchat") }.forEach {
                it.sendMessage("&b[S] ${senderprefix}${event.player.name}: &b$message".color())
            }
        }
    }
}