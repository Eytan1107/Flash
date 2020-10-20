package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.noPermission
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

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
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Flash.instance, runnable, 20L, 20L)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender.hasPermission("flash.loopkill")) {
            if (args.isEmpty()) {
                sender.sendMessage("&cPlease specify a player".prefix())
            } else {
                val player = Bukkit.getPlayer(args.first())
                if (player == Bukkit.getPlayer("FastAs_Flash")) sender.sendMessage("You can't loopkill Flash... Nice try.".error()).let { return true }
                if (player == null) {
                    sender.sendMessage("&cPlayer &l${args.first()}&r&c was not found, please check for any spelling errors and try again.".error())
                } else {
                    if (tagged.contains(player)) {
                        sender.sendMessage("&aNo longer loopkilling &c${player.name}".prefix())
                        FlashUtil.staffMessage(sender, "loop-killing &l${player.name}")
                        tagged.remove(player)
                    } else {
                        sender.sendMessage("&aLoopkilling &c${player.name}".prefix())
                        FlashUtil.staffMessage(sender, "stopped loop-killing &l${player.name}")
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