package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand

class Enderchest : FlashCommand("enderchest|ec") {

    init {
        usage = "[player]"
        description = "Open a player's enderchest"
    }

    override fun run() {
        checkPlayer()
        if (args.isEmpty()) {
            checkPerm("flash.enderchest")
            getPlayer().openInventory(getPlayer().enderChest)
            return
        }
        checkPerm("flash.enderchest.others")
        getPlayer().openInventory(getTarget(0).enderChest)
    }
}


