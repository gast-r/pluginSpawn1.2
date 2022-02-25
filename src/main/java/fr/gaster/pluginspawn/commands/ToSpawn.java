package fr.gaster.pluginspawn.commands;

import fr.gaster.pluginspawn.PluginSpawn;
import fr.gaster.pluginspawn.Spawn;
import fr.gaster.pluginspawn.SpawnUtility;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToSpawn implements CommandExecutor {

    private Spawn spawn;

    public ToSpawn(PluginSpawn pluginMain) {
        this.spawn = pluginMain.getSpawn();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            SpawnUtility.playerToSpawn(spawn, player);
            return true;
            } else {
            sender.sendMessage("Cette commande est réservé aux joueurs.");
        }
        return false;
    }
}
