package fr.gaster.pluginspawn;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SpawnUtility {

    public static void playerToSpawn(Spawn spawn, Player player) {
        if (spawn.haveLocation()) {
            player.teleport(spawn.getLocation());
            player.sendMessage(spawn.haveSpawn() ?
                    ChatColor.GOLD + "Vous venez d'être téléporté au spawn " + ChatColor.AQUA + spawn.getName() + ChatColor.GOLD + "." :
                    ChatColor.GOLD + "Vous venez d'être téléporté au " + ChatColor.AQUA + "spawn" + ChatColor.GOLD + ".");
        } else {
            player.sendMessage(spawn.haveSpawn() ?
                    ChatColor.RED + "Téléportation impossible, le spawn " + ChatColor.AQUA + spawn.getName() + ChatColor.RED + " n'est pas définit." :
                    ChatColor.RED + "Téléportation impossible, le " + ChatColor.AQUA + "spawn" + ChatColor.RED + " n'est pas définit.");
        }
    }

}
