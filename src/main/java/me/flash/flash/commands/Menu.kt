package me.flash.flash.commands

import me.flash.flash.Flash
import me.flash.flash.listeners.Compass
import me.flash.flash.listeners.KitsMenu
import me.flash.flash.utils.FlashUtil.Companion.notPlayer
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.plugin.java.JavaPlugin


@Suppress("UNUSED_VARIABLE", "RedundantVisibilityModifier", "LocalVariableName")
class Menu : CommandExecutor, Listener {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) {
            sender.sendMessage(notPlayer)
            return true
        }
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("worlds.hub").contains(sender.world.name)) Compass.genInventory(sender)
        if (JavaPlugin.getPlugin(Flash::class.java).config.getStringList("worlds.kitpvpworld").contains(sender.world.name)) KitsMenu.genInventory(sender)
        return true
    }
}
// ok so, the worlds.* nothing works for any commands , I made sure every command has it changed but, it cant check for the player's world so what did I do wrong ?














    


