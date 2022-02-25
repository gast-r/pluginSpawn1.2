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

public class SetSpawn implements CommandExecutor {
    private Spawn spawn;
    private FileConfiguration config;
    private PluginSpawn pluginMain;

    public SetSpawn(PluginSpawn pluginMain) {
        this.spawn = pluginMain.getSpawn();
        this.config = pluginMain.getConfig();
        this.pluginMain = pluginMain;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            String spawnName = args.length == 0 ? "" : args[0];
            Player player = (Player) sender;
            Location playerLocation = new Location(
                    player.getWorld(),
                    player.getLocation().getX(),
                    player.getLocation().getY(),
                    player.getLocation().getZ());
            spawn.setId(0);
            spawn.setName(spawnName);
            spawn.setLocation(playerLocation);
            saveSpawnInConfig(spawn);
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
        spawn.saveInConfig("spawns." + spawn.getId(), config);
        pluginMain.saveConfig();
    }
}
