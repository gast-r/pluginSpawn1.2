package fr.gaster.pluginspawn;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn {

    private Location location;
    private String name;
    private int id;

    public Spawn() {
        this.id = 0;
        this.name = "";
        this.location = null;
    }
    public Spawn(int spawnId, String spawnName, Location spawnLocation) {
        this.id = spawnId;
        this.name = spawnName;
        this.location = spawnLocation;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean haveLocation() {
        return this.location != null;
    }

    public boolean haveSpawn() {
        return this.name != "";
    }

    @Override
    public String toString() {
        return "Spawn{" +
                "location=" + location +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    public void saveInConfig(String path, FileConfiguration config) {
        config.set(path + ".id", id);
        config.set(path + ".name", name);
        config.set(path + ".location", location);
    }

    public void loadFromConfig(String path, FileConfiguration config) {
        int loadId = (int) config.get(path + ".id");
        String loadName = (String) config.get(path + ".name");
        Location loadLocation = (Location) config.get(path + ".location");
        this.setId(loadId);
        this.setName(loadName);
        this.setLocation(loadLocation);
    }
}
