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

class Kill : CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val parsed = Flash.parse(args)
        if (!sender.hasPermission("flash.kill")) {
            sender.sendMessage(Flash.noPermission)
            return true
        }
        if (parsed.args.size > 1) {
            sender.sendMessage("kill [player]".usage())
            return true
        }
        if (parsed.isSilent) {
            if (!sender.hasPermission("flash.silent")) {
                sender.sendMessage(Flash.noPermission)
                return true
            }
        }
        if (parsed.args.isEmpty()) {
            val sendername = Bukkit.getPlayer(sender.name)
            sendername.health = 0.0
            sender.sendMessage("You have killed yourself".prefix())
            Flash.staffMessage(sender, "Killed &l${sender.name}")
            return true
        }
        val player = Bukkit.getPlayer(parsed.args.first()) ?: sender.sendMessage(Flash.targetOffline).run { return true }
        player.health = 0.00
        if (player == sender) sender.sendMessage("You have killed yourself".prefix())
        else {
            sender.sendMessage("You have killed &c${player.name}".prefix())
        }
        if (!parsed.isSilent) Flash.staffMessage(sender, "Killed &l${player.name}").run { player.sendMessage("You have been killed by &c${sender.name}".prefix()) }
        return true
    }
}
