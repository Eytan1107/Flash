package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.getConfig
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.usage
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class ClearAll : FlashCommand("clearall|clearinventoryall|ciall") {

    init {
        description = "Clear the inventory of everyone in your world"
    }

    override fun run() {
        checkPerm("flash.clearall")
        checkPlayer()
        if (args.size == 1 && args[0] == "confirm") {
            msg("You have cleared the inventory of ${nice()}${getPlayer().world.players.size} &6players.".prefix())
            FlashUtil.staffMessage(sender, "Cleared every player's inventory")
            getPlayer().world.players.forEach { player ->
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4)
                if (player != sender) msg(player, "Your inventory was cleared by &c${sender.name}&r".prefix())
                if (getConfig().getStringList("hub").contains(player.world.name)) {
                    player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&6Flash's Server Selector".color()
                            lore = listOf("&7Click me to open the selector".color())
                        }
                    })
                    return
                }
            }
        }
        else {
            if (args.isNotEmpty()) sender.sendMessage("clearall".usage()).run { return }
            getPlayer().spigot().sendMessage(TextComponent("Are you sure you want to clear everyone's inventories? This is not a reversible action.").apply {
                color = ChatColor.RED
                isBold = true
                clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/clearall confirm")
            })
        }
        return
    }
}