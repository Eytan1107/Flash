package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.HoverEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ClearAll : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.clearall")) sender.sendMessage(Flash.noPermission).let { return true }
        if (sender !is Player) sender.sendMessage(Flash.notPlayer).let { return true }
        (args.firstOrNull() ?: "").let { arg ->
            if (arg != "confirm") {
                sender.spigot().sendMessage(TextComponent("Are you sure you want to clear everyone's inventories? This is not a reversible action.").apply {
                    color = ChatColor.RED
                    isBold = true
                    clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clearall confirm")
                })
                return true
            }
            sender.world.players.forEach { player ->
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4)
                if (player != sender) player.sendMessage("Your inventory was cleared by &c${sender.name}&r".prefix())
                if (Flash.instance.config.getStringList("hub").contains(player.world.name)) {
                    player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&6Flash's Server Selector".color()
                            lore = listOf("&7Click me to open the selector".color())
                        }
                    })
                    return true
                }
            }
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have cleared the inventory of &c${sender.world.players.size} &6players.".prefix()) else sender.sendMessage("You have cleared the inventory of &l${sender.world.players.size} &6players.")
            Flash.staffMessage(sender, "Cleared every player's inventory")
            return true
        }
    }
}