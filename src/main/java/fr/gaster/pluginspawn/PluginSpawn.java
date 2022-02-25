package fr.gaster.pluginspawn;

import fr.gaster.pluginspawn.commands.SetSpawn;
import fr.gaster.pluginspawn.commands.ToSpawn;
import fr.gaster.pluginspawn.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Set;

public final class PluginSpawn extends JavaPlugin {

    private PluginManager pluginManager;
    private HashMap<String, Spawn> hashOfSpawns;
    private Spawn spawn;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[PluginSpawn] Le plugin s'allume...");
        pluginManager = Bukkit.getServer().getPluginManager();
        this.saveDefaultConfig();
        this.initHashOfSpawn();

        pluginManager.registerEvents(new PlayerListener(this), this);

        getCommand("spawn").setExecutor(new ToSpawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[PluginSpawn] Le plugin s'éteint...");
    }

    public HashMap<String, Spawn> getHashOfSpawns() {
        return hashOfSpawns;
    }

    private void initHashOfSpawn() {
        hashOfSpawns = new HashMap();
        System.out.println("INITIALISATION DU HashMap de Spawn");
        try {
            Set<String> nameOfSpawns = this.getConfig().getConfigurationSection("spawns").getKeys(false);
            System.out.println("NOMBRE DE SPAWN : " + nameOfSpawns.size());
            for (String spawn : nameOfSpawns) {
                System.out.println(spawn);
                Spawn onGoingSpawn = new Spawn();
                onGoingSpawn.loadFromConfig("spawns." + spawn, this.getConfig());
                hashOfSpawns.put(spawn, onGoingSpawn);
            }

        } catch (Exception e) {
            System.out.println("EXCEPTION");
            System.out.println(e.getMessage());
            System.out.println("Not able to load spawn values from config, creation of an empty HashMap of spawn.");

        }
    }
}

