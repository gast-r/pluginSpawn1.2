package fr.gaster.pluginspawn.commands;

import fr.gaster.pluginspawn.PluginSpawn;
import fr.gaster.pluginspawn.Spawn;
import fr.gaster.pluginspawn.SpawnUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class ToSpawn implements CommandExecutor {

    private PluginSpawn pluginMain;
    private HashMap<String, Spawn> hashOfSpawn;

    public ToSpawn(PluginSpawn pluginMain) {
        this.pluginMain = pluginMain;
        this.hashOfSpawn = this.pluginMain.getHashOfSpawns();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            String spawnName = args.length == 0 ? " " : args[0];
            Spawn spawnToTp = hashOfSpawn.containsKey(spawnName) ? hashOfSpawn.get(spawnName) : new Spawn(spawnName, null);
            Player player = (Player) sender;
            SpawnUtility.playerToSpawn(spawnToTp, player);
            return true;

            } else {
            sender.sendMessage("Cette commande est réservé aux joueurs.");
        }
        return false;
    }
}
