package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand
import org.bukkit.entity.Player


class Fly : FlashCommand("fly") {

    init {
        usage = "[player]"
        description = "Toggle flying on or off"
    }

    override fun run() {
        if (args.isEmpty()) {
            checkPlayer()
            checkPerm("flash.fly")
            val player = getPlayer()
            player.allowFlight = !player.allowFlight
            msg("You turned ${state(player)} &6flight".prefix())
            FlashUtil.staffMessage(sender, ternary(player.allowFlight, "Enabled", "Disabled") + " flight for &l${player.name}")
            return
        }
        checkPerm("flash.fly.others")
        val player = getTarget(0)
        player.allowFlight = !player.allowFlight
        if (player !== sender) {
            msg("You turned ${state(player)} &6flight for ${nice()}${player.name}".prefix())
            msg(player, "${nice()}${sender.name} &6turned ${state(player)} &6your flight".prefix())
        }
        else
            msg("You turned ${state(player)} &6flight".prefix())
        FlashUtil.staffMessage(sender, ternary(player.allowFlight, "Enabled", "Disabled") + " flight for &l${player.name}")
    }

    private fun state(p: Player) : String {
        return nice() + ternary(p.allowFlight, "on", "off")
    }
}