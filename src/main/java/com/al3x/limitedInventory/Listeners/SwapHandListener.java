package com.al3x.limitedInventory.Listeners;

import com.al3x.limitedInventory.BarrierItem;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

import static com.al3x.limitedInventory.Util.colorize;

public class SwapHandListener implements Listener {

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        Player player = e.getPlayer();
        if (player.getGameMode().equals(GameMode.CREATIVE)) return;

        ItemStack mainHandItem = e.getMainHandItem();
        ItemStack offHandItem = e.getOffHandItem();

        if (BarrierItem.isBarrierItem(mainHandItem) || BarrierItem.isBarrierItem(offHandItem)) {
            e.setCancelled(true);
            player.sendMessage(colorize("&cYou can't this item!"));
        }
    }

}
