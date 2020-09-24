package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Craft : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(Flash.notPlayer)
            return true
        }
        if (!sender.hasPermission("flash.craft")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        when (sender.world.name) {
            "kitpvp", "island_normal_world", "skyblock_spawn" -> {
                sender.openWorkbench(null, true)
            }
            else ->{
                if (sender.hasPermission("flash.craft.all")) {
                    sender.openWorkbench(null, true)
                } else {
                    sender.sendMessage("You must be in KitPvP or SkyBlock to do that.".error())
                }
            }
        }
        return true
    }
}
