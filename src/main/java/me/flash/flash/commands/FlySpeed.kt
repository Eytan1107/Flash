package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.notPlayer
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class FlySpeed : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage(notPlayer).run { return true }
        if (!sender.hasPermission("flash.flyspeed")) sender.sendMessage(noPermission).run { return true }
        val speed = args.firstOrNull()?.toIntOrNull() ?: sender.sendMessage("&cUsage: /flyspeed <speed> [player]".prefix()).run { return true }
        if (!IntRange(1, 10).contains(speed)) sender.sendMessage("Please specify a whole number 1 and 10.".error()).run { return true }
        if (args.size > 2) sender.sendMessage("&cUsage: /flyspeed <speed> [player]".prefix()).run { return true }
        if (args.size == 2) {
            val player = Bukkit.getPlayer(args.last()) ?: sender.sendMessage(FlashUtil.targetOffline).run { return true }
            player.flySpeed = speed.toFloat()/10
            if (player !== sender) {
                if (!sender.hasPermission("flash.flyspeed.others")) sender.sendMessage(noPermission).run { return true }
                if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&c${sender.name} set your flight speed to &c$speed".prefix()) else player.sendMessage("&l${sender.name} &6Set your flight speed to &l$speed".prefix())
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Set flight speed for &c${player.name}&r &6to &c$speed".prefix()) else sender.sendMessage("Set flight speed for &l${player.name} &6to &l$speed".prefix())
                FlashUtil.staffMessage(sender, "Set &l${player.name}&r&d's flight speed to &l$speed".color())
            } else {
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Set flight speed for &c${sender.name}&r &6to &c$speed".prefix()) else sender.sendMessage("Set flight speed for &l${sender.name} &6to &l$speed".prefix())
                FlashUtil.staffMessage(sender, "Set their flight speed to &l$speed".color())
            }
            return true
        }
        sender.flySpeed = speed.toFloat()/10
        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage(("Set flight speed for &c${sender.name}&r &6to &c$speed".prefix())) else sender.sendMessage(("Set flight speed for &l${sender.name} &6to &l$speed".prefix()))
        FlashUtil.staffMessage(sender, "Set their flight speed to &l$speed".color())
        return true
    }
}