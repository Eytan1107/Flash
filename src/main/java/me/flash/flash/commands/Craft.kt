package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.error
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Craft : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(FlashUtil.notPlayer)
            return true
        }
        if (!sender.hasPermission("flash.craft")) {
            sender.sendMessage(FlashUtil.noPermission)
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
