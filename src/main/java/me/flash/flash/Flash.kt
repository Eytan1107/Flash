package me.flash.flash

import me.flash.flash.commands.*
import me.flash.flash.listeners.Compass
import me.flash.flash.listeners.EventsListener
import me.flash.flash.listeners.KitsMenu
import net.milkbowl.vault.chat.Chat
import org.bukkit.plugin.java.JavaPlugin
import org.sqlite.JDBC
import java.io.File
import java.sql.Connection
import java.sql.DriverManager

class Flash : JavaPlugin() {
    override fun onEnable() {
        saveDefaultConfig()
        Class.forName("org.sqlite.JDBC")
        DriverManager.registerDriver(JDBC())
        playerdata = DriverManager.getConnection("jdbc:sqlite:" + File(dataFolder, "playerdata.db").absolutePath)
        suggestionsdb = DriverManager.getConnection("jdbc:sqlite:" + File(dataFolder, "suggestions.db").absolutePath)
        instance = this
        //language=SQLite
        playerdata.prepareStatement("create table if not exists data(uuid varchar(48), kills int default(0), deaths int default(0), time int default(0), primary key(uuid));").executeUpdate()
        playerdata.autoCommit = true
        //language=SQLite
        suggestionsdb.prepareStatement("create table if not exists suggestion(uuid varchar(48), text varchar(256));").executeUpdate()
        suggestionsdb.autoCommit = true

        //can we actually make a proper hook so I dont have to comment this section out every time I want to test the plugin? - Skeagle
        try {
            vaultChat = server.servicesManager.getRegistration(Chat::class.java).provider
        } catch (e: Exception) {
            println("Vault isn't installed! The plugin cannot enable.")
            server.pluginManager.disablePlugin(this)
            return
        }

        Feed().register()
        Hub().register()
        getCommand("suggest").executor = Suggest()
        getCommand("suggestions").executor = Suggestions()
        KillAll().register()
        Broadcast().register()
        Clear().register()
        Check().register()
        getCommand("Reply").executor = Reply()
        Colorlist().register()
        Craft().register()
        Discord().register()
        F().register()
        getCommand("tpall").executor = TpAll()
        getCommand("loopkill").executor = Loopkill().apply { this.start() }
        ClearAll().register()
        getCommand("teleport").executor = Teleport()
        getCommand("silentteleport").executor = Teleport()
        getCommand("tphere").executor = TpHere()
        getCommand("silenttphere").executor = TpHere()
        getCommand("kill").executor = Kill()
        Back().register()
        getCommand("sudo").executor = Sudo()
        Fly().register()
        getCommand("staffchat").executor = StaffChat()
        getCommand("tphere").executor = TpHere()
        getCommand("flyspeed").executor = FlySpeed()
        getCommand("walkspeed").executor = WalkSpeed()
        getCommand("speed").executor = Speed()
        GameMode().register()
        getCommand("gmsp").executor = ShGameMode()
        getCommand("gma").executor = ShGameMode()
        getCommand("gms").executor = ShGameMode()
        getCommand("gmc").executor = ShGameMode()
        Give().register()
        getCommand("kit").executor = Kit()
        getCommand("kits").executor = Kits()
        getCommand("menu").executor = Menu()
        getCommand("vanish").executor = Vanish()
        Build().register()
        getCommand("server").apply {
            executor = Server()
            tabCompleter = Server()
        }.executor = Server()
        getCommand("help").executor = Help()
        getCommand("stats").executor = stats()
        getCommand("msg").executor = Msg()
        getCommand("wake").executor = Wake()
        getCommand("sethub").executor = SetHub()
        Invsee().register()
        getCommand("players").executor = Players()
        getCommand("rank").executor = Rank()
        getCommand("parkour").executor = Parkour()
        server.pluginManager.registerEvents(Back(), this)
        server.pluginManager.registerEvents(EventsListener(), this)
        server.pluginManager.registerEvents(StaffChat(), this)
        server.pluginManager.registerEvents(Build(), this)
        server.pluginManager.registerEvents(Menu(), this)
        server.pluginManager.registerEvents(Compass(), this)
        server.pluginManager.registerEvents(Rank(), this)
        server.pluginManager.registerEvents(KitsMenu(), this)
    }

    companion object {
        lateinit var playerdata: Connection
        lateinit var suggestionsdb: Connection
        lateinit var instance: Flash
        lateinit var vaultChat: Chat
    }
}
