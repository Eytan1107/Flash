package me.flash.flash.listeners


import me.flash.flash.Flash
import me.flash.flash.Flash.Companion.playerdata
import me.flash.flash.commands.Menu
import me.flash.flash.commands.Players
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.player.*
import org.bukkit.event.server.ServerListPingEvent
import org.bukkit.inventory.ItemStack
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

    private fun isVanished(player: Player): Boolean {
        for (meta in player.getMetadata("vanished")) {
            if (meta.asBoolean()) return true
        }
        return false
    }

    @EventHandler
    fun world(event: PlayerChangedWorldEvent) {
        val player = Bukkit.getPlayer(event.player.name)
        val name = player.name
        name.replace(
                regex = Regex("Member"),
                replacement = ""
        )
        when (event.player.world.name) {
            "builds" -> {
                if (event.player.hasPermission("flash.gamemode") && event.player.hasPermission("worldguard.region.bypass.*")) {
                    event.player.gameMode = GameMode.CREATIVE
                } else event.player.gameMode = GameMode.SURVIVAL
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4) // we need to make a per world inventory + health + hunger bar
            }
            "world" -> {
                if (event.player.hasPermission("flash.gamemode.in.hub")) {
                    event.player.gameMode = GameMode.CREATIVE
                } else event.player.gameMode = GameMode.SURVIVAL
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4) // we need to make a per world inventory + health + hunger bar
                event.player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
                    itemMeta = itemMeta.apply {
                        displayName = "&6Flash's Server Selector".color()
                        lore = listOf("&7Click me to open the selector".color())
                    }
                })
            }
            "kitpvp" -> {
                event.player.gameMode = GameMode.SURVIVAL
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4) // we need to make a per world inventory + health + hunger bar
            }
            else -> {
                player.inventory.clear()
                player.inventory.armorContents = arrayOfNulls(4) // we need to make a per world inventory + health + hunger bar
                event.player.gameMode = GameMode.SURVIVAL
            }
        }
        event.from.players.forEach { players ->
            if (!isVanished(event.player)) {
                players.sendMessage("&6[&3-&6] ${event.player.name}".color())
            }
            if (players.hasPermission("Flash.fly")) {
                players.allowFlight = true
                players.isFlying = true
            }
        }
        event.player.world.players.forEach { players ->
            if (!isVanished(event.player)) {
                players.sendMessage("&6[&3+&6] ${event.player.name}".color())
            }
        }
    }

    @Suppress("DEPRECATION")
    @EventHandler
    fun join(event: PlayerJoinEvent) {
        if (event.player.hasPlayedBefore()) {
            event.player.sendTitle("&6Welcome Back!".color(), "&e${event.player.name}".color())
            event.player.playSound(event.player.location, Sound.NOTE_PLING, 100f, 1f)
        }
        else {
            event.player.sendTitle("Welcome!", "&e${event.player.name}".color())
            event.player.playSound(event.player.location, Sound.LEVEL_UP, 100f, 1f)
        }
        event.player.inventory.setItem(4, ItemStack(Material.COMPASS).apply {
            itemMeta = itemMeta.apply {
                displayName = "&6Flash's Server Selector".color()
                lore = listOf("&7Click me to open the selector".color())
            }
        })
        event.joinMessage = null
        event.player.teleport(Bukkit.getWorld("world").spawnLocation)
        playerdata.prepareStatement("insert into data(uuid) values (?)").apply {
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
        val motd = Flash.instance.config.getString("motd").removeSurrounding("[", "]")
        event.motd = motd
    }

    @EventHandler
    fun onPlayerDeath(event: PlayerDeathEvent) { // nope
        playerdata.prepareStatement("update data set deaths=deaths+1 where uuid=?").apply {
            setString(1, event.entity.uniqueId.toString())
            executeUpdate()
        }
        playerdata.prepareStatement("update data set kills=kills+1 where uuid=?").apply {
            setString(1, event.entity.killer.uniqueId.toString())
            executeUpdate()
        }
        event.entity.player.world.players.forEach { players ->
            if (event.entity.killer != null) {
                if (!players.hasPermission("flash.msg.nice")) players.sendMessage("&c${event.entity.player.name} &6was killed by &c${event.entity.killer.name}".prefix()) else players.sendMessage("&l${event.entity.player.name} &6was killed by &l${event.entity.killer.name}".prefix())
            }
            else {
                if (!players.hasPermission("flash.msg.nice")) players.sendMessage("&c${event.entity.player.name} &6has died".prefix()) else players.sendMessage("&l${event.entity.player.name} &6has died".prefix())
            }
            event.deathMessage = null
            if (players.world.name == "kitpvp") {
                event.entity.killer.health = (event.entity.killer.health + 3.00)
            }
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

