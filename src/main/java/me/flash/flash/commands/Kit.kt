package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.apache.commons.lang.time.DateUtils
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.text.DateFormat
import java.time.Duration
import java.time.Instant
import java.time.temporal.TemporalUnit

class Kit : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = if (sender is Player) sender else sender.sendMessage(Flash.notPlayer).run { return true }
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) {
            if (args.size == 1) {
                when (args.first()) {
                    "pvp" -> {
                        val timeout = Kits.kitpvpCooldowns[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout).seconds + " more seconds before you can use this kit again")
                            return true
                        } else Kits.kitpvpCooldowns[sender] = Instant.now().plusSeconds(10)
                        sender.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 5))
                        sender.inventory.helmet = ItemStack(Material.CHAINMAIL_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.chestplate = ItemStack(Material.CHAINMAIL_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.leggings = ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.boots = ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                    }
                }
            }
        } else {
            player.sendMessage("You need to be in KitPvP".error())
        }
        return true
    }

    private fun Duration.seconds(): Long {
        return this.seconds % 60
    }

    private fun Duration.minutes(): Long {
        return this.seconds / 60
    }

}
