package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.colour
import me.flash.flash.Flash.Companion.error
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack

class Give : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.give")) sender.sendMessage(Flash.noPermission).run { return true }
        if (args.size > 3) {
            sender.sendMessage("&6&oYour command is too long, redirecting to vanilla /give.".colour())
            sender.sendMessage("&6&oIf this is an error (command block), please try without spacing (commandblock)".colour())
            Bukkit.dispatchCommand(sender, "/minecraft:give " + args.joinToString(" "))
            return true
        }
        else if (args.size < 2) {
            sender.sendMessage("&cUsage: /give [player] [item] <count>")
        }
        else {
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            val material = Material.getMaterial(args[1]) ?: sender.sendMessage("That item was not found".error()).run { return true }
            val count = args[2].toIntOrNull()
            player.inventory.addItem(ItemStack(material, count?:1))
            Flash.staffMessage(sender.name, "Gave ${count ?: 1} of ${material.name.toLowerCase().replace("_"," ")} to ${player.name}")
        }
        return true
    }

}
