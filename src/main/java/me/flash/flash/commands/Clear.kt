package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.getConfig
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class Clear : FlashCommand("clear|ci|clearinventory|cl") {

    init {
        usage = "[player]"
        description = "Clear a player's inventory"
    }

    override fun run() {
        if (args.isEmpty()) {
            checkPlayer()
            checkPerm("flash.clear")
            val player = getPlayer()
            clearInv(player)
            return
        }

        val player = getTarget(0)
        if (player == sender) {
            clearInv(player)
            return
        }
        checkPerm("flash.clear.others")
        player.inventory.clear()
        player.inventory.armorContents = arrayOfNulls(4)
        msg(player, "Your inventory was cleared by ${nice()}${sender.name}".prefix())
        msg(player, "You have cleared the inventory of ${nice()}${player.name}".prefix())
        FlashUtil.staffMessage(sender, "cleared the inventory of &l${player.name}")
        if (getConfig().getStringList("worlds.hub").contains(player.world.name))
            player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&6Flash's Server Selector".color()
                    lore = listOf("&7Click me to open the selector".color())
                }
            })
    }

    private fun clearInv(p: Player) {
        p.inventory.clear()
        p.inventory.armorContents = arrayOfNulls(4)
        msg(p, "You have cleared your inventory!".prefix())
        if (getConfig().getStringList("worlds.hub").contains(p.world.name))
            p.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&6Flash's Server Selector".color()
                    lore = listOf("&7Click me to open the selector".color())
                }
            })
    }
}
