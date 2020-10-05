package me.flash.flash.commands

import me.flash.flash.FlashUtil.Companion.color
import me.flash.flash.FlashUtil.Companion.playersInWorlds
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Item
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPluginLoader
import java.util.function.Consumer
import kotlin.collections.forEach as forEach


/*
Make it so the player cant move stuff in the menu,
This prevents the player getting items,
And commands not working properly
 */
@Suppress("UNUSED_VARIABLE", "RedundantVisibilityModifier", "LocalVariableName")
class Rank : CommandExecutor, Listener {
    private var inventory2 = Bukkit.createInventory(null, 18, "&6Rank Selector")
    private var inventory = Bukkit.createInventory(null, 45, "&6Rank Selector".color()) // Creates the inventory
    override fun onCommand(sender: CommandSender?, command: Command?, label: String?, args: Array<out String>?): Boolean {

        val player = (sender as Player) // Creates the player value
        val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply {
            itemMeta = itemMeta.apply {
                displayName = "&8[&6Flash's Server&8]&r".color()
            }
        }
        inventory2.setItem(1/2/3/4/5/6/7/8/9, empty)
        // Create a list of empty slots
        val emptySlots = intArrayOf(0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 42, 44)
        emptySlots.forEach { slot ->
            inventory.setItem(slot, empty)

        }
        val Close = ItemStack(Material.BARRIER).apply {
            itemMeta = itemMeta.apply {
                displayName = "&c&lClose".color()
            }
            inventory.setItem(40, this)
        }
        val ranks = mutableListOf<String>("", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "")
        // LOT OF VALUES FUCK OFF
        val owner = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&4&lOwner".color()
            }
            inventory.setItem(1, this)
        }
        val developer = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&5&lDeveloper".color()
            }
            inventory.setItem(3, this)
        }
        val admin = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&c&lAdmin".color()
            }
            inventory.setItem(5, this)
        }
        val manager = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&d&lManager".color()
            }
            inventory.setItem(7, this)
        }
        val leadModerator = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&c&lLead-Moderator".color()
            }
            inventory.setItem(9, this)
        }
        val moderator = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&cModerator".color()
            }
            inventory.setItem(11, this)
        }
        val trialModerator = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&2T-&cModerator".color()
            }
            inventory.setItem(13, this)
        }
        val leadBuilder = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lLead-Builder".color()
            }
            inventory.setItem(15, this)
        }
        val builder = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lBuilder".color()
            }
            inventory.setItem(17, this)
        }
        val trialBuilder = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&2T-&aBuilder".color()
            }
            inventory.setItem(19, this)
        }
        val helper = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&2Helper".color()
            }
            inventory.setItem(21, this)
        }
        val tester = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&3Tester".color()
            }
            inventory.setItem(23, this)
        }
        val friend = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&dFriend".color()
            }
            inventory.setItem(25, this)
        }
        val youtube = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6You&fTube".color()
            }
            inventory.setItem(27, this)
        }
        val twitch = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&5Twitch".color()
            }
            inventory.setItem(29, this)
        }
        val flash = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6&lFlash".color()
            }
            inventory.setItem(31, this)
        }
        val savitar = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&b&lSavitar".color()
            }
            inventory.setItem(33, this)
        }
        val reverseFlash = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&eReverseFlash".color()
            }
            inventory.setItem(35, this)
        }
        val kidFlash = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6Kid&eFlash".color()
            }
            inventory.setItem(37, this)
        }
        val zoom = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&1Zoom".color()
            }
            inventory.setItem(39, this)
        }
        val godspeed = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&bGod&fSpeed".color()
            }
            inventory.setItem(41, this)
        }
        val member = ItemStack(Material.NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&7Member".color()
            }
            inventory.setItem(43, this)
        }
        player.openInventory(inventory)
        return true
    }

    private fun sendFinalCommand(selectedRank: String, user: String, time: String) {
        if (time.isEmpty()) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/lp user $user group set $selectedRank")
        } else {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user $user group clear")
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "/lp user $user group addtemp $selectedRank $time")
        }
    }

    @EventHandler
    fun cancelTheMovement(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player) // Creates a value of the player that clicked in the inventory
        if (tagged.contains(event.inventory)) {
            if (event.inventory.title == "&6Rank Selector") {
                event.isCancelled = true // Disables the option to move the items
            }
        }
    }

    @EventHandler
    fun closeInventory(event: InventoryCloseEvent) {
        if (tagged.contains(event.inventory)) tagged.remove(event.inventory) // Unlocks the inventory only if the players had it lock and closes his inventory
        inventory.clear()
    }

    companion object {
        val tagged = mutableListOf<Inventory>() // Creates the list of players that have the inventory opened
    }

    @EventHandler // All the different ranks
    fun rankSelect(event: InventoryClickEvent) {
        tagged.add(event.inventory)
        val player = (event.whoClicked as Player)
        when (event.currentItem.itemMeta.displayName) {
            "&4&lOwner" -> player.openInventory(inventory2)
            "&5&lDeveloper" -> player.openInventory(inventory2)
            "&c&lAdmin" -> player.openInventory(inventory2)
            "&d&lManager" -> player.openInventory(inventory2)
            "&c&lLead-Moderator" -> player.openInventory(inventory2)
            "&cModerator" -> player.openInventory(inventory2)
            "&2T-&cModerator" -> player.openInventory(inventory2)
            "&a&lLead-Builder" -> player.openInventory(inventory2)
            "&a&lBuilder" -> player.openInventory(inventory2)
            "&2T-&aBuilder" -> player.openInventory(inventory2)
            "&2Helper" -> player.openInventory(inventory2)
            "&3Tester" -> player.openInventory(inventory2)
            "&dFriend" -> player.openInventory(inventory2)
            "&6You&fTube" -> player.openInventory(inventory2)
            "&5Twitch" -> player.openInventory(inventory2)
            "&6&lFlash" -> player.openInventory(inventory2)
            "&b&lSavitar" -> player.openInventory(inventory2)
            "&eReverseFlash" -> player.openInventory(inventory2)
            "&6Kid&eFlash" -> player.openInventory(inventory2)
            "&1Zoom" -> player.openInventory(inventory2)
            "&bGod&fSpeed" -> player.openInventory(inventory2)
            "&7Member" -> player.openInventory(inventory2)
        }
    }
}

