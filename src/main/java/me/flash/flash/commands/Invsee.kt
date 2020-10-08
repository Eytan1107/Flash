package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil.Companion.error
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Invsee : FlashCommand("invsee|openinv|inventory|inv") {

    init {
        usage = "[player]"
        description = "Shows a player's inventory"
        setMinArgs(1)
    }

    override fun run() {
        checkPlayer()
        val player = getTarget(0)
        if (getPlayer() == player) msg("Are you stupid? Press \"E\"")
        when (player.name) {
            "FastAs_Flash", "DarrenSanders", "JGamingz", "Skeagle_" ->
                if (!hasPerm("*")) msg("You cannot sudo this player").run { return }
        }
        getPlayer().openInventory(player.inventory)
    }
}