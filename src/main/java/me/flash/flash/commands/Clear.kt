package me.flash.flash.commands

import me.flash.flash.Flash
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
            if (!sender.hasPermission("core.clear.self")) {
                sender.sendMessage(Flash.formatMessage("&cYou do not have permission to use this command!"))
                return true
            }
            sender.inventory.clear()
            sender.inventory.armorContents = emptyArray()
        } else {
            if (!sender.hasPermission("core.clear.others")) {
                sender.sendMessage(Flash.noPermission)
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player == null) {
                    sender.sendMessage(Flash.targetOffline)
                } else {
                    player.inventory.clear()
                    player.inventory.armorContents = emptyArray()
                    player.sendMessage(Flash.formatMessage("Your inventory was cleared by ${sender.name}"))
                    sender.sendMessage(Flash.formatMessage("You have cleared the inventory of ${player.name}"))
                }
            }
        }
        return true
    }
}
