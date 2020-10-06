package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.utils.FlashUtil.Companion.usage
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Wake : CommandExecutor{
    @Suppress("DEPRECATION")
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (args.isEmpty()) {
                sender.sendMessage("wake <player> <message>".usage())
            }
            val player = Bukkit.getPlayer(args.first()) ?: sender.sendMessage(FlashUtil.targetOffline).run { return true }
            player.sendTitle("&c".color() + sender.name, "&7Is requesting your attention".color())
            args.toMutableList().apply { removeAt(0) }.let {
                player.sendMessage("&7---------------------".color())
                player.sendMessage(("&c&l" + sender.name + " is requesting your attention.").color())
                player.sendMessage(("&7Message: " + it.joinToString(" ")).color())
                player.sendMessage("&7---------------------".color())
                sender.sendMessage("Wake message sent to &c${player.name}".prefix())
            }
            Bukkit.getScheduler().runTaskAsynchronously(Flash.instance) {
                for (i in 1..5) {
                    player.playSound(player.location, Sound.NOTE_PLING, 100f, 1f)
                    Thread.sleep(200)
                }
            }
        } else {
            sender.sendMessage(FlashUtil.notPlayer)
        }
        return true
    }
}