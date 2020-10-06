package me.flash.flash.commands

import me.flash.flash.Flash.Companion.instance
import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.color
import me.flash.flash.FlashUtil.Companion.getConfig
import me.flash.flash.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


class Clear : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) {
                sender.sendMessage(FlashUtil.notPlayer)
                return true
            }
            if (!sender.hasPermission("flash.clear")) {
                sender.sendMessage(FlashUtil.noPermission)
                return true
            }
            sender.inventory.clear()
            sender.inventory.armorContents = arrayOfNulls(4)
            sender.sendMessage("You have cleared your inventory!".prefix())
            if (getConfig().getStringList("hub").contains(sender.world.name)) {
                sender.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6Flash's Server Selector".color()
                        lore = listOf("&7Click me to open the selector".color())
                    }
                })
            }
        } else {
            if (sender !is Player) {
                sender.sendMessage(FlashUtil.notPlayer)
                return true
            }
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(FlashUtil.targetOffline).run { return true }
            if (player == sender) {
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4)
                sender.sendMessage("You have cleared your inventory!".prefix())
                if (instance.config.getStringList("hub").contains(player.world.name)) {
                    player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&6Flash's Server Selector".color()
                            lore = listOf("&7Click me to open the selector".color())
                        }
                    })
                }
                return true
            }
            if (!sender.hasPermission("flash.clear.others")) {
                sender.sendMessage(FlashUtil.noPermission)
                return true
            }
            player.inventory.clear()
            player.inventory.armorContents = arrayOfNulls(4)
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Your inventory was cleared by &c${sender.name}".prefix()) else sender.sendMessage("Your inventory was cleared by &f${sender.name}".prefix())
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have cleared the inventory of &c${player.name}".prefix()) else sender.sendMessage("You have cleared the inventory of &l${player.name}".prefix())
            FlashUtil.staffMessage(sender, "cleared the inventory of &l${player.name}")
            if (instance.config.getStringList("hub").contains(player.world.name)) {
                player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6Flash's Server Selector".color()
                        lore = listOf("&7Click me to open the selector".color())
                    }
                })
            }
            else if (instance.config.getStringList("kitpvpworld").contains(player.world.name)) {
                player.inventory.setItem(8, ItemStack(Material.NETHER_STAR).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6Kit Menu".color()
                        lore = listOf("&7Click me to open the kits menu".color())
                    }
                })
            }
        }
        return true
    }
}
