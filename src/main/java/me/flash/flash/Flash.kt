package me.flash.flash

import me.flash.flash.commands.*
import me.flash.flash.listeners.EventsListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Flash : JavaPlugin() {
    override fun onEnable() {
        getCommand("feed").executor = Feed()
        getCommand("pro").executor = Pro()
        getCommand("hub").executor = Hub()
       // todo getCommand("suggest").executor = Suggest()
       // todo getCommand("suggestions").executor = Suggestions()
        getCommand("broadcast").executor = Broadcast()
        getCommand("clear").executor = Clear()
        getCommand("check").executor = Check()
        getCommand("colorlist").executor = Colorlist()
        getCommand("craft").executor = Craft()
        getCommand("discord").executor = Discord()
        getCommand("bcf").executor = F()
        getCommand("tpall").executor = TpAll()
        getCommand("loopkill").executor = Loopkill().apply { this.start() }
        getCommand("clearall").executor = ClearAll()
        getCommand("teleport").executor = Teleport()
        getCommand("silentteleport").executor = Teleport()
        getCommand("tphere").executor = TpHere()
        getCommand("silenttphere").executor = TpHere()
        getCommand("kill").executor = Kill()
        getCommand("back").executor = Back()
        getCommand("sudo").executor = Sudo()
        getCommand("fly").executor = Fly()
        getCommand("tphere").executor = TpHere()
        server.pluginManager.registerEvents(Back(), this)
        server.pluginManager.registerEvents(EventsListener(), this)
        //TODO suggestion file reading
    }

    override fun onDisable() {
        // todo Suggestion file saving
    }

    companion object {
        var noPermission = "&cYou don't have permission to do that.".prefix()
        var notPlayer = "&cYou must be a player to do this.".prefix()
        var targetOffline = "&cThe target player was not found, please check for any typos and try again.".prefix()

        fun String.prefix(): String = ("[&6Flash's Server&r] &6$this").colour()
        fun String.colour(): String = ChatColor.translateAlternateColorCodes('&', this)
        fun String.error(): String = ("[&6Flash's Server&r] &cError: $this").colour()
        fun staffMessage(staff:String, action:String) {
            Bukkit.broadcast("&d[A] &5$staff: &d$action".colour(), "flash.staff")
        }
    }
}