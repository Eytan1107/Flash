package me.flash.flash.commands

import me.flash.flash.utils.FlashUtil.Companion.color
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class Help : CommandExecutor {

    //needs a rework. badly.

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = Bukkit.getPlayer(sender.name)
        if (args.contains("player")) { // Workaround so thar staff can see the normal commands as well
            player.sendMessage("&6&m               &6&l Players Help Menu &m          ".color()) // ChatMessage
            player.sendMessage("&a/spawn &3to return to spawn".color()) // ChatMessage
            player.sendMessage("&a/server <server> &3to change server (for example: &a/server kitpvp&3)".color()) // ChatMessage
            player.sendMessage("&a/msg <player> <text> &3to send a private message to someone".color()) // ChatMessage
            player.sendMessage("&a/flashcoins &3to check your Flash Coins (you can also do &a/fc&3)".color()) // ChatMessage
            player.sendMessage("&a/menu &3to open the Server Selector".color()) // ChatMessage
            if (player.hasPermission("flash.fly.hub")) player.sendMessage("&a/fly &3to fly in the hub !".color()) // ChatMessage
            player.sendMessage("&a/discord&e for the discord link".color()) // ChatMessage
            player.sendMessage("&6&m                                                  ".color()) // ChatMessage
        }
        if (!player.hasPermission("flash.staff")) { // Checks if the player is a non
                if (player.world == Bukkit.getWorld("world")) { // Runs if there is only 1 argument
                    if (args.isEmpty() or args.contains("1")) { // Checks if the player is a non
                        player.sendMessage("&6&m                   &6&l Help Menu &m              ".color()) // ChatMessage
                        player.sendMessage("&a/hub &eto to to the hub".color()) // ChatMessage
                        player.sendMessage("&a/spawn &3to return to spawn".color()) // ChatMessage
                        player.sendMessage("&a/server <server> &3to change server (for example: &a/server kitpvp&3)".color()) // ChatMessage
                        player.sendMessage("&a/msg <player> <text> &3to send a private message to someone".color()) // ChatMessage
                        player.sendMessage("&a/flashcoins &3to check your Flash Coins (you can also do &a/fc&3)".color()) // ChatMessage
                        player.sendMessage("&a/menu &3to open the Server Selector".color()) // ChatMessage
                        if (player.hasPermission("flash.fly.hub")) player.sendMessage("&a/fly &3to fly in the hub !".color()) // ChatMessage
                        player.sendMessage("&a/discord&e for the discord link".color()) // ChatMessage
                        player.sendMessage("&6&m                  &6&l Page 1 &m                  ".color()) // ChatMessage
                    }
                } else if (player.world == Bukkit.getWorld("kitpvp")) { // Checks if the player is in kitpvp
                    player.sendMessage("&6&m                   &6&l Help Menu &m              ".color()) // ChatMessage
                    player.sendMessage("&a/hub &eto to to the hub".color()) // ChatMessage
                    player.sendMessage("&a/spawn &3to return to spawn".color()) // ChatMessage
                    player.sendMessage("&a/kit&e to fast select a kit".color()) // ChatMessage
                    player.sendMessage("&a/msg <player> <text> &3to send a private message to someone".color()) // ChatMessage
                    player.sendMessage("&a/flashcoins &3to check your Flash Coins (you can also do &a/fc&3)".color()) // ChatMessage
                    player.sendMessage("&a/discord&e for the discord link".color()) // ChatMessage
                    player.sendMessage("&a/kitmenu&e open the kitmenu".color())
                    player.sendMessage("&6&m                  &6&l Page 1 &m                  ".color()) // ChatMessage
                    return true
                } else if (player.world == Bukkit.getWorld("skyblock")) {
                    player.sendMessage("&6&m                   &6&l Help Menu &m              ".color()) // ChatMessage
                    player.sendMessage("&a/hub &eto to to the hub".color()) // ChatMessage
                    player.sendMessage("&a/spawn &3to return to spawn".color()) // ChatMessage
                    player.sendMessage("&a/is help&e to see all the island commands".color()) // ChatMessage
                    player.sendMessage("&a/msg <player> <text> &3to send a private message to someone".color()) // ChatMessage
                    player.sendMessage("&a/flashcoins &3to check your Flash Coins (you can also do &a/fc&3)".color()) // ChatMessage
                    player.sendMessage("&a/discord&e for the discord link".color()) // ChatMessage
                    player.sendMessage("&6&m                  &6&l Page 1 &m                  ".color()) // ChatMessage
                }

            }else if (player.hasPermission("flash.staff")) {
                if (args.isEmpty() || args.contains("1")) {
                    player.sendMessage("&c&m               &4&l Staff Help Menu &m          ".color()) // ChatMessage
                    if (player.hasPermission("flash.back")) player.sendMessage("&c&l/back".color()) // ChatMessage
                    if (player.hasPermission("flash.broadcast")) player.sendMessage("&c&l/bc".color()) // ChatMessage
                    if (player.hasPermission("flash.check")) player.sendMessage("&c&l/check <player>".color()) // ChatMessage
                    if (player.hasPermission("flash.clear.others")) player.sendMessage("&c&l/clear [player]".color()) else if (player.hasPermission("flash.clear")) player.sendMessage("&c&l/clear".color()) // ChatMessage
                    if (player.hasPermission("flash.clearall")) player.sendMessage("&4&l/clearall !!!".color()) // ChatMessage
                    if (player.hasPermission("flash.colorlist")) player.sendMessage("&c&l/colorlist".color()) // ChatMessage
                    if (player.hasPermission("flash.vanish")) player.sendMessage("&c&l/vanish [player]".color()) // ChatMessage
                    player.sendMessage("&c&m                     &4&l Page 1 &c&m                    ".color()) // ChatMessage
                    return true
                } else if (args.contains("2")) {
                    player.sendMessage("&c&m                 &4&l Staff Help Menu &m&c            ".color()) // ChatMessage
                    if (player.hasPermission("flash.craft.all")) player.sendMessage("&c&l/craft".color()) else if (player.hasPermission("flash.craft")) player.sendMessage("&c&l/craft".color()) // ChatMessage
                    if (player.hasPermission("flash.feed.others")) player.sendMessage("&c&l/feed [player]".color()) else if (player.hasPermission("flash.feed")) player.sendMessage("&c&l/feed".color()) // ChatMessage
                    if (player.hasPermission("flash.fly.others")) player.sendMessage("&c&l/fly [player]".color()) else if (player.hasPermission("flash.fly")) player.sendMessage("&c&l/fly".color()) // ChatMessage
                    if (player.hasPermission("flash.flyspeed")) player.sendMessage("&c&l/flyspeed <speed> [player]".color())
                    if (player.hasPermission("flash.gamemode.others")) player.sendMessage("&c&l/gamemode <gamemode> [player]".color()) else if (player.hasPermission("flash.gamemode")) player.sendMessage("&c&l/gamemode <gamemode>".color()) // ChatMessage
                    if (player.hasPermission("flash.give")) player.sendMessage("&c&l/give [player] [item] <count>".color()) // ChatMessage
                    if (player.hasPermission("flash.walkspeed")) player.sendMessage("&c&l/walkspeed <speed> [player]".color()) // ChatMessage
                    player.sendMessage("&c&m                    &4&l Page 2 &m&c                    ".color()) // ChatMessage
                    return true
                } else if (args.contains("3")) {
                    player.sendMessage("&c&m                 &4&l Staff Help Menu &m&c            ".color()) // ChatMessage
                    if (player.hasPermission("flash.kill")) player.sendMessage("&c&l/kill <player>".color()) // ChatMessage
                    if (player.hasPermission("flash.loopkill")) player.sendMessage("&c&l/loopkill <player>".color()) // ChatMessage
                    if (player.hasPermission("flash.staffchat")) player.sendMessage("&c&l/staffchat or # <message>".color()) // ChatMessage
                    if (player.hasPermission("flash.sudo")) player.sendMessage("&c&l/sudo <player> <message>".color()) // ChatMessage
                    if (player.hasPermission("tp.others")) player.sendMessage("&c&l/tp <player> [player]".color()) else if (player.hasPermission("flash.tp")) player.sendMessage("&c&l/tp <player>".color()) // ChatMessage
                    if (player.hasPermission("flash.tpall")) player.sendMessage("&4&l/tpall !!!".color()) // ChatMessage
                    if (player.hasPermission("flash.tphere")) player.sendMessage("&c&l/tphere <player> or /s <player>".color()) // ChatMessage
                    player.sendMessage("&c&m                    &4&l Page 3 &m&c                    ".color()) // ChatMessage
                    return true
                }
            }
        return true
        }
    }


//if (player.hasPermission("")) player.sendMessage("".color()) else if (player.hasPermission("")) player.sendMessage("".color())