package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Clear : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender !is Player) {
                sender.sendMessage(Flash.notPlayer)
                return true
            }
            if (!sender.hasPermission("flash.clear")) {
                sender.sendMessage(Flash.noPermission)
                return true
            }
            sender.inventory.clear()
            sender.inventory.armorContents = arrayOfNulls(4)
            sender.sendMessage("You have cleared your inventory!".prefix())
            Flash.staffMessage(sender, "cleared their inventory")
        } else {
            if (!sender.hasPermission("flash.clear.others")) {
                sender.sendMessage(Flash.noPermission)
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player == null) {
                    sender.sendMessage(Flash.targetOffline)
                } else {
                    player.inventory.clear()
                    player.inventory.armorContents = arrayOfNulls(4)
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Your inventory was cleared by &c${sender.name}".prefix()) else sender.sendMessage("Your inventory was cleared by &f${sender.name}".prefix())
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have cleared the inventory of &c${player.name}".prefix()) else sender.sendMessage("You have cleared the inventory of &l${player.name}".prefix())
                    Flash.staffMessage(sender, "cleared the inventory of &l${player.name}")
                }
            }
        }
        return true
    }
}
