package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.color
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.Material.NOTE_BLOCK
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


/*
Make it so the player cant move stuff in the menu,
This prevents the player getting items,
And commands not working properly
 */
@Suppress("UNUSED_VARIABLE", "RedundantVisibilityModifier", "LocalVariableName")
class Rank : CommandExecutor, Listener {

    private var inventory = Bukkit.createInventory(null, 45, "&6Rank Selector".color()) // Creates the inventory
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = (sender as Player) // Creates the player value
        val target = if (args.isNotEmpty()) {
            args.first()
        } else {
            sender.name
        }
        val empty = ItemStack(Material.STAINED_GLASS_PANE, 1, 14).apply {
            itemMeta = itemMeta.apply {
                displayName = "&8[&6Flash's Server&8]&r".color()
            }
        }
        // is there even a player argument, but that;s jsut granting on yourself
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
        // LOT OF VALUES FUCK OFF
        val owner = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&4&lOwner".color()
            }
            inventory.setItem(1, this)
        }
        val developer = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&5&lDeveloper".color()
            }
            inventory.setItem(3, this)
        }
        val admin = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&c&lAdmin".color()
            }
            inventory.setItem(5, this)
        }
        val manager = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&d&lManager".color()
            }
            inventory.setItem(7, this)
        }
        val leadModerator = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&c&lLead-Moderator".color()
            }
            inventory.setItem(9, this)
        }
        val moderator = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&cModerator".color()
            }
            inventory.setItem(11, this)
        }
        val trialModerator = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&2Trial-&cModerator".color()
            }
            inventory.setItem(13, this)
        }
        val leadBuilder = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lLead-Builder".color()
            }
            inventory.setItem(15, this)
        }
        val builder = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&a&lBuilder".color()
            }
            inventory.setItem(17, this)
        }
        val trialBuilder = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&2Trial-&aBuilder".color()
            }
            inventory.setItem(19, this)
        }
        val helper = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&2Helper".color()
            }
            inventory.setItem(21, this)
        }
        val tester = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&3Tester".color()
            }
            inventory.setItem(23, this)
        }
        val friend = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&dFriend".color()
            }
            inventory.setItem(25, this)
        }
        val youtube = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6You&fTube".color()
            }
            inventory.setItem(27, this)
        }
        val twitch = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&5Streamer".color()
            }
            inventory.setItem(29, this)
        }
        val flash = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6&lFlash".color()
            }
            inventory.setItem(31, this)
        }
        val savitar = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&b&lSavitar".color()
            }
            inventory.setItem(33, this)
        }
        val reverseFlash = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&eReverse Flash".color()
            }
            inventory.setItem(35, this)
        }
        val kidFlash = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6Kid&e Flash".color()
            }
            inventory.setItem(37, this)
        }
        val zoom = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&1Zoom".color()
            }
            inventory.setItem(39, this)
        }
        val godspeed = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&bGod&fSpeed".color()
            }
            inventory.setItem(41, this)
        }
        val member = ItemStack(NOTE_BLOCK).apply {
            itemMeta = itemMeta.apply {
                displayName = "&7Member".color()
            }
            inventory.setItem(43, this)
        }
        player.openInventory(inventory)
        return true
    }




    @EventHandler
    fun cancelTheMovement(event: InventoryClickEvent) {
        val player = (event.whoClicked as Player)
        if (tagged.contains(event.inventory)) { // Creates a value of the player that clicked in the inventory
                event.isCancelled = true // Disables the option to move the items
            }
        }

    @EventHandler
    fun closeInventory(event: InventoryCloseEvent) {
        if (tagged.contains(event.inventory)) tagged.remove(event.inventory) // Unlocks the inventory only if the players had it lock and closes his inventory
    }

    companion object {
        val tagged = mutableListOf<Inventory>() // Creates the list of players that have the inventory opened
    }

    @EventHandler
    fun rankSelect(event: InventoryClickEvent): Boolean {
        if (event.inventory.toString() == "&6Rank Selector") {
            // TARGET needs to be the first args And use that to bring over data
            when {
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&4&lOwner"
                    }
                }) -> {
                    tagged.remove(event.inventory)
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&5&lDeveloper"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&4&lAdmin"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&d&lManager"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&c&lLead-Moderator"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&cModerator"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&aTrial-&cModerator"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&a&lLead-Builder"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&a&lBuilder"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&2Trial-&aBuilder"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&2Helper"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&3Tester"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&dFriend"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6You&4Tube"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&5Streamer"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6&lFlash"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&b&lSavitar"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&eReverse Flash"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6Kid&e Flash"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&1Zoom"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&bGod&fSpeed"
                    }
                }) -> {

                    tagged.remove(event.inventory)
                    // action
                }
                event.currentItem.isSimilar(ItemStack(NOTE_BLOCK).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&7Member"
                    }
                }) -> {
                    Bukkit.broadcastMessage("test")
                    tagged.remove(event.inventory)
                    // action
                }
            }
            val rank = ChatColor.stripColor(event.currentItem.itemMeta.displayName)
            if (rank == "Close") {
                event.view.close()
            } else if (rank == "[Flash's Server]") {
                event.isCancelled = true
            } else {
                sendFinalCommand(rank, event.whoClicked.name) // TARGET needs to be the first args And use that to bring over data
            }
        }
        tagged.add(event.inventory)
        return true
    }
    private fun sendFinalCommand(selectedRank: String, user: String) {
        val users = Bukkit.getPlayer(user)
        users.chat("/lp user $user group set $selectedRank")
        Bukkit.broadcastMessage("test $user $selectedRank")
    }
}


