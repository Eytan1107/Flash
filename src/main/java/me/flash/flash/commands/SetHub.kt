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
        Flash.instance.config.set("hub.location.x", sender.location.x)
        Flash.instance.config.set("hub.location.y", sender.location.y)
        Flash.instance.config.set("hub.location.z", sender.location.z)
        Flash.instance.saveConfig()
        val hubX = Flash.instance.config.getDouble("hub.location.x")
        val hubY = Flash.instance.config.getDouble("hub.location.y")
        val hubZ = Flash.instance.config.getDouble("hub.location.z")
        Bukkit.dispatchCommand(sender, "mv setspawn")
        sender.sendMessage("You have set the Parkour at &c${"%.2f".format(hubX)}, ${hubY.toInt()}, ${"%.2f".format(hubZ)}".prefix())
        return true
    }
}
