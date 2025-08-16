package com.al3x.limitedInventory.Listeners;

import com.al3x.limitedInventory.BarrierItem;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) return;
        if (!e.getBlock().getType().equals(BarrierItem.getBarrierItem().getType())) return;
        e.setCancelled(true);
    }

}
