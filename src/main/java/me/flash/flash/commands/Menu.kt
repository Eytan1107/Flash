package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


@Suppress("INCOMPATIBLE_ENUM_COMPARISON")
class Menu : CommandExecutor, Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name)) {
            if (tagged.contains(event.inventory)) {
                event.isCancelled = true
            }
        }
    }
    @EventHandler
    fun close(event: InventoryCloseEvent) {
        if (tagged.contains(event.inventory)) tagged.remove(event.inventory)
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage("Noob").run { return true }
        val inventory = Bukkit.createInventory(null, 27, "Server selector".color())
        val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply {
            itemMeta = itemMeta.apply {
                displayName = "&8[&6Flash's server&8]&r".color()
            }
        }
        for (i in 0..26) {
            inventory.setItem(i, empty)
        }
        val skyblock = ItemStack(Material.GRASS, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lSkyBlock".color()
                lore = listOf("&7Click to teleport to SkyBlock".color(), "&7Players online: ${Flash.playersInWorlds("skyblock").size}".color())

            }
            inventory.setItem(0, this)
        }
        val tntrun = ItemStack(Material.TNT, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&4&lTnT Run".color()
                lore = listOf("&7Click to teleport to &4&lTnT Run".color(), "&7Players online: ".color() + Flash.playersInWorlds("tntrun").size)
            }
            inventory.setItem(8, this)
        }
        val parkour = ItemStack(Material.GOLD_BOOTS, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6&lParkour".color()
                lore = listOf("&7Click to teleport to &6&lParkour".color())
            }
            inventory.setItem(18, this)
        }
        val kitpvp = ItemStack(Material.DIAMOND_SWORD, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6&lKitPvP".color()
                lore = listOf("&7Click to teleport to &9&lKitpvp".color(), "&7Players online: ".color() + Flash.playersInWorlds("kitpvp").size)
            }
            inventory.setItem(13, this)
        }
        val builds = ItemStack(Material.BRICK, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&b&lBuilds".color()
                lore = listOf("&7Click to teleport to builds".color(), "&7Players online: ".color() + Flash.playersInWorlds("builds").size)
            }
            inventory.setItem(22, this)
        }
        val event = ItemStack(Material.REDSTONE, 1).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lEvent".color()
                lore = listOf("&7Click to teleport to &a&lEvent".color(), "&7Players online: ".color() + Flash.playersInWorlds("event").size)
            }
            inventory.setItem(26, this)
        }
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(sender.world.name)) sender.openInventory(inventory) else sender.sendMessage("Sorry but you can only use this in HUB".error())
        return true
    }
    companion object {
        val tagged = mutableListOf<Inventory>()
    }
    @EventHandler
    public fun click(event: InventoryClickEvent): Boolean {
        val player = event.whoClicked
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name)) {
            if (event.currentItem.isSimilar(ItemStack(Material.GRASS).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&a&lSkyBlock".color()
                            lore = listOf("&7Click to teleport to SkyBlock".color(), "&7Players online: ${Flash.playersInWorlds("skyblock").size}".color())
                        }
                    })) {
                player.sendMessage("Sending you to SkyBlock".prefix())
                player.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
                tagged.remove(event.inventory)
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.TNT).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&4&lTnT Run".color()
                            lore = listOf("&7Click to teleport to &4&lTnT Run".color(), "&7Players online: ".color() + Flash.playersInWorlds("tntrun").size)
                        }
                    })) {
                player.sendMessage("Sending you to TnTRun".prefix())
                player.teleport(Bukkit.getWorld("tntrun").spawnLocation)
                tagged.remove(event.inventory)
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.DIAMOND_SWORD).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&6&lKitPvP".color()
                            lore = listOf("&7Click to teleport to &9&lKitpvp".color(), "&7Players online: ".color() + Flash.playersInWorlds("kitpvp").size)
                        }
                    })) {
                player.sendMessage("Sending you to KitPvP".prefix())
                player.teleport(Bukkit.getWorld("kitpvp").spawnLocation)
                tagged.remove(event.inventory)
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.GOLD_BOOTS).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&6&lParkour".color()
                            lore = listOf("&7Click to teleport to &6&lParkour".color())
                        }
                    })) {
                player.sendMessage("Sending you to the Parkour".prefix())
                Bukkit.dispatchCommand(player, "/startparkour")
                tagged.remove(event.inventory)
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.BRICK).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&b&lBuilds".color()
                            lore = listOf("&7Click to teleport to builds".color(), "&7Players online: ".color() + Flash.playersInWorlds("builds").size)
                        }
                    })) {
                if (player.hasPermission("flash.staff")) {
                    player.sendMessage("Sending you to Builds".prefix())
                    player.teleport(Bukkit.getWorld("builds").spawnLocation)
                    tagged.remove(event.inventory)
                    return true
                } else player.sendMessage(Flash.noPermission).run { return true }
            } else if (event.currentItem.isSimilar(ItemStack(Material.REDSTONE).apply {
                        itemMeta = itemMeta.apply {
                            displayName = "&a&lEvent".color()
                            lore = listOf("&7Click to teleport to &a&lEvent".color(), "&7Players online: ".color() + Flash.playersInWorlds("event").size)
                        }
                    }))
                player.sendMessage("Sending you to Event".prefix())
                player.teleport(Bukkit.getWorld("event").spawnLocation)
                tagged.remove(event.inventory)
                return true
            } else return true
        }
    }
















    


