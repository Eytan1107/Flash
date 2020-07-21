package me.flash.flash.commands

import org.bukkit.*
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import java.util.ArrayList

class ExampleInventory : Listener, CommandExecutor {
    companion object{
        val tagged = ArrayList<Inventory>()
    }
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val inventory = Bukkit.createInventory(null, 27, "${ChatColor.LIGHT_PURPLE} Wow look an inventory")
            var itemStack = ItemStack(Material.STAINED_GLASS_PANE, 1, 7).apply {
                val itemMeta = this.itemMeta
                itemMeta.displayName = " "
                this.itemMeta = itemMeta
            }
            for (i in 0..26) {
                inventory.setItem(i, itemStack)
            }
            itemStack = ItemStack(Material.GRASS).apply {
                val itemMeta = this.itemMeta
                itemMeta.displayName = "${ChatColor.GREEN} Cool sapling."
                this.itemMeta = itemMeta
            }
            inventory.setItem(0, itemStack)
            tagged.add(inventory)
            sender.openInventory(inventory)
        }
        return true
    }

    @EventHandler
    fun click(event: InventoryClickEvent) {
        if (tagged.contains(event.clickedInventory)) {
            event.isCancelled = true
            if (event.slot == 0) {
                event.whoClicked.sendMessage("Wow you clicked the sapling")
                event.whoClicked.teleport(Bukkit.getWorld("skyblock_spawn").spawnLocation)
            }
        }
    }
}