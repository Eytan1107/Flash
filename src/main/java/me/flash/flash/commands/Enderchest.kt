package me.flash.flash.commands

import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import java.io.File
import java.io.IOException

class Enderchest : CommandExecutor, JavaPlugin(), Listener {

    //var echestname: String? = null
    //override fun onEnable() {
        //Bukkit.getPluginManager().registerEvents(this, this)
        //saveDefaultConfig()
        //for (all in Bukkit.getOnlinePlayers()) {
        //    makeFile(all)
        //}
    //}

    //override fun onDisable() {
        //val folder = File("$dataFolder/spieler").listFiles()
        //for (file in folder) {
            //val config = YamlConfiguration .loadConfiguration(file)
            //try {
                //config.save(file)
            //} catch (e: IOException) {
            //}
       // }
    //}

    //var inv: Inventory? = null
    //private fun makeFile(p: Player) {
        //val file = File("$dataFolder/spieler", p.name + ".yml")
        //if (!file.exists()) {
            //val config: FileConfiguration = YamlConfiguration .loadConfiguration(file)
            //config["Items.0"] = ItemStack(Material.AIR)
            //try {
            //    config.save(file)
           //} catch (e: IOException) {
            //}
        //}
    //}

    //private fun openEnderchest(p: Player, slots: Int) {
        //val file = File("$dataFolder/spieler", p.name + ".yml")
        //val config: FileConfiguration = YamlConfiguration.loadConfiguration(file)
        //val inv = Bukkit.createInventory(null, slots * 9, echestname!!.replace("<Spieler>", p.name))
        //for (key in config.getConfigurationSection("Items") .getKeys(false)) {
            //try {
                //inv.setItem(key.toInt(),
                        //config.getItemStack("Items.$key"))
            //} catch (e: Exception) {
            //}
        //}
        //p.openInventory(inv)
        //p.playSound(p.location, Sound.CHEST_OPEN, 1f, 1f)
    //}

    //@EventHandler
    //fun onInvClose(e: InventoryCloseEvent) {
        //val p = e.player as Player
        //if (e.inventory.title == echestname?.replace("<Spieler>", p.name)) {
            //p.playSound(p.location, Sound.CHEST_CLOSE, 1f, 1f)
            //val file = File("$dataFolder/spieler", p.name + ".yml")
            //val config: FileConfiguration = YamlConfiguration .loadConfiguration(file)
            //for (i in 0 until e.inventory.size) {
                //if (e.inventory.getItem(i) == null) {
                    //config["Items.$i"] = null
                //} else {
                    //config["Items.$i"] = e.inventory.getItem(i)
               // }
           // }
            //try {
               // config.save(file)
           // } catch (e1: Exception) {
          //  }
        //}
   // }

    //@EventHandler
   // fun onJoin(e: PlayerJoinEvent) {
       // val p = e.player
    //    makeFile(p)
   // }

    //@EventHandler
    //fun onEnderchestClick(e: PlayerInteractEvent) {
      //  val p = e.player
     //  if (e.action == Action.RIGHT_CLICK_BLOCK && !e.isCancelled
      //          && e.clickedBlock.type == Material.ENDER_CHEST) {
      //      if (p.itemInHand.type == Material.AIR) {
      //          p.isSneaking = false
      //      }
      //      if (p.itemInHand.type.isBlock && p.isSneaking) {
        //        return
        //    }
         //   for (i in 6 downTo 2) {
         //       if (p.hasPermission("Enderchest.slots.$i") || p.isOp) {
         //           openEnderchest(p, i)
         //           e.isCancelled = true
         //           break
          //      }
          //  }
      //  }
   // }

   // override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
     //   return true
  //  }
}



