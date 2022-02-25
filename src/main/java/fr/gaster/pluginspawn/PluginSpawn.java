package fr.gaster.pluginspawn;

import fr.gaster.pluginspawn.commands.SetSpawn;
import fr.gaster.pluginspawn.commands.ToSpawn;
import fr.gaster.pluginspawn.listeners.PlayerListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PluginSpawn extends JavaPlugin {

    private Spawn spawn;
    private PluginManager pluginManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("[PluginSpawn] Le plugin s'allume...");
        pluginManager = Bukkit.getServer().getPluginManager();
        this.saveDefaultConfig();
        this.initSpawn();

        pluginManager.registerEvents(new PlayerListener(this), this);

        getCommand("spawn").setExecutor(new ToSpawn(this));
        getCommand("setspawn").setExecutor(new SetSpawn(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("[PluginSpawn] Le plugin s'éteint...");
    }

    public Spawn getSpawn() {
        return this.spawn;
    }

    private void initSpawn() {
        spawn = new Spawn();
        System.out.println("INITIALISATION DU SPAWN");
        try {
            spawn.loadFromConfig("spawns.0", this.getConfig());
            System.out.println("Spawn chargé depuis la config");
            } catch (Exception e) {
                System.out.println("EXCEPTION");
                System.out.println(e.getMessage());
            System.out.println("Le spawn n'a pas pu être chargé depuis la config, création d'un nouveau spawn.");
        }
    }
}

