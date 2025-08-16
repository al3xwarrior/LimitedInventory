package com.al3x.limitedInventory;

import com.al3x.limitedInventory.Commands.LimitedInventoryCmd;
import com.al3x.limitedInventory.Listeners.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    File dataFile;
    FileConfiguration data;

    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        dataFile = new File(getDataFolder(), "data.yml");
        if (!dataFile.exists()) {
            dataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }
        data = YamlConfiguration.loadConfiguration(dataFile);

        playerManager = new PlayerManager(data);

        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        getServer().getPluginManager().registerEvents(new DropItemListener(), this);
        getServer().getPluginManager().registerEvents(new DeathListener(), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(playerManager), this);
        getServer().getPluginManager().registerEvents(new SwapHandListener(), this);
        getCommand("limitedinventory").setExecutor(new LimitedInventoryCmd(playerManager));

        Bukkit.getLogger().info("Limited Inventories Enabled!");
    }

    @Override
    public void onDisable() {
        try {
            data.save(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bukkit.getLogger().info("Limited Inventories Disabled!");
    }
}
