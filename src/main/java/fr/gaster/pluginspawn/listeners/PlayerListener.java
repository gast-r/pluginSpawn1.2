package fr.gaster.pluginspawn.listeners;

import fr.gaster.pluginspawn.PluginSpawn;
import fr.gaster.pluginspawn.Spawn;
import fr.gaster.pluginspawn.SpawnUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class PlayerListener implements Listener {

    PluginSpawn pluginMain;
    private HashMap<String, Spawn> hashOfSpawn;

    public PlayerListener(PluginSpawn pluginMain) {
        this.pluginMain = pluginMain;
        this.hashOfSpawn = this.pluginMain.getHashOfSpawns();
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerConnection(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Spawn spawnToTp = hashOfSpawn.containsKey(" ") ? hashOfSpawn.get(" ") : new Spawn(" ", null);
        SpawnUtility.playerToSpawn(spawnToTp, player);
    }
}
