package me.flash.flash.commands
// https://discord.gg/3RHYQNd
import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.prefix
import net.md_5.bungee.api.ChatColor
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
            sender.spigot().sendMessage(TextComponent("Click here to join our Discord server !").apply {
                color = ChatColor.DARK_AQUA
                clickEvent = ClickEvent(ClickEvent.Action.OPEN_URL, "http://adfoc.us/46924976102034")
            })
        } else {
            sender.sendMessage(Flash.notPlayer)
        }
        return true
    }
}
