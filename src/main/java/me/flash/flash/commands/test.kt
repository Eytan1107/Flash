package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.apache.commons.lang.time.DateUtils
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.ItemMergeEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.text.DateFormat
import java.time.Duration
import java.time.Instant
import java.time.temporal.TemporalUnit
import org.bukkit.inventory.meta.*

class Test : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = if (sender is Player) sender else sender.sendMessage(Flash.notPlayer).run { return true }
        player.inventory.addItem(ItemStack(Material.COMPASS, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6Flash Server Selector".color()
                lore = listOf("&7Click me to open the selector".color())
            }
        })
        // make it so the menu opens when the player right clicks and only in hub
        // and make it so the compass always be set to the middle and only one's
            return true
        }
    }
