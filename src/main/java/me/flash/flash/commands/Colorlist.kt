package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.prefix
import me.flash.flash.commands.api.FlashCommand

class Colorlist : FlashCommand("colorlist|cll|colorslist") {

    init {
        description = "Display all colors available"
    }

    override fun run() {
        checkPerm("flash.colorslist")
        msg("Here is a list of all the colors available in minecraft:".prefix())
        msg("&1&&11&r, &2&&22&r, &3&&33&r, &4&&44&r, &5&&55&r ,&6&&66&r ,&7&&77&r ,&8&&88&r ,&9&&99&r ,&0&&00&r ,&d&&dd&r ,&e&&ee&r ,&f&&ff&r ,&&fk&kg&r ,&l&&ll&r, &m&&mm&r, &n&&nn&r, &o&&oo&r, &&rr (reset)".color())
    }
}
