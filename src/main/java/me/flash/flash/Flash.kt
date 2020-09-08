package me.flash.flash

import me.flash.flash.commands.*
import me.flash.flash.listeners.EventsListener
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class Flash : JavaPlugin() {
    override fun onEnable() {
        instance = this
        saveDefaultConfig()
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
        getCommand("staffchat").executor = StaffChat()
        getCommand("tphere").executor = TpHere()
        getCommand("flyspeed").executor = Flyspeed()
        getCommand("walkspeed").executor = WalkSpeed()
        getCommand("gamemode").executor = GameMode()
        getCommand("gmsp").executor = ShGameMode()
        getCommand("gma").executor = ShGameMode()
        getCommand("gms").executor = ShGameMode()
        getCommand("gmc").executor = ShGameMode()
        getCommand("give").executor = Give()
        getCommand("kit").executor = Kit()
        getCommand("kits").executor = Kits()
        getCommand("menu").executor = Menu()
        server.pluginManager.registerEvents(Back(), this)
        server.pluginManager.registerEvents(EventsListener(), this)
        server.pluginManager.registerEvents(StaffChat(), this)
        server.pluginManager.registerEvents(Menu(), this)
        //TODO suggestion file reading
    }

    override fun onDisable() {
        // todo Suggestion file saving
    }

    companion object {
        lateinit var instance : Flash
        var scEnabled = mutableListOf<Player>()
        var noPermission = "You don't have permission to do that.".error()
        var notPlayer = "You must be a player to do this.".error()
        var targetOffline = "The target player was not found, please check for any typos and try again.".error()

        fun String.prefix(): String = ("[&6Flash's Server&r] &6$this").color()
        fun String.color(): String = ChatColor.translateAlternateColorCodes('&', this)
        fun String.error(): String = ("[&6Flash's Server&r] &cError: $this").color()

        fun staffMessage(sender:CommandSender, action: String, vararg ignored: Player) {
            val senders = mutableListOf<CommandSender>()
            senders.addAll(Bukkit.getOnlinePlayers().filter { p -> p.hasPermission("flash.staff") })
            senders.add(Bukkit.getConsoleSender())
            senders.remove(sender)
            senders.removeAll(ignored)
            senders.forEach {
                it.sendMessage("&d[S] &5${sender.name}: &d$action".color())
            }
        }
    }
}
