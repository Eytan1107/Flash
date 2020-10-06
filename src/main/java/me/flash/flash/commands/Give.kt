package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.inventory.ItemStack

class Give : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.give")) sender.sendMessage(FlashUtil.noPermission).run { return true }
        when {
            args.size > 3 -> {
                sender.sendMessage("&6&oYour command is too long, redirecting to vanilla /give.".color())
                sender.sendMessage("&6&oIf this is an error (command block), please try without spacing (commandblock)".color())
                Bukkit.dispatchCommand(sender, "minecraft:give " + args.joinToString(" "))
                return true
            }
            args.size < 2 -> {
                sender.sendMessage("&cUsage: /give [player] [item] <count>".prefix())
            }
            else -> {
                val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(FlashUtil.targetOffline).run { return true }
                val material = Material.getMaterial(args[1].toUpperCase()) ?: sender.sendMessage("That item was not found".error()).run { return true }
                val count = args.getOrElse(2) { "1" }.toIntOrNull() ?: 1

                player.inventory.addItem(ItemStack(material, count))
                FlashUtil.staffMessage(sender, "Gave &l$count &dof &l${material.name.toLowerCase().replace("_"," ")} &dto &l${player.name}")
            }
        }
        return true
    }

}
