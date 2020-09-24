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

class WalkSpeed : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (!sender.hasPermission("flash.walkspeed")) sender.sendMessage(noPermission).run { return true }
        if (sender !is Player) sender.sendMessage(notPlayer).run { return true }
        if (args.size > 2) sender.sendMessage("&cUsage: /walkspeed <speed> [player]".prefix()).run { return true }
        val speed = args.firstOrNull()?.toIntOrNull() ?: sender.sendMessage("&cUsage: /walkspeed <speed> [player]".prefix()).run { return true }
        if (!IntRange(1, 15).contains(speed)) sender.sendMessage("Please specify a whole number 1 and 15.".error()).run { return true }
        if (args.size == 2) {
            val player = Bukkit.getPlayer(args.last()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            if (player !== sender) {
                player.sendMessage("&l${sender.name} &6set your walk speed to &c$speed".prefix())
                sender.sendMessage("Set walk speed for &c${player.name} &6to &c$speed".prefix())
                Flash.staffMessage(sender, "Set &l$player&d's walk speed to &l$speed".color())
                player.walkSpeed = speed.toFloat().plus(2) / 17
                return true
            } else {
                sender.walkSpeed = speed.toFloat().plus(2) / 17
                sender.sendMessage("Set walk speed for &l${sender.name} &6to &l$speed".prefix())
                Flash.staffMessage(sender, "Set their walk speed to &l$speed")
                return true
            }
        } else {
            sender.walkSpeed = speed.toFloat().plus(2) / 17
            sender.sendMessage("Set walk speed for &c${sender.name} &6to &c$speed".prefix())
            Flash.staffMessage(sender, "Set their walk speed to &l$speed")
            return true
        }
    }
}