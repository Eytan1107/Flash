package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import org.apache.commons.lang.time.DurationFormatUtils
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration
import java.time.Instant

class Kit : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val playerer = if (sender is Player) sender else sender.sendMessage(Flash.notPlayer).run { return true }
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(playerer.world.name)) {
            if (args.size > 2) sender.sendMessage("Too many arguments".error()).run { return true }
            if (args.size == 1) {
                when (args.first()) {
                    "pvp" -> {
                        val timeout = Kits.pvpCooldown[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout).words() + " before you can use this kit again")
                            return true
                        } else Kits.pvpCooldown[sender] = Instant.now().plusSeconds(10)
                        sender.sendMessage("Kit PvP received !".prefix())
                        sender.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 5))
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "fast" -> {
                        val timeout1 = Kits.fastCooldown[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout1.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout1).words() + " before you can use this kit again")
                            return true
                        } else Kits.fastCooldown[sender] = Instant.now().plusSeconds(3600)
                        sender.sendMessage("Kit Fast received !".prefix())
                        sender.inventory.addItem(ItemStack(Material.IRON_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 10))
                        sender.inventory.addItem(ItemStack(Material.GOLD_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLD_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "speedster" -> {
                        val timeout2 = Kits.speedsterCooldown[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout2.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout2).words() + " before you can use this kit again")
                            return true
                        } else Kits.speedsterCooldown[sender] = Instant.now().plusSeconds(10800)
                        sender.sendMessage("Kit Speedster received !".prefix())
                        sender.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 2)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.BOW, 1).apply {
                            addEnchantment(Enchantment.ARROW_DAMAGE, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                            addEnchantment(Enchantment.ARROW_INFINITE, 1)
                        })
                        sender.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 16))
                        sender.inventory.addItem(ItemStack(Material.ARROW, 1))
                        sender.inventory.addItem(ItemStack(Material.IRON_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.IRON_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "godspeed" -> {
                        val timeout3 = Kits.godSpeedCooldown[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout3.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout3).words() + " before you can use this kit again")
                            return true
                        } else Kits.godSpeedCooldown[sender] = Instant.now().plusSeconds(43200)
                        sender.sendMessage("Kit GodSpeed received !".prefix())
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
                        sender.inventory.addItem(ItemStack(Material.DIAMOND_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "speedforce" -> {
                        val timeout4 = Kits.speedForce[sender] ?: Instant.now().minusSeconds(1)
                        if (timeout4.isAfter(Instant.now())) {
                            sender.sendMessage("You must wait ".error() + Duration.between(Instant.now(), timeout4).words() + " before you can use this kit again")
                            return true
                        } else Kits.speedForce[sender] = Instant.now().plusSeconds(86400)
                        sender.sendMessage("Kit SpeedForce received !".prefix())
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
                        sender.inventory.addItem(ItemStack(Material.DIAMOND_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.IRON_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        sender.inventory.addItem(ItemStack(Material.IRON_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }

                }
            } else {
                val player = if (Bukkit.getPlayer(args[1]) == sender) sender else Bukkit.getPlayer(args[1]) ?: sender.sendMessage(targetOffline).run { return true }
                when (args.first()) {
                    "pvp" -> {
                        if (player == sender) {
                            sender.sendMessage("Kit PvP received !".prefix())
                        }
                        else {
                            sender.sendMessage("Kit PvP given to ${player.name} !".prefix())
                            player.sendMessage("Kit PvP given by ${sender.name} !".prefix())
                        }
                        player.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 5))
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "fast" -> {
                        if (player == sender) {
                            sender.sendMessage("Kit Fast received !".prefix())
                        }
                        else {
                            sender.sendMessage("Kit Fast given to ${player.name} !".prefix())
                            player.sendMessage("Kit Fast given by ${sender.name} !".prefix())
                        }
                        player.inventory.addItem(ItemStack(Material.IRON_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 1)
                            addEnchantment(Enchantment.DURABILITY, 1)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLDEN_APPLE, 10))
                        player.inventory.addItem(ItemStack(Material.GOLD_HELMET).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.GOLD_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "speedster" -> {
                        if (player == sender) {
                            sender.sendMessage("Kit Speedster received !".prefix())
                        }
                        else {
                            sender.sendMessage("Kit Speedster given to ${player.name} !".prefix())
                            player.sendMessage("Kit Speedster given by ${sender.name} !".prefix())
                        }
                        player.inventory.addItem(ItemStack(Material.STONE_SWORD).apply {
                            addEnchantment(Enchantment.DAMAGE_ALL, 2)
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
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.IRON_CHESTPLATE).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_LEGGINGS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                        player.inventory.addItem(ItemStack(Material.CHAINMAIL_BOOTS).apply {
                            addEnchantment(Enchantment.DURABILITY, 3)
                        })
                    }
                    "godspeed" -> {
                        if (player == sender) {
                            sender.sendMessage("Kit GodSpeed received !".prefix())
                        }
                        else {
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
                        }
                        else {
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

                }
            }
        } else {
            playerer.sendMessage("You need to be in KitPvP".error())
        }
        return true
    }

    private fun Duration.words() : String {
        return DurationFormatUtils.formatDurationWords(this.toMillis(), true, true)
    }

}
