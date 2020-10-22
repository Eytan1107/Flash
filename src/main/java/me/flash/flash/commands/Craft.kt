package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.commands.api.FlashCommand

class Craft : FlashCommand("craft") {

    init {
        description = "Open a virtual crafting table"
    }

    override fun run() {
        checkPlayer()
        checkPerm("flash.craft")
        if (args.isNotEmpty()) sender.sendMessage("Too many arguments".error()).run { return }
        when (getPlayer().world.name) {
            "kitpvp", "island_normal_world", "skyblock_spawn" -> getPlayer().openWorkbench(null, true)
            else -> if (hasPerm("flash.craft.all")) getPlayer().openWorkbench(null, true) else msg("You must be in KitPvP or SkyBlock to do that.".error())
        }
    }
}
