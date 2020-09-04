package me.flash.flash.commands


import me.flash.flash.Flash.Companion.noPermission
import me.flash.flash.Flash.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Flyspeed : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            if (sender.hasPermission("flash.flyspeed")) {
                sender.sendMessage("/flyspeed <speed>".prefix())
                return true
            } else sender.sendMessage(noPermission)
            when {
                args.contains("1") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 1F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 1".prefix())
                    return true
                }
                args.contains("2") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 2F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 2".prefix())
                    return true
                }
                args.contains("3") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 3".prefix())
                    player.flySpeed = 3F
                    return true
                }
                args.contains("4") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 4F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 4".prefix())
                    return true
                }
                args.contains("5") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 5F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 5".prefix())
                    return true
                }
                args.contains("6") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 6F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 6".prefix())
                    return true
                }
                args.contains("7") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 7F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 7".prefix())
                    return true
                }
                args.contains("8") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 8F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 8".prefix())
                    return true
                }
                args.contains("9") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 9F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 9".prefix())
                    return true
                }
                args.contains("10") -> {
                    val player = Bukkit.getPlayer(sender.name)
                    player.flySpeed = 10F
                    sender.sendMessage("Set fly speed for &l${sender.name}&r &6to 10".prefix())
                    return true
                }
                else -> sender.sendMessage(noPermission)
            }
        }
    return true
    }
}