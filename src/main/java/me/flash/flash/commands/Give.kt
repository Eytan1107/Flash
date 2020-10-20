package me.flash.flash.commands

import me.flash.flash.commands.api.FlashCommand
import me.flash.flash.utils.FlashUtil
import me.flash.flash.utils.FlashUtil.Companion.color
import me.flash.flash.utils.FlashUtil.Companion.error
import me.flash.flash.utils.FlashUtil.Companion.prefix
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class Give : FlashCommand("give") {

    init {
        usage = "[player] [item] <count>"
        description = "Give a player an item"
        setMinArgs(1)
    }

    override fun run() {
        checkPerm("flash.give")
        when {
            args.size > 3 -> {
                msg("&6&oYour command is too long, redirecting to vanilla /give.".color())
                msg("&6&oIf this is an error (command block), please try without spacing (commandblock)".color())
                Bukkit.dispatchCommand(sender, "minecraft:give " + args.joinToString(" "))
                return
            }
            args.size < 2 -> {
                msg("&cUsage: /give [player] [item] <count>".prefix())
            }
            else -> {
                val player = getTarget(0)
                val material = Material.getMaterial(args[1].toUpperCase())
                        ?: msg("That item was not found".error()).run { return }
                val count = args.getOrElse(2) { "1" }.toIntOrNull() ?: 1

                player.inventory.addItem(ItemStack(material, count))
                if (player == sender) {
                    if (!player.hasPermission("flash.msg.nice")) player.sendMessage("You have been given &c$count &6of &c${material.name.toLowerCase().replace("_", " ")}".prefix()) else player.sendMessage("You have been given &l$count &6of &l${material.name.toLowerCase().replace("_", " ")}".prefix())
                }
                else {
                    if (!sender.hasPermission("flash.msg.nice")) sender.sendMessage("You have given &c$count &6of &c${material.name.toLowerCase().replace("_", " ")} &6to &c${player.name}".prefix()) else sender.sendMessage("You have given &l$count &6of &l${material.name.toLowerCase().replace("_", " ")} &6to &l${player.name}".prefix())
                    if (!player.hasPermission("flash.msg.nice")) player.sendMessage("You have been given &c$count &6of &c${material.name.toLowerCase().replace("_", " ")} &6by &c${sender.name}".prefix()) else player.sendMessage("You have been given &l$count &6of &l${material.name.toLowerCase().replace("_", " ")} &6by &l${sender.name}".prefix())
                }
                FlashUtil.staffMessage(sender, "Gave &l$count &dof &l${material.name.toLowerCase().replace("_", " ")} &dto &l${player.name}")
            }
        }
    }
}
