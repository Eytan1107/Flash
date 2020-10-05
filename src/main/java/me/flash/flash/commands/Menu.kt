package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.color
import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.noPermission
import me.flash.flash.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.enchantments.Enchantment.DURABILITY
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin


@Suppress("UNUSED_VARIABLE", "RedundantVisibilityModifier", "LocalVariableName")
class Menu : CommandExecutor, Listener {
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player) // Creates a value of the player that clicked in the inventory
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name) || JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) { // Gets a list of all the players in the world
            if (tagged.contains(event.inventory)) { // Checks if the user has the inventory open
                if (event.inventory.title == "&6Server Selector") {
                    event.isCancelled = true // Disables the option to move the items
                }
                else {
                    if (player.hasPermission("flash.gamemode.in.hub")) {
                        event.isCancelled = false
                        if (event.currentItem.type == Material.COMPASS && event.currentItem.itemMeta?.displayName ?: false == "&6Flash's Server Selector".color()) {
                            event.isCancelled = true
                            return
                        }
                    }
                    else {
                        event.isCancelled = true
                    }
                }
            }
        } else if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) {
            if (tagged.contains(event.inventory)) {
                if (event.inventory.title == "&6Kit Menu") {
                    event.isCancelled = true
                }
            }
        }
        else return
    }
    // @EventHandler // doesn't work + add if player is in world "world" + if the compass's name is &6Flash's Server Selector
    //fun onDrop(event: PlayerDropItemEvent) {
        //val item = event.itemDrop
        //val player = event.player
        //if (item.equals("Material.COMPASS")) {
            //player.sendMessage("You can't drop this item")
            //event.isCancelled = true
        //}
   // }
    @EventHandler
    fun close(event: InventoryCloseEvent) {
        if (tagged.contains(event.inventory)) tagged.remove(event.inventory) // Unlocks the inventory only if the players had it lock and closes his inventory
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage("Noob").run { return true } // Returns true if the sender isn't a player, like console
        val player = Bukkit.getPlayer(sender.name)
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name)) {
            val inventory = Bukkit.createInventory(null, 27, "&6Server Selector".color()) // Creates the inventory
            val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply { // The default inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&8[&6Flash's server&8]&r".color()
                }
            }
            for (i in 0..26) {
                inventory.setItem(i, empty) // Sets the inventory slots to the default item if they dont have a used block
            }
            val skyblock = ItemStack(Material.GRASS, 1).apply { // Creates the skyblock inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&a&lSkyBlock".color()
                    lore = listOf("&7Click to teleport to SkyBlock".color(), "&7Players online: ${FlashUtil.playersInWorlds("skyblock").size}".color())
                }
                inventory.setItem(0, this) // Sets the item to the right slot
            }
            val tntrun = ItemStack(Material.TNT, 1).apply { // Creates the TntRun inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&4&lTnT Run".color()
                    lore = listOf("&7Click to teleport to &4&lTnT Run".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("tntrun").size)
                }
                inventory.setItem(8, this) // Sets the item to the right slot
            }
            val parkour = ItemStack(Material.GOLD_BOOTS, 1).apply { // Creates the Parkour inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&6&lParkour".color()
                    lore = listOf("&7Click to teleport to &6&lParkour".color())
                }
                inventory.setItem(18, this) // Sets the item to the right slot
            }
            val kitpvp = ItemStack(Material.DIAMOND_CHESTPLATE, 1).apply { // Creates the kitpvp inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&6&lKitPvP".color()
                    lore = listOf("&7Click to teleport to &9&lKitpvp".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("kitpvp").size)
                }
                inventory.setItem(13, this) // Sets the item to the right slot
            }
            val builds = ItemStack(Material.BRICK, 1).apply { // Creates the builds inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&b&lBuilds".color()
                    lore = listOf("&7Click to teleport to builds".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("builds").size)
                }
                inventory.setItem(22, this) // Sets the item to the right slot
            }
            val event = ItemStack(Material.REDSTONE, 1).apply { // Creates the event inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&a&lEvent".color()
                    lore = listOf("&7Click to teleport to &a&lEvent".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("event").size)
                }
                inventory.setItem(26, this) // Sets the item to the right slot
            }
            if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(sender.world.name)) { // Checks if the player is in the world Hub
                sender.openInventory(inventory) // Opens the inventory if the player is in the world Hub
            } else sender.sendMessage("Sorry but you can only use this in Hub".error()) // If the player isn't in the world hub send this message
            return true
        } else if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) {
            val inventory = Bukkit.createInventory(null, 27, "&6Kit Menu".color()) // Creates the inventory
            val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply { // The default inventory item
                itemMeta = itemMeta.apply {
                    displayName = "&8[&6Flash's server&8]&r".color()
                }
            }
            for (i in 0..26) {
                inventory.setItem(i, empty) // Sets the inventory slots to the default item if they dont have a used block
            }
            val pvp = ItemStack(Material.CHAINMAIL_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&7&lKit PvP".color()
                    lore = listOf("&5Click to get the PvP kit".color())
                }
                inventory.setItem(9, this)
            }
            val fast = ItemStack(Material.GOLD_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&f&lKit Fast".color()
                    lore = listOf("&5Click to get the Fast kit".color())
                }
                inventory.setItem(11, this)
            }
            val speedster = ItemStack(Material.IRON_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&d&lKit SpeedSter".color()
                    lore = listOf("&5Click to get the SpeedSter kit".color())
                }
                inventory.setItem(13, this)
            }
            val GodSpeed = ItemStack(Material.DIAMOND_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&bKit GodSpeed".color()
                    lore = listOf("&5Click to get the GodSpeed kit".color())
                }
                inventory.setItem(15, this)
            }
            val SpeedForce = ItemStack(Material.DIAMOND_CHESTPLATE, 1).apply {
                itemMeta = itemMeta.apply {
                    displayName = "&b&lKit SpeedForce".color()
                    lore = listOf("&5Click to get the SpeedForce kit".color())
                    addEnchant(DURABILITY, 3, true)
                }
                inventory.setItem(17, this)
            }
            if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(sender.world.name)) { // Checks if the player is in the world Hub
                sender.openInventory(inventory) // Opens the inventory if the player is in the world Hub
            } else sender.sendMessage("Sorry but you can only use this in Hub".error()) // If the player isn't in the world hub send this message
            return true
        }

        return true
    }

    companion object {
        val tagged = mutableListOf<Inventory>() // Creates the list of players that have the inventory opened
    }
    @EventHandler
    public fun click(event: InventoryClickEvent): Boolean {
        val player = event.whoClicked // Creates a value of the player that clicked in the inventory
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("hub").contains(player.world.name)) { // Checks if the player is in the world Hub
            if (event.currentItem.isSimilar(ItemStack(Material.GRASS).apply { // Checks if the players clicks ONLY on the Grass block
                        itemMeta = itemMeta.apply {
                            displayName = "&a&lSkyBlock".color()
                            lore = listOf("&7Click to teleport to SkyBlock".color(), "&7Players online: ${FlashUtil.playersInWorlds("skyblock").size}".color())
                        }
                    })) {
                player.sendMessage("Sending you to SkyBlock".prefix()) // ChatMessage
                player.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation) // Teleports the player to Skyblock
                tagged.remove(event.inventory) // Unlocks the inventory
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.TNT).apply { // Checks if the players clicks ONLY on the Tnt block
                        itemMeta = itemMeta.apply {
                            displayName = "&4&lTnT Run".color()
                            lore = listOf("&7Click to teleport to &4&lTnT Run".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("tntrun").size)
                        }
                    })) {
                player.sendMessage("Sending you to TnTRun".prefix()) // ChatMessage
                player.teleport(Bukkit.getWorld("tntrun").spawnLocation) // Teleports the player tp TnTRun
                tagged.remove(event.inventory) // Unlocks the inventory
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.DIAMOND_CHESTPLATE).apply { // Checks if the players clicks ONLY on the Diamond_Sword
                        itemMeta = itemMeta.apply {
                            displayName = "&6&lKitPvP".color()
                            lore = listOf("&7Click to teleport to &9&lKitpvp".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("kitpvp").size)
                        }
                    })) {
                player.sendMessage("Sending you to KitPvP".prefix()) // ChatMessage
                player.teleport(Bukkit.getWorld("kitpvp").spawnLocation) // Teleports the player to Kitpvp
                tagged.remove(event.inventory) // Unlocks the inventory
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.GOLD_BOOTS).apply { // Checks if the players clicks ONLY on the Golden_Boots
                        itemMeta = itemMeta.apply {
                            displayName = "&6&lParkour".color()
                            lore = listOf("&7Click to teleport to &6&lParkour".color())
                        }
                    })) {
                player.sendMessage("Sending you to the Parkour".prefix()) // ChatMessage
                Bukkit.dispatchCommand(player, "startparkour") // Executes the command /startparkour as the player to tp to the parkour
                tagged.remove(event.inventory) // Unlocks the inventory
                return true
            } else if (event.currentItem.isSimilar(ItemStack(Material.BRICK).apply { // Checks if the players clicks ONLY on the Brick Block
                        itemMeta = itemMeta.apply {
                            displayName = "&b&lBuilds".color()
                            lore = listOf("&7Click to teleport to builds".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("builds").size)
                        }
                    })) {
                if (player.hasPermission("flash.staff")) { // Checks if the user has the Flash.staff perm
                    player.sendMessage("Sending you to Builds".prefix()) // ChatMessage
                    player.teleport(Bukkit.getWorld("builds").spawnLocation) // Teleports the player to Builds
                    tagged.remove(event.inventory) // Unlocks the inventory
                    return true
                } else player.sendMessage(noPermission).run { return true } // Stops if the user doesn't have the right Permissions
            } else if (event.currentItem.isSimilar(ItemStack(Material.REDSTONE).apply { // Checks if the players clicks ONLY on the Redstone Dust
                        itemMeta = itemMeta.apply {
                            displayName = "&a&lEvent".color()
                            lore = listOf("&7Click to teleport to &a&lEvent".color(), "&7Players online: ".color() + FlashUtil.playersInWorlds("event").size)
                        }
                    })) {
                player.sendMessage("Sending you to Event".prefix()) // ChatMessage
                player.teleport(Bukkit.getWorld("event").spawnLocation) // Teleports the player to Event
                tagged.remove(event.inventory) // Unlocks the inventory
                return true
            }
            } else if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("kitpvpworld").contains(player.world.name)) {
                    if (event.currentItem.isSimilar(ItemStack(Material.CHAINMAIL_CHESTPLATE).apply {
                                itemMeta = itemMeta.apply {
                                    displayName = "&7&lKit PvP".color()
                                    lore = listOf("&5Click to get the PvP kit".color())
                                }
                            })) {
                        event.view.close()
                        tagged.remove(event.inventory)
                        val playerB = Bukkit.getPlayer(event.whoClicked.name)
                        playerB.chat("/kit pvp")
                        return true
                    } else if (event.currentItem.isSimilar(ItemStack(Material.GOLD_CHESTPLATE).apply {
                                itemMeta = itemMeta.apply {
                                    displayName = "&f&lKit Fast".color()
                                    lore = listOf("&5Click to get the Fast kit".color())
                                }
                            })) {
                        if (player.hasPermission("flash.fast")) {
                            event.view.close()
                            tagged.remove(event.inventory)
                            val playerB = Bukkit.getPlayer(event.whoClicked.name)
                            playerB.chat("/kit fast")
                            return true
                        } else {
                            player.sendMessage(noPermission)
                        }
                        // set the action
                        return true
                    } else if (event.currentItem.isSimilar(ItemStack(Material.IRON_CHESTPLATE).apply {
                                itemMeta = itemMeta.apply {
                                    displayName = "&d&lKit SpeedSter".color()
                                    lore = listOf("&5Click to get the SpeedSter kit".color())
                                }
                            })) {
                        if (player.hasPermission("flash.speedster")) {
                            event.view.close()
                            tagged.remove(event.inventory)
                            val playerB = Bukkit.getPlayer(event.whoClicked.name)
                            playerB.chat("/kit speedster")
                            return true
                        } else {
                            player.sendMessage(noPermission)
                        }
                        // set the action
                        return true
                    } else if (event.currentItem.isSimilar(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                                itemMeta = itemMeta.apply {
                                    displayName = "&bKit GodSpeed".color()
                                    lore = listOf("&5Click to get the GodSpeed kit".color())
                                }
                            })) {
                        if (player.hasPermission("flash.godspeed")) {
                            event.view.close()
                            tagged.remove(event.inventory)
                            val playerB = Bukkit.getPlayer(event.whoClicked.name)
                            playerB.chat("/kit godspeed")
                            return true
                        } else {
                            player.sendMessage(noPermission)
                        }
                        // set the action
                        return true
                    } else if (event.currentItem.isSimilar(ItemStack(Material.DIAMOND_CHESTPLATE).apply {
                                itemMeta = itemMeta.apply {
                                    displayName = "&b&lKit SpeedForce".color()
                                    lore = listOf("&5Click to get the SpeedForce kit".color())
                                    addEnchant(DURABILITY, 3, true)
                                }
                            })) {
                        if (player.hasPermission("flash.speedforce")) {
                            event.view.close()
                            tagged.remove(event.inventory)
                            val playerB = Bukkit.getPlayer(event.whoClicked.name)
                            playerB.chat("/kit speedforce")
                            return true
                        } else {
                            player.sendMessage(noPermission)
                        }
                        // set the action
                        return true
                    }
                    return true
                } else {
            player.sendMessage("You have to be in kitpvp to use this".error())
        }
        tagged.add(event.inventory) // Adds the player that does /menu to the inventory list.
        return true
        }
    }















    


