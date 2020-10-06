package me.flash.flash

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player

class FlashUtil {

    //if you're wondering why this was moved, there will be more things than just this here in this class

    @Suppress("unused")
    companion object {
        var scEnabled = mutableListOf<Player>()
        var noPermission = "You don't have permission to do that.".error()
        var notPlayer = "You must be a player to do this.".error()
        var targetOffline = "The target player was not found.".error()
        var TooManyArgs = "Too many arguments".error() //this is retarded. get rid of it.

        fun String.prefix(): String = ("[&6Flash's Server&r] &6$this").color()
        fun String.color(): String = ChatColor.translateAlternateColorCodes('&', this)
        fun String.error(): String = ("[&6Flash's Server&r] &cError: $this").color()
        fun String.usage(): String = ("[&6Flash's Server&r] &cUsage: /$this").color()

        fun staffMessage(sender: CommandSender, action: String, vararg ignored: Player) {
            val senders = mutableListOf<CommandSender>()
            senders.addAll(Bukkit.getOnlinePlayers().filter { p -> p.hasPermission("flash.staff") })
            senders.add(Bukkit.getConsoleSender())
            senders.remove(sender)
            senders.removeAll(ignored)
            senders.forEach {
                it.sendMessage("&d[S] &5${sender.name}: &d$action".color())
            }
        }

        fun playersInWorlds(server: String): MutableList<Player> {
            val players = mutableListOf<Player>()
            for (s in Flash.instance.config.getStringList(server)) {
                players.addAll((Bukkit.getWorld(s) ?: continue).players)
            }
            return players
        }

        fun getConfig(): FileConfiguration {
            return Flash.instance.config
        }

        fun say(cs: CommandSender, s: String) {
            cs.sendMessage(s.color())
        }
    }
}