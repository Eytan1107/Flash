package me.flash.flash.commands.api

import me.flash.flash.Flash
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandMap
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

/*
New system for commands. This is better because:
    - use no params void run() instead of boolean execute()
    - command/alias/usage/description written in the constructor of a command class
    - methods for simplicity (e.g. checkPlayer())
    - no plugin.yml for registering commands

Check the Broadcast command class for an example of how this can be used.
 */

abstract class FlashCommand : Command {
    private var cmd: String? = null
    private var desc: String? = null
    private var use: String? = null
    var alias = mutableListOf<String>()
    lateinit var sender: CommandSender
    var args = mutableListOf<String>()
    private var logAction: String? = null

    constructor (cmd: String): this(parseCmd(cmd), parseAliases(cmd))

    constructor (cmd: String, use: String, desc: String, aliases: List<String>) : this(cmd, aliases) {
        usage = use
        setDescription(desc)
    }

    constructor (cmd: String, aliases: List<String>): super(cmd) {
        alias = aliases.toMutableList()
        this.cmd = cmd
        super.setLabel(cmd)
        this.aliases = alias
        this.register()
    }

    override fun execute(sender: CommandSender, cmd: String, args: Array<String>): Boolean {
        this.sender = sender
        this.cmd = cmd
        this.args = args.toMutableList()
        try {
            this.run()
        } catch (ex: IllegalArgumentException) {
            sender.sendMessage(ex.message)
        }
        catch (ex: FlashException) {
            sender.sendMessage(ex.message)
        }
        catch (t: Throwable) {
            sender.sendMessage(t.message)
            t.printStackTrace()
        }
        return true
    }

    abstract fun run()

    protected fun checkPlayer() {
        if (this.sender !is Player)
            throw FlashException(Flash.notPlayer)
    }

    final override fun setDescription(desc: String): Command {
        this.desc = desc
        super.setDescription(desc)
        return this
    }

    final override fun setUsage(use: String): Command {
        this.use = use
        super.setUsage(use)
        return this
    }

    fun register() {
        try {
            val declaredField = Bukkit.getServer().javaClass.getDeclaredField("commandMap")
            declaredField.isAccessible = true
            (declaredField[Bukkit.getServer()] as CommandMap).register(this.cmd, this)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    companion object {
        private fun parseAliases(cmd: String) : List<String> {
            val aliases: Array<String> = cmd.split("|").toTypedArray()
            return (if (aliases.isNotEmpty()) listOf(*aliases.copyOfRange(1, aliases.size)) else ArrayList());
        }

        private fun parseCmd(cmd: String) : String {
            return cmd.split("|")[0]
        }

    }

}
