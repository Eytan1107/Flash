package me.flash.flash.commands

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class Check : FlashCommand("check") {

    init {
        usage = "<player>"
        description = "Shows information about a player."
    }

    override fun run() {
        checkPerm("flash.check")
        if (args.isEmpty()) {
            sender.sendMessage("Please specify a player.".error())
            return
        }
        val player = Bukkit.getPlayer(args.first())?: sender.sendMessage(FlashUtil.targetOffline).run { return }
            val name: String = when (player.world.name) {
                "world" -> "Hub"
                "kitpvp" -> "KitPvP"
                "island_normal_world" -> "SkyBlock"
                "skyblock_spawn" -> "SkyBlock"
                "event" -> "Event"
                "tntrun" -> "TnTRun"
                else -> player.world.name
            }
            if (!hasPerm("flash.msg.nice")) sender.sendMessage("${player.name} is in &c$name".prefix()) else sender.sendMessage("${player.name} is in &l$name")
            sender.sendMessage("${player.name} has &c${player.health} &6health".prefix())
    }


}
