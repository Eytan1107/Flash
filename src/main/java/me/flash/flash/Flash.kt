package me.flash.flash

import com.avaje.ebeaninternal.server.lib.thread.ThreadPoolManager
import me.flash.flash.commands.*
import me.flash.flash.listeners.EventsListener
import me.flash.flash.variables.ParsedArgsSilent
import net.milkbowl.vault.chat.Chat
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.sqlite.JDBC
import java.io.File
import java.sql.Connection
import java.sql.DriverManager
import java.util.concurrent.Executors

class Flash : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        Class.forName("org.sqlite.JDBC")
        DriverManager.registerDriver(JDBC())
        playerdata = DriverManager.getConnection("jdbc:sqlite:" + File(dataFolder, "playerdata.db").absolutePath)
        suggestionsdb = DriverManager.getConnection("jdbc:sqlite:" + File(dataFolder, "suggestions.db").absolutePath)
        instance = this
        playerdata.prepareStatement("create table if not exists data(uuid varchar(48), kills int default(0), deaths int default(0), time int default(0), primary key(uuid));").executeUpdate()
        playerdata.autoCommit = true
        suggestionsdb.prepareStatement("create table if not exists suggestion(uuid varchar(48), text varchar(256));").executeUpdate()
        suggestionsdb.autoCommit = true
        try {
            vaultChat = server.servicesManager.getRegistration(Chat::class.java).provider
        } catch (e: Exception) {
            println("Vault isn't installed! The plugin cannot enable.")
            server.pluginManager.disablePlugin(this)
            return
        }
        getCommand("feed").executor = Feed()
        getCommand("hub").executor = Hub()
        getCommand("suggest").executor = Suggest()
        getCommand("suggestions").executor = Suggestions()
        getCommand("broadcast").executor = Broadcast()
        getCommand("clear").executor = Clear()
        getCommand("check").executor = Check()
        getCommand("Reply").executor = Reply()
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
        getCommand("flyspeed").executor = FlySpeed()
        getCommand("walkspeed").executor = WalkSpeed()
        getCommand("speed").executor = Speed()
        getCommand("gamemode").executor = GameMode()
        getCommand("gmsp").executor = ShGameMode()
        getCommand("gma").executor = ShGameMode()
        getCommand("gms").executor = ShGameMode()
        getCommand("gmc").executor = ShGameMode()
        getCommand("give").executor = Give()
        getCommand("kit").executor = Kit()
        getCommand("kits").executor = Kits()
        getCommand("menu").executor = Menu()
        getCommand("vanish").executor = Vanish()
        getCommand("server").apply {
            executor = Server()
            tabCompleter = Server()
        }.executor = Server()
        getCommand("enderchest").executor = Enderchest()
        getCommand("help").executor = Help()
        getCommand("stats").executor = stats()
        getCommand("msg").executor = Msg()
        getCommand("wake").executor = Wake()
        server.pluginManager.registerEvents(Back(), this)
        server.pluginManager.registerEvents(EventsListener(), this)
        server.pluginManager.registerEvents(StaffChat(), this)
        server.pluginManager.registerEvents(Menu(), this)
        server.pluginManager.registerEvents(Compass(), this)
        //TODO suggestion file reading
    }

    override fun onDisable() {
        // todo Suggestion file saving
    }

    @Suppress("unused")
    companion object {
        lateinit var playerdata: Connection
        lateinit var suggestionsdb: Connection
        lateinit var instance : Flash
        lateinit var vaultChat : Chat
        val async = Executors.newFixedThreadPool(5)
        var scEnabled = mutableListOf<Player>()
        var noPermission = "You don't have permission to do that.".error()
        var notPlayer = "You must be a player to do this.".error()
        var targetOffline = "The target player was not found, please check for any typos and try again.".error()

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

        fun parse(args: Array<out String>) : ParsedArgsSilent {
            val a = mutableListOf(*args)
            var found = false
            a.toList().forEachIndexed { index, s ->
                if (s.matches(Regex("-s(?:ilent)?"))) {
                    a.removeAt(index)
                    found = true
                    return@forEachIndexed
                }
            }
            return ParsedArgsSilent(a.toList() as ArrayList<String>, found)
        }

        fun playersInWorlds(server: String): MutableList<Player> {
            val players = mutableListOf<Player>()
            for (s in instance.config.getStringList(server)) {
                players.addAll((Bukkit.getWorld(s) ?: continue).players)
            }
            return players
        }
    }
}
