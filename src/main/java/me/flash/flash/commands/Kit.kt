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
import org.bukkit.entity.Item
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
                            addEnchantment(Enchantment.DURABILITY, 1)
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
                    "fast" -> {
                        val timeout1 = Kits.kitpvpCooldowns[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout1.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout1).seconds + " more seconds before you can use this kit again")
                            return true
                        } else Kits.kitpvpCooldowns[sender] = Instant.now().plusSeconds(3600)
                        sender.inventory.addItem(ItemStack(Material.IRON_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 10))
                        sender.inventory.helmet = ItemStack(Material.GOLD_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.chestplate = ItemStack(Material.GOLD_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.leggings = ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.boots = ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                    }
                    "speedster" -> {
                        val timeout2 = Kits.kitpvpCooldowns[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout2.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout2).seconds + " more seconds before you can use this kit again")
                            return true
                        } else Kits.kitpvpCooldowns[sender] = Instant.now().plusSeconds(10800)
                        sender.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 2)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 16))
                        sender.inventory.helmet = ItemStack(Material.IRON_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.ARROW, 1))
                        sender.inventory.chestplate = ItemStack(Material.IRON_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.leggings = ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.boots = ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                    }
                    "godspeed" -> {
                        val timeout3 = Kits.kitpvpCooldowns[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout3.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout3).seconds + " more seconds before you can use this kit again")
                            return true
                        } else Kits.kitpvpCooldowns[sender] = Instant.now().plusSeconds(43200)
                        sender.inventory.addItem(ItemStack(Material.DIAMOND_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 20))
                        sender.inventory.addItem(ItemStack(Material.ARROW, 1))
                        sender.inventory.helmet = ItemStack(Material.DIAMOND_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.chestplate = ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.leggings = ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.boots = ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                    }
                    "speedforce" -> {
                        val timeout4 = Kits.kitpvpCooldowns[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout4.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout4).seconds + " more seconds before you can use this kit again")
                            return true
                        } else Kits.kitpvpCooldowns[sender] = Instant.now().plusSeconds(86400)
                        sender.inventory.addItem(ItemStack(Material.DIAMOND_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 3)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 2)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 32))
                        sender.inventory.addItem(ItemStack(Material.ARROW, 1))
                        sender.inventory.helmet = ItemStack(Material.DIAMOND_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.chestplate = ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.leggings = ItemStack(Material.IRON_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        }
                        sender.inventory.boots = ItemStack(Material.IRON_BOOTS).apply {
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
