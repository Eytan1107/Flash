package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SetHub : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) sender.sendMessage("Huh? Where you wanna set the hub ? In the files ?".error()).run { return true }
        if (args.isNotEmpty()) sender.sendMessage("Too many arguments".error())
        if (sender.world.name != "world") sender.sendMessage("You can only do this command in the Hub.".error()).run { return true }
        with(sender.location) {
            sender.location.world.setSpawnLocation(blockX, blockY, blockZ)
            sender.sendMessage("Hub set at : &c".prefix() + x.toInt() + ", " + y.toInt() + ", " + z.toInt())
        }

        return true
    }
}
