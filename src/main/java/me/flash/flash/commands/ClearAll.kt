package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ClearAll : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.clearall")) sender.sendMessage(Flash.noPermission).let { return true }
        if (sender !is Player) sender.sendMessage(Flash.notPlayer).let { return true }
        args.firstOrNull() ?: "".let {arg->
            if (arg != "confirm") {
                sender.spigot().sendMessage(TextComponent("Click here to continue").apply {
                    color = ChatColor.AQUA
                    clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clearall confirm")
                })
                return true
            }
        }
        sender.world.players.forEach { player->
            player.inventory.clear()
            player.inventory.armorContents = emptyArray()
            player.sendMessage("Your inventory was cleared by &c${sender.name}&r".prefix())
        }
        sender.sendMessage("You have cleared the inventory of &c${sender.world.players.size} &6players.".prefix())
        Flash.staffMessage(sender, "Cleared everyones inventory")
        return true
    }
}