package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.notPlayer
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.utils.FlashUtil.Companion.targetOffline
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Parkour : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) sender.sendMessage("You can do /parkour help for more info".error()).run { return true }
        if (sender !is Player) sender.sendMessage(notPlayer).run { return true }
        val parkourX = Flash.instance.config.getString("parkour.location.x").removeSurrounding("[", "]")
        val parkourY = Flash.instance.config.getString("parkour.location.y").removeSurrounding("[", "]")
        val parkourZ = Flash.instance.config.getString("parkour.location.z").removeSurrounding("[", "]")
        val parkour = Location(Bukkit.getWorld("world"), parkourX.toDouble(), parkourY.toDouble(), parkourZ.toDouble())
        when (args.size) {
            1 ->
                when {
                    args.first() == "start" -> {
                        sender.sendMessage("f")
                        sender.teleport(parkour)
                    }
                    args.first() == "stop" -> {
                        sender.sendMessage("f")
                    }
                    args.first() == "checkpoint" -> {
                        sender.sendMessage("f")
                    }
                    args.first() == "set" -> {
                        if (!sender.hasPermission("flash.parkour.set")) sender.sendMessage(noPermission).run { return true }
                        Flash.instance.config.set("parkour.location.x", sender.location.x.toInt().toDouble())
                        Flash.instance.config.set("parkour.location.y", sender.location.y.toInt().toDouble())
                        Flash.instance.config.set("parkour.location.z", sender.location.z.toInt().toDouble())
                        Flash.instance.saveConfig()
                        sender.sendMessage("You have set the Parkour at &c$parkourX, $parkourY, $parkourZ".prefix())
                        return true
                    }
                    args.first() == "restart" -> {
                        sender.sendMessage("f")
                    }
                }
            2 ->
                when {
                    args.first() == "tp" || args.first() == "teleport" -> {
                        val player = Bukkit.getPlayer(args[1]) ?: sender.sendMessage(targetOffline).run { return true }
                        if (player == sender) sender.sendMessage("You have teleported &c${player.name} &6to the Parkour !".prefix())
                        else {
                            if (player.hasPermission("*")) sender.sendMessage("You can't teleport this player to the Parkour.".error()).run { return true }
                            sender.sendMessage("You have teleported &c${player.name} &6to the Parkour !".prefix())
                            player.sendMessage("&c${sender.name} &6teleported you to the Parkour !".prefix())
                        }
                        player.teleport(parkour)
                    }
                    args.first() == "reset" ->
                        when {
                            args.last() == "f" -> sender.sendMessage("f")
                            args.last() == "f" -> sender.sendMessage("f")
                            else -> {
                                sender.sendMessage("f")
                                return true
                            }
                        }
                    args.first() == "setcheckpoint" ->
                        when {
                            args.last() == "f" -> sender.sendMessage("f")
                            args.last() == "f" -> sender.sendMessage("f")
                        }
                }
        }
        return true
    }
}