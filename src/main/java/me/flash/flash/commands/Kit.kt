package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.getConfig
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.utils.FlashUtil.Companion.targetOffline
import me.flash.flash.utils.FlashUtil.Companion.usage
import org.apache.commons.lang.time.DurationFormatUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import java.time.Duration
import java.time.Instant

class Kit : CommandExecutor {


    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val playerer = if (sender is Player) sender else sender.sendMessage(FlashUtil.notPlayer).run { return true }
        if (getConfig().getStringList("worlds.kitpvpworld").contains(playerer.world.name)) {
            if (args.size > 2) sender.sendMessage("Too many arguments".error()).run { return true }
            if (args.size == 2) {
                if (sender.hasPermission("flash.kit.others")) {
                    val player = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(targetOffline).run { return true }
                    when (args.first()) {
                        "pvp" -> {
                            if (player == sender) {
                                sender.sendMessage("Kit PvP received !".prefix())
                            } else {
                                sender.sendMessage("Kit PvP given to ${player.name} !".prefix())
                                player.sendMessage("Kit PvP given by ${sender.name} !".prefix())
                            }
                            player.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                                addEnchantment(Enchantment.DAMAGE_ALL, 1)
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 5))
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_HELMET).apply {
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_CHESTPLATE).apply {
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                        }
                        "fast" -> {
                            if (player == sender) {
                                sender.sendMessage("Kit Fast received !".prefix())
                            } else {
                                sender.sendMessage("Kit Fast given to ${player.name} !".prefix())
                                player.sendMessage("Kit Fast given by ${sender.name} !".prefix())
                            }
                            player.inventory.addItem(ItemStack(Material.IRON_SWORD).apply {
                                addEnchantment(Enchantment.DAMAGE_ALL, 1)
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 10))
                            player.inventory.addItem(ItemStack(Material.GOLD_HELMET).apply {
                                addEnchantment(Enchantment.DURABILITY, 2)
                            })
                            player.inventory.addItem(ItemStack(Material.GOLD_CHESTPLATE).apply {
                                addEnchantment(Enchantment.DURABILITY, 2)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                        }
                        "speedster" -> {
                            if (player == sender) {
                                sender.sendMessage("Kit Speedster received !".prefix())
                            } else {
                                sender.sendMessage("Kit Speedster given to ${player.name} !".prefix())
                                player.sendMessage("Kit Speedster given by ${sender.name} !".prefix())
                            }
                            player.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                                addEnchantment(Enchantment.DAMAGE_ALL, 1)
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                                addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                                addEnchantment(Enchantment.DURABILITY, 1)
                                addEnchantment(Enchantment.ARROW_INFINITE, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 16))
                            player.inventory.addItem(ItemStack(Material.ARROW, 1))
                            player.inventory.addItem(ItemStack(Material.IRON_HELMET).apply {
                                addEnchantment(Enchantment.DURABILITY, 2)
                            })
                            player.inventory.addItem(ItemStack(Material.IRON_CHESTPLATE).apply {
                                addEnchantment(Enchantment.DURABILITY, 2)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                                addEnchantment(Enchantment.DURABILITY, 2)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                                addEnchantment(Enchantment.DURABILITY, 2)
                            })
                        }
                        "godspeed" -> {
                            if (player == sender) {
                                sender.sendMessage("Kit GodSpeed received !".prefix())
                            } else {
                                sender.sendMessage("Kit GodSpeed given to ${player.name} !".prefix())
                                player.sendMessage("Kit GodSpeed given by ${sender.name} !".prefix())
                            }
                            player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD).apply {
                                addEnchantment(Enchantment.DAMAGE_ALL, 1)
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                                addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                                addEnchantment(Enchantment.DURABILITY, 1)
                                addEnchantment(Enchantment.ARROW_INFINITE, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 20))
                            player.inventory.addItem(ItemStack(Material.ARROW, 1))
                            player.inventory.addItem(ItemStack(Material.DIAMOND_HELMET).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                            player.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                            player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                        }
                        "speedforce" -> {
                            if (player == sender) {
                                sender.sendMessage("Kit SpeedForce received !".prefix())
                            } else {
                                sender.sendMessage("Kit SpeedForce given to ${player.name} !".prefix())
                                player.sendMessage("Kit SpeedForce given by ${sender.name} !".prefix())
                            }
                            player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD).apply {
                                addEnchantment(Enchantment.DAMAGE_ALL, 3)
                                addEnchantment(Enchantment.DURABILITY, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                                addEnchantment(Enchantment.ARROW_DAMAGE, 2)
                                addEnchantment(Enchantment.DURABILITY, 1)
                                addEnchantment(Enchantment.ARROW_INFINITE, 1)
                            })
                            player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 32))
                            player.inventory.addItem(ItemStack(Material.ARROW, 1))
                            player.inventory.addItem(ItemStack(Material.DIAMOND_HELMET).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                            player.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                            player.inventory.addItem(ItemStack(Material.IRON_LEGGINGS).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                            player.inventory.addItem(ItemStack(Material.IRON_BOOTS).apply {
                                addEnchantment(Enchantment.DURABILITY, 3)
                            })
                        }
                        "menu" -> {
                            player.chat("/menu")
                        }
                        else -> {
                            sender.sendMessage("kit <kit> <player>".usage())
                            return true
                        }
                    }
                }
            } else if (args.size == 1) {
                val player = Bukkit.getPlayer(sender.name)
                when (args.first()) {
                    "pvp" -> {
                        sender.sendMessage("Kit PvP received !".prefix())
                        player.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 5))
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                    }
                    "fast" -> {
                        sender.sendMessage("Kit Fast received !".prefix())
                        player.inventory.addItem(ItemStack(Material.IRON_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 10))
                        player.inventory.addItem(ItemStack(Material.GOLD_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 2)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLD_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 2)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                    }
                    "speedster" -> {
                        sender.sendMessage("Kit Speedster received !".prefix())
                        player.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 16))
                        player.inventory.addItem(ItemStack(Material.ARROW, 1))
                        player.inventory.addItem(ItemStack(Material.IRON_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 2)
                        })
                        player.inventory.addItem(ItemStack(Material.IRON_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 2)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 2)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 2)
                        })
                    }
                    "godspeed" -> {
                        sender.sendMessage("Kit GodSpeed received !".prefix())
                        player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 20))
                        player.inventory.addItem(ItemStack(Material.ARROW, 1))
                        player.inventory.addItem(ItemStack(Material.DIAMOND_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "speedforce" -> {
                        sender.sendMessage("Kit SpeedForce received !".prefix())
                        player.inventory.addItem(ItemStack(Material.DIAMOND_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 3)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 2)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 32))
                        player.inventory.addItem(ItemStack(Material.ARROW, 1))
                        player.inventory.addItem(ItemStack(Material.DIAMOND_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.IRON_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.IRON_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "menu" -> {
                        player.chat("/menu")
                    }
                    else -> {
                        sender.sendMessage("kit <kit>".usage())
                        return true
                    }
                }
            } else {
                playerer.sendMessage("You need to be in KitPvP".error())
            }
            return true
        }
        else {
            sender.sendMessage("You need to be in KitPvP".error())
        }
        return true
    }

    private fun Duration.words() : String {
        return DurationFormatUtils.formatDurationWords(this.toMillis(), true, true)
    }
    companion object {
        val pvpCooldown = mutableMapOf<Player, Instant>()
        val fastCooldown = mutableMapOf<Player, Instant>()
        val speedsterCooldown = mutableMapOf<Player, Instant>()
        val godSpeedCooldown = mutableMapOf<Player, Instant>()
        val speedForce = mutableMapOf<Player, Instant>()
    }
}
