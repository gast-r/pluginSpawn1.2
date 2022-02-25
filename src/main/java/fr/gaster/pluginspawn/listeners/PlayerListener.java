package fr.gaster.pluginspawn.listeners;

import fr.gaster.pluginspawn.PluginSpawn;
import fr.gaster.pluginspawn.Spawn;
import fr.gaster.pluginspawn.SpawnUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    PluginSpawn pluginMain;
    Spawn spawn;

    public PlayerListener(PluginSpawn pluginMain) {
        this.pluginMain = pluginMain;
        this.spawn = this.pluginMain.getSpawn();
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onPlayerConnection(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        SpawnUtility.playerToSpawn(spawn, player);
    }
}
