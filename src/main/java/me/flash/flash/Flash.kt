package me.flash.flash

import me.flash.flash.commands.*
import me.flash.flash.listeners.EventsListener
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Flash : JavaPlugin() {
    override fun onEnable() {
        instance = this
        getCommand("kfeed").executor = Feed()
        getCommand("pro").executor = Pro()
        getCommand("kkick").executor = Kick()
        getCommand("khub").executor = Hub()
        getCommand("kbroadcast").executor = Broadcast()
        getCommand("kclear").executor = Clear()
        getCommand("kclearchat").executor = Clearchat()
        getCommand("kcheck").executor = Check()
        getCommand("kcolorlist").executor = Colorlist()
        getCommand("kcraft").executor = Craft()
        getCommand("kdiscord").executor = Discord()
        getCommand("kf").executor = F()
        getCommand("ktpall").executor = TpAll()
        getCommand("kloopkill").executor = Loopkill().apply { this.start() }
        server.pluginManager.registerEvents(EventsListener(), this)
    }

    companion object {
        var instance : Flash? = null

        var noPermission = "${ChatColor.RED}You don't have permission to do that.".prefix()
        var notPlayer = "${ChatColor.RED}You must be a player to do this.".prefix()
        var targetOffline = "${ChatColor.RED}The target player was not found, please check for any typos and try again.".prefix()

        fun String.prefix(): String = ("[&6Flash's server&r] &6$this").colour()
        fun String.colour(): String = ChatColor.translateAlternateColorCodes('&', this)
    }
}