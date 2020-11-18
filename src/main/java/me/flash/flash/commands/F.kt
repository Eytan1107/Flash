package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class F : FlashCommand("broadcastf|sudof|bcf") {

    init {
        description = "F"
    }

    private fun isVanished(player: Player): Boolean {
        for (meta in player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true
        }
        return false
    }

    override fun run() {
        checkPlayer()
        if (args.isEmpty()) {
            checkPerm("flash.f")
            if ((sender as Player).world.name == "island_normal_world" || (sender as Player).world.name == "skyblock_spawn") {
                Bukkit.getWorld("skyblock_spawn").players.filter { p -> !p.hasPermission("flash.f.no") && !isVanished(p) && p != sender}.forEach { player ->
                    player.chat("F")
                }
                Bukkit.getWorld("island_normal_world").players.filter { p -> !p.hasPermission("flash.f.no") && !isVanished(p) && p != sender}.forEach { player ->
                    player.chat("F")
                }
                FlashUtil.staffMessage(sender, "Ran the F command")
                return
            }
            (sender as Player).world.players.filter { p -> !p.hasPermission("flash.f.no") && !isVanished(p) && p != sender}.forEach { player ->
                player.chat("F")
                FlashUtil.staffMessage(sender, "Ran the F command")
            }
            return
        }
        else {
            checkPerm("flash.sudo")
            if ((sender as Player).world.name == "island_normal_world" || (sender as Player).world.name == "skyblock_spawn") {
                Bukkit.getWorld("skyblock_spawn").players.filter { p -> !p.hasPermission("flash.f.no") && !isVanished(p) && p != sender}.forEach { player ->
                    player.chat(args.joinToString(" "))
                }
                Bukkit.getWorld("island_normal_world").players.filter { p -> !p.hasPermission("flash.f.no") && !isVanished(p) && p != sender}.forEach { player ->
                    player.chat(args.joinToString(" "))
                }
                FlashUtil.staffMessage(sender, "Ran the F command")
                return
            }
            (sender as Player).world.players.filter { player -> !player.hasPermission("flash.f.no") && !isVanished(player) && player != sender}.forEach { player ->
                player.chat(args.joinToString(" "))
            }
            FlashUtil.staffMessage(sender, "Ran the sudo command on all players")
        }
    }
}
