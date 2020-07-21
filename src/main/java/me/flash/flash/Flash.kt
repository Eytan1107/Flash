package me.flash.flash

import me.flash.flash.commands.*
import me.flash.flash.listeners.EventsListener
import org.bukkit.ChatColor
import org.bukkit.plugin.java.JavaPlugin

class Flash : JavaPlugin() {
    override fun onEnable() {
        instance = this
        getCommand("feed").executor = Feed()
        getCommand("pro").executor = Pro()
        getCommand("kkick").executor = KKick()
        getCommand("inventory").executor = ExampleInventory()
        getCommand("khub").executor = Hub()
        getCommand("KLoopKill").executor = KLoopKill().apply { this.start() }
        server.pluginManager.registerEvents(EventsListener(), this)
        server.pluginManager.registerEvents(ExampleInventory(), this)
    }

    companion object {
        @JvmField
        var noPermissionMessage = "[" + ChatColor.GOLD + "Flash's Server" + ChatColor.WHITE + "]" + ChatColor.RED + " You don't have permission to do that."
        var instance : Flash? = null
        @JvmStatic
        fun formatMessage(text: String): String {
            return ChatColor.translateAlternateColorCodes('&', "[&6Flash's server&r] &6$text")
        }
    }
}