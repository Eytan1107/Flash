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
        if (args.size == 2) {
            val player = Bukkit.getPlayer(args.last()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
            player.walkSpeed = speed + 1.toFloat()/10
            Flash.staffMessage(sender, "Set &l$player&r&d's walk speed to &l$speed".color())
            if (player !== sender) {
                player.sendMessage("&c${sender.name} set your walk speed to &c$speed".prefix())
                sender.sendMessage("Set walk speed for &c${player.name}&r &6to &c$speed".prefix())
                return true
            }
        }
        if (!IntRange(1, 10).contains(speed)) sender.sendMessage("Please specify a whole number 1 and 10.".error()).run { return true }
        sender.walkSpeed = speed + 1.toFloat()/10
        sender.sendMessage("Set walk speed for &l${sender.name} &6to $speed".prefix())
        Flash.staffMessage(sender, "Set their walk speed to $speed")
        return true
    }
}