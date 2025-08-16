package com.al3x.limitedInventory.Listeners;

import com.al3x.limitedInventory.BarrierItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        e.getDrops().removeIf(BarrierItem::isBarrierItem);
    }

}
