package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.prefix
import me.flash.flash.Flash.Companion.targetOffline
import me.flash.flash.Flash.Companion.usage
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityDamageEvent

class Kill : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val parsed = args.toMutableList()
        var notSilent = true
        if (sender !is Player) {
            sender.sendMessage("Do you have the stupids?".error())
            return true
        }
        if (sender.hasPermission("flash.kill").not()) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (args.isEmpty()) {
            sender.health = 0.00
            sender.sendMessage("You have killed yourself, Do you want to talk about it?".prefix())
            return true
        }
        parsed.toList().forEachIndexed { index, s ->
            if (s.matches(Regex("-s[?:ilent]?"))) {
                parsed.removeAt(index)
                notSilent = false
                return@forEachIndexed
            }
        }
        val player = Bukkit.getPlayer(parsed.first()) ?: sender.sendMessage(targetOffline).run { return true }
        player.health = 0.00
        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have killed &c${player.name}".prefix()) else sender.sendMessage("You have killed &l${player.name}".prefix())
        if (notSilent) {
            if (!player.hasPermission("flash.msg.nice")) player.sendMessage("You have been killed by &c${sender.name}".prefix()) else player.sendMessage("You have been killed by &l${sender.name}".prefix())
            Flash.staffMessage(sender,"Killed &l${player.name}", player)
        }
        return true
    }
}
