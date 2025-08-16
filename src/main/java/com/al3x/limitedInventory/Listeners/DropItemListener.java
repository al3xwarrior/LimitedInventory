package com.al3x.limitedInventory.Listeners;

import com.al3x.limitedInventory.BarrierItem;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class DropItemListener implements Listener {

    @EventHandler
    public void onDropItem(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        ItemStack item = e.getItemDrop().getItemStack();
        if (!BarrierItem.isBarrierItem(item)) return;

        e.setCancelled(true);
        player.sendMessage("§cYou are not allowed to drop that item!");

    }

}
