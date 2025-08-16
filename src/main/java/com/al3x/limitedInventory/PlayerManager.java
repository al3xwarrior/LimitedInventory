package com.al3x.limitedInventory;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.UUID;

import static com.al3x.limitedInventory.Util.colorize;

public class PlayerManager {

    private FileConfiguration data;
    private HashMap<UUID, Integer> players;

    public PlayerManager(FileConfiguration data) {
        players = new HashMap<>();

        this.data = data;

        // Load Data
        ConfigurationSection section = data.getConfigurationSection("players");
        if (section == null || section.getKeys(false).isEmpty()) {
            Bukkit.getLogger().info("No player data found to load.");
            return;
        }

        for (String uuidString : section.getKeys(false)) {
            try {
                UUID uuid = UUID.fromString(uuidString);
                int value = data.getInt("players." + uuidString);
                Bukkit.getLogger().info("Loaded: " + uuid + " -> " + value);

                // You can put this into a Map if you want to keep it in memory
                players.put(uuid, value);

            } catch (IllegalArgumentException e) {
                Bukkit.getLogger().warning("Invalid UUID format in data: " + uuidString);
            }
        }
    }

    public void setPlayer(Player player, int slots) {
        players.put(player.getUniqueId(), slots);
        updatePlayerSlots(player);
        data.set("players." + player.getUniqueId(), slots);
    }

    public int getPlayerSlots(Player player) {
        return players.getOrDefault(player.getUniqueId(), 0);
    }

    /**
     * Main method for updating a player's inventory slots
     * @param player
     */
    public void updatePlayerSlots(Player player) {
        Inventory inventory = player.getInventory();
        int slots = getPlayerSlots(player);

        ItemStack barrier = BarrierItem.getBarrierItem();

        // Messy but all it does is remove the current barriers
        for (int i = 0; i <= 40; i++) {
            ItemStack item = inventory.getItem(i);
            if (item == null) continue;
            ItemMeta meta1 = item.getItemMeta();
            if (meta1 == null) continue;
            if (meta1.hasDisplayName() && meta1.getDisplayName().equals(colorize("&0"))) {
                inventory.clear(i);
            }
        }

        for (int i = 9; i <= 9 + (slots - 1); i++) {
            if (i > 35) {
                int hotbarIndex = 8 - (i - 36);
                if (hotbarIndex < 0) break;
                inventory.setItem(hotbarIndex, barrier);
                continue;
            }
            inventory.setItem(i, barrier);
        }
    }

}
