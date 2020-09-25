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

class Speed : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.speed")) sender.sendMessage(noPermission).run { return true }
        if (sender !is Player) sender.sendMessage(notPlayer).run { return true }
        if (args.size > 2) sender.sendMessage("&cUsage: /speed <speed> [player]".prefix()).run { return true }
        val speed = args.firstOrNull()?.toIntOrNull()
                ?: sender.sendMessage("&cUsage: /speed <speed> [player]".prefix()).run { return true }
        if (!IntRange(1, 10).contains(speed)) sender.sendMessage("Please specify a whole number 1 and 10.".error()).run { return true }
        if (args.size == 2) {
            val player = Bukkit.getPlayer(args.last()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            if (player !== sender) {
                if (!player.hasPermission("flash.msg.nice")) player.sendMessage("&c${sender.name} &6set your speed to &c$speed".prefix()) else player.sendMessage("&l${sender.name} &6set your speed to &l$speed".prefix())
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Set speed for &c${player.name} &6to &c$speed".prefix()) else sender.sendMessage("Set speed for &l${player.name} &6to &l$speed".prefix())
                Flash.staffMessage(sender, "Set &l$player&d's speed to &l$speed".color())
                player.walkSpeed = speed.toFloat().plus(1) / 11
                sender.flySpeed = speed.toFloat() / 10
                return true
            } else {
                sender.walkSpeed = speed.toFloat().plus(1) / 11
                sender.flySpeed = speed.toFloat() / 10
                if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Set speed for &c${sender.name} &6to &c$speed".prefix()) else sender.sendMessage("Set speed for &l${sender.name} &6to &l$speed".prefix())
                Flash.staffMessage(sender, "Set their speed to &l$speed")
                return true
            }
        } else {
            sender.walkSpeed = speed.toFloat().plus(1) / 11
            sender.flySpeed = speed.toFloat() / 10
            if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("Set speed for &c${sender.name} &6to &c$speed".prefix()) else sender.sendMessage("Set speed for &l${sender.name} &6to &l$speed".prefix())
            Flash.staffMessage(sender, "Set speed to &l$speed")
            return true
        }
    }
}