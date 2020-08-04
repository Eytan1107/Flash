package me.flash.flash

import me.flash.flash.commands.*
import me.flash.flash.listeners.EventsListener
import me.flash.flash.variables.Suggestion
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Flash : JavaPlugin() {
    override fun onEnable() {
        getCommand("feed").executor = Feed()
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
        getCommand("kclearall").executor = ClearAll()
        getCommand("kteleport").executor = Teleport()
        getCommand("ksilentteleport").executor = Teleport()
        getCommand("kenderchest").executor = EnderChest()
        getCommand("kkill").executor = Kill()
        //server.pluginManager.registerEvents(EventsListener(), this)
        Suggest.suggestions = TODO() // a file that we saved somewhere
    }

    override fun onDisable() {
        // Save it to the file again
    }

    companion object {
        var noPermission = "&cYou don't have permission to do that.".prefix()
        var notPlayer = "&cYou must be a player to do this.".prefix()
        var targetOffline = "&cThe target player was not found, please check for any typos and try again.".prefix()

        fun String.prefix(): String = ("[&6Flash's server&r] &6$this").colour()
        fun String.colour(): String = ChatColor.translateAlternateColorCodes('&', this)
        fun staffMessage(staff:String, action:String) {
            Bukkit.broadcast("&7&o[$staff: &7$action&7&o]".colour(), "skript.staff")
        }
    }
}