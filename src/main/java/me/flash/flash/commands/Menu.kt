package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.event.EventHandler
import org.bukkit.inventory.*
import org.bukkit.inventory.Inventory
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryType

class Menu : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val inventory = Bukkit.createInventory(null, 27, "server selctor")
        inventory.setItem(0, ItemStack(Material.GOLD_BLOCK, 1))
        val player = Bukkit.getPlayer(sender.name)
        player.openInventory(inventory)

        return true
        }
    @EventHandler
    public fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked
        val clicked = event.currentItem
        val inventory = event.inventory
        if (inventory.getName().equals(inventory.getName())) {
            if (clicked.getType() == Material.GOLD_BLOCK) {
                event.setCancelled(true)
                player.closeInventory()
                player.getInventory().addItem(ItemStack(Material.GOLD_BLOCK, 1))
            }
        }
    }


    }
    


