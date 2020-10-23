package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetHub : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage("Huh? Where you wanna set the hub ? In the files ?".error()).run { return true }
        if (args.isNotEmpty()) sender.sendMessage("Too many arguments".error())
        if (sender.world.name != "world") sender.sendMessage("You can only do this command in the Hub.".error()).run { return true }
        val hubX = Flash.instance.config.getString("hub.location.x").removeSurrounding("[", "]")
        val hubY = Flash.instance.config.getString("hub.location.y").removeSurrounding("[", "]")
        val hubZ = Flash.instance.config.getString("hub.location.z").removeSurrounding("[", "]")
        Flash.instance.config.set("hub.location.x", sender.location.x.toInt().toDouble())
        Flash.instance.config.set("hub.location.y", sender.location.y.toInt().toDouble())
        Flash.instance.config.set("hub.location.z", sender.location.z.toInt().toDouble())
        Flash.instance.saveConfig()
        Bukkit.dispatchCommand(sender, "mv setspawn" + args.joinToString(" "))
        sender.sendMessage("You have set the Parkour at &c$hubX, $hubY, $hubZ".prefix())
        return true
    }
}
