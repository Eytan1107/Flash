package me.flash.flash.commands.api

import me.flash.flash.FlashUtil
import me.flash.flash.FlashUtil.Companion.color
import me.flash.flash.FlashUtil.Companion.error
import me.flash.flash.FlashUtil.Companion.prefix
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
    private var minArgs = 0

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
            if (args.size < minArgs) throw FlashException("/$cmd $usage".error())
            this.run()
        }
        catch (ex: FlashException) {
            sender.sendMessage(ex.getMsg())
        }
        catch (t: Throwable) {
            sender.sendMessage(t.message)
            t.printStackTrace()
        }
        return true
    }

    abstract fun run()

    //DO NOT use in an if statement
    protected fun checkPlayer() {
        if (!isPlayer())
            throw FlashException(FlashUtil.notPlayer)
    }

    protected fun isPlayer() : Boolean {
        return sender is Player
    }

    //DO NOT use in an if statement
    protected fun checkPerm(perm: String) {
        if (!hasPerm(perm))
            throw FlashException(FlashUtil.noPermission)
    }

    protected fun hasPerm(perm: String) : Boolean {
        return sender.hasPermission(perm)
    }

    protected fun getPlayer() : Player {
        return if (isPlayer()) (sender as Player) else throw FlashException("sender cannot be cast to player, make sure you are checking for the console")
    }

    final override fun setDescription(desc: String): Command {
        this.desc = desc
        super.setDescription(desc)
        return this
    }

    final override fun setUsage(use: String): Command {
        this.use = "/$cmd $use"
        super.setUsage(use)
        return this
    }

    protected fun getTarget(index: Int): Player {
        if (args.size < index) throw FlashException("Index cannot be greater than args size")
        return Bukkit.getPlayer(args[index]) ?: throw FlashException(FlashUtil.targetOffline)
    }

    protected fun setMinArgs(minArgs: Int) {
        this.minArgs = minArgs
    }

    protected fun msg(s: String) {
        FlashUtil.say((if (isPlayer()) getPlayer() else sender), s.color())
    }

    protected fun msg(cs: CommandSender, s: String) {
        FlashUtil.say(cs, s.color())
    }

    protected fun <T> ternary(condition: Boolean, v1: T, v2: T) : T {
        return (if (condition) v1 else v2)
    }

    protected fun nice() : String {
        return ternary(!hasPerm("flash.msg.nice"), "&c", "&l")
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
