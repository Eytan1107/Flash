package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.usage
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Invsee : FlashCommand("invsee|inventorysee") {

    init {
        usage = "[player]"
        description = "Shows a player's inventory"
        setMinArgs(1)
    }

    override fun run() {
        checkPlayer()
        val player = getTarget(0)
        if (args.size > 1) sender.sendMessage("invsee <player>".usage()).run { return }
        if (getPlayer() == player) msg("&f[&6Flash's Server&f] &cError: You cannot open your own inventory using this command.").run { return }
        getPlayer().openInventory(player.inventory)
    }
}