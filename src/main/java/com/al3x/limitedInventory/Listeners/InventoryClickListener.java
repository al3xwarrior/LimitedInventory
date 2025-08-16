package com.al3x.limitedInventory.Listeners;

import com.al3x.limitedInventory.BarrierItem;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;

import static com.al3x.limitedInventory.Util.colorize;

public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) return;
        Player player = (Player) e.getWhoClicked();
        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        // Cancel if it's a hotbar swap (we cant get the item for some reason)
        if (e.getAction().equals(InventoryAction.HOTBAR_SWAP)) {
            e.setCancelled(true);
            player.sendMessage(colorize("&cIn order for Limited Inventory to work, swapping must be disabled. Sorry!"));
            return;
        }

        // If the item is a barrier
        if (!BarrierItem.isBarrierItem(e.getCurrentItem())) return;

        e.setCancelled(true);
        player.sendMessage(colorize("&cYou are not allowed to use that slot!"));
    }

}
