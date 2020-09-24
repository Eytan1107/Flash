package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.error
import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Loopkill : CommandExecutor {
    companion object {
        var tagged = ArrayList<Player>()
        val runnable = Runnable {
            tagged.forEach { player ->
                player.health = 0.00
            }
        }
    }

    fun start() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(JavaPlugin.getPlugin(Flash::class.java), runnable, 20L, 20L)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("flash.loopkill")) {
            if (args.isEmpty()) {
                sender.sendMessage("&cPlease specify a player".prefix())
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player.toString() == "FastAs_Flash" || sender.name != "DarrenSanders") sender.sendMessage("You can't loopkill Flash... Nice try.".error()).let { return true }
                if (player == null) {
                    sender.sendMessage("&cPlayer &l${args.first()}&r &cwas not found, please check for any spelling errors and try again.".color())
                } else {
                    if (tagged.contains(player)) {
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("&aNo longer loopkilling &c${player.name}".prefix()) else sender.sendMessage("&aNo longer loopkilling &l${player.name}".prefix())
                        Flash.staffMessage(sender, "loop-killing &l${player.name}")
                        tagged.remove(player)
                    } else {
                        if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("&aLoopkilling &l${player.name}".prefix()) else sender.sendMessage("&aLoopkilling &l${player.name}".prefix())
                        sender.sendMessage("".color())
                        Flash.staffMessage(sender, "stopped loop-killing &l${player.name}")
                        tagged.add(player)
                    }
                }
            }
        } else {
            sender.sendMessage(noPermission)
        }
        return true
    }
}