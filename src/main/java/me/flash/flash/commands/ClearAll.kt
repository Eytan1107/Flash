package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ClearAll : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.owner")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (sender !is Player) {
            sender.sendMessage(Flash.notPlayer)
            return true
        }
        sender.world.players.forEach { player->
            player.inventory.clear()
            player.inventory.armorContents = emptyArray()
            player.sendMessage("Your inventory was cleared by ${sender.name}".prefix())
        }
        sender.sendMessage("You have cleared the inventory of ${sender.world.players.size} players.")
        return true
    }
}