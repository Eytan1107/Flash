package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.error
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Invsee : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            sender.sendMessage("Usage: /invsee <name>".error()).let { return true }
        } else if (args.isNotEmpty()) {
            val player = Bukkit.getPlayer(args[1])
            val sn = Bukkit.getPlayer(sender.name)
            if (player == sn) sender.sendMessage("Are you stupid? Press \"E\"")
            if (!sender.hasPermission("*")) if (player == Bukkit.getPlayer("FastAs_Flash") || player == Bukkit.getPlayer("DarrenSanders") || player == Bukkit.getPlayer("JGamingz")) sender.sendMessage("You cannot sudo this player").run { return true }
            sn.openInventory(player.inventory)
        }
        return true
    }
}