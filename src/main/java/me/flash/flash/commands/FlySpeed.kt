package me.flash.flash.commands


import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.notPlayer
import me.flash.flash.Flash.Companion.prefix
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
            val player = Bukkit.getPlayer(args.last()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            player.flySpeed = speed.toFloat()/10
            Flash.staffMessage(sender, "Set &l$player&r&d's flight speed to &l$speed".color())
            if (player !== sender) {
                if (!sender.hasPermission("flash.flyspeed.others")) sender.sendMessage(noPermission).run { return true }
                player.sendMessage("&c${sender.name} set your flight speed to &c$speed".prefix())
                sender.sendMessage("Set flight speed for &c${player.name}&r &6to &c$speed".prefix())
            } else {
                sender.sendMessage("Set flight speed for &c${sender.name}&r &6to &c$speed".prefix())
            }
            return true
        }
        sender.flySpeed = speed.toFloat()/10
        sender.sendMessage("Set flight speed for &c${sender.name}&r &6to &c$speed".prefix())
        Flash.staffMessage(sender, "Set their flight speed to $speed".color())
        return true
    }
}