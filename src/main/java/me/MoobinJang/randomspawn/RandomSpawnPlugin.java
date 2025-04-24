package me.MoobinJang.randomspawn;

import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class RandomSpawnPlugin extends JavaPlugin implements Listener {

    private File spawnFile;
    private FileConfiguration spawnConfig;

    @Override
    public void onEnable() {
        // config.yml 생성
        saveDefaultConfig();

        // spawn-locations.yml 로딩
        loadSpawnConfig();

        // 이벤트 등록
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("RandomSpawn plugin enabled!");
    }

    private void loadSpawnConfig() {
        spawnFile = new File(getDataFolder(), "spawn-locations.yml");
        if (!spawnFile.exists()) {
            try {
                spawnFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    }

    private void saveSpawnLocation(UUID uuid, Location location) {
        String path = "spawns." + uuid.toString();
        spawnConfig.set(path + ".world", location.getWorld().getName());
        spawnConfig.set(path + ".x", location.getX());
        spawnConfig.set(path + ".y", location.getY());
        spawnConfig.set(path + ".z", location.getZ());

        try {
            spawnConfig.save(spawnFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Location getRandomLocation() {
        FileConfiguration config = getConfig();

        int minX = config.getInt("spawn.minX");
        int maxX = config.getInt("spawn.maxX");
        int minZ = config.getInt("spawn.minZ");
        int maxZ = config.getInt("spawn.maxZ");
        String worldName = config.getString("spawn.world");

        World world = Bukkit.getWorld(worldName);
        if (world == null) {
            getLogger().warning("World '" + worldName + "' not found!");
            return null;
        }

        int x = minX + (int) (Math.random() * (maxX - minX + 1));
        int z = minZ + (int) (Math.random() * (maxZ - minZ + 1));
        int y = world.getHighestBlockYAt(x, z) + 1;

        return new Location(world, x + 0.5, y, z + 0.5);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        if (!player.hasPlayedBefore()) {
            Location randomLocation = getRandomLocation();
            if (randomLocation != null) {
                player.teleport(randomLocation);
                player.setRespawnLocation(randomLocation); // 1.20+ 사용 가능
                saveSpawnLocation(uuid, randomLocation);
                getLogger().info("New spawn saved for " + player.getName());
            }
        }
    }
}
