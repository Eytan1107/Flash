package me.flash.flash.listeners


import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.color
import me.flash.flash.Flash.Companion.sql
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Statistic
import org.bukkit.conversations.PlayerNamePrompt
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.*
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.plugin.java.JavaPlugin

class EventsListener : Listener {
    @EventHandler
    fun leave(event: PlayerQuitEvent) {
        event.player.world.players.forEach { players ->
            val name = event.player.name
            name.replace(
                    regex = Regex("Member"),
                    replacement = ""
            )
            players.sendMessage("&6[&3-&6] $name".color())
        }
        event.quitMessage = null // Take away the default (player) left the game
    }

    @EventHandler
    fun world(event: PlayerChangedWorldEvent) {
        val player = Bukkit.getPlayer(event.player.name)
        val name = player.name
        name.replace(
                regex = Regex("Member"),
                replacement = ""
        )
        event.from.players.forEach { players ->
            player.sendMessage("&6[&3-&6] $name".color())
            if (players.hasPermission("Flash.fly")) {
                players.allowFlight = true
                players.isFlying = true
            }
        }
        event.player.world.players.forEach { players ->
            players.sendMessage("&6[&3+&6] ${event.player.name}".color())
        }
    }

    @EventHandler
    fun join(event: PlayerJoinEvent) {
        sql.prepareStatement("insert into data(uuid) values (?)").apply {
            setString(1, event.player.uniqueId.toString())
            executeUpdate()
        }
        val playerer = Bukkit.getPlayer(event.player.name)
        if (playerer.hasPermission("flash.walkspeed")) {
            val speed = 2
            playerer.walkSpeed = speed.toFloat() / 10
        }
        if (playerer.hasPermission("flash.flyspeed")) {
            val speedfly = 1
            playerer.flySpeed = speedfly.toFloat() / 10
        }
        playerer.teleport(Bukkit.getWorld("world").spawnLocation)
        event.player.world.players.forEach { players ->
            if (players.hasPermission("Flash.fly")) {
                players.allowFlight = true
                players.isFlying = true
                val player = Bukkit.getPlayer(event.player.name)
                val name = player.name
                name.replace(
                        regex = Regex("Member"),
                        replacement = ""
                )
            }
            event.joinMessage = null
        }
    }


    @EventHandler
    fun colors(event: AsyncPlayerChatEvent) {
        if (event.player.hasPermission("flash.colors")) {
            event.message = event.message.color()
        }
    }

    @EventHandler
    fun motd(event: ServerListPingEvent) {
        event.motd = "         \u00A76\u00A7lFlash's Server \u00A7c◀ 1.8 - 1.16 ▶\u00A7r\n                  \u00A7a\u00A7lKitPvP ◊ SkyBlock"
    }

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val player = event.whoClicked
        if (Flash.instance.config.getStringList("hub").contains(player.world.name)) {
            if (event.viewers.contains(player)) event.isCancelled = true
        }
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) { // nope
        if (event.entity.killer !is Player) return
        sql.prepareStatement("update data set deaths=deaths+1 where uuid=?").apply {
            setString(1, event.entity.uniqueId.toString())
            executeUpdate()
        }
        sql.prepareStatement("update data set kills=kills+1 where uuid=?").apply {
            setString(1, event.entity.killer.uniqueId.toString())
            executeUpdate()
        }
        //event.entity.killer.uniqueId.let { uuid ->
        //        if (!Flash.sql.createStatement().executeQuery("SELECT uuid FROM main.data").equals("$uuid")) {
        //            Flash.sql.createStatement().execute("INSERT INTO main.data (uuid, deaths, kills) VALUES ('$uuid', 0, 0)")
        //        } else {
        //            Flash.sql.createStatement().execute("update data set kills = kills + 1 where uuid = '$uuid'")
        //        }
        //    }
    }
// to get
//Flash.sql.createStatement().executeQuery("select kills from data where uuid = '${event.entity.killer.uniqueId}'")

}

