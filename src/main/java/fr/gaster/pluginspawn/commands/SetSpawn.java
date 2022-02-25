package fr.gaster.pluginspawn.commands;

import fr.gaster.pluginspawn.PluginSpawn;
import fr.gaster.pluginspawn.Spawn;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SetSpawn implements CommandExecutor {
    private PluginSpawn pluginMain;
    private HashMap<String, Spawn> hashOfSpawn;
    private FileConfiguration config;

    public SetSpawn(PluginSpawn pluginMain) {
        this.pluginMain = pluginMain;
        this.hashOfSpawn = this.pluginMain.getHashOfSpawns();
        this.config = this.pluginMain.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            String spawnName = args.length == 0 ? " " : args[0];
            Player player = (Player) sender;
            Location playerLocation = new Location(
                    player.getWorld(),
                    player.getLocation().getX(),
                    player.getLocation().getY(),
                    player.getLocation().getZ());
            Spawn spawnToSet = new Spawn(spawnName, playerLocation);
            if (hashOfSpawn.containsKey(spawnName)) { // is spawn in HashMap
                this.hashOfSpawn.get(spawnName).setLocation(playerLocation);
            } else { // if spawn NOT in HashMap, put the spawn in the HashMap
                this.hashOfSpawn.put(spawnName, spawnToSet);
            }
            saveSpawnInConfig(spawnToSet);
            player.sendMessage(args.length == 0 ?
                    ChatColor.GOLD + "Vous venez définit le " + ChatColor.AQUA + "spawn" + ChatColor.GOLD + " sur votre position." :
                    ChatColor.GOLD + "Vous venez définit le spawn " + ChatColor.AQUA + args[0] + ChatColor.GOLD + " sur votre position." );
            return true;
        } else {
            sender.sendMessage("Cette commande est réservé aux joueurs.");
        }
        return false;
    }

    private void saveSpawnInConfig(Spawn spawn) {
        spawn.saveInConfig("spawns." + spawn.getName(), config);
        pluginMain.saveConfig();
    }
}
