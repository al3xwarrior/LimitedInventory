package com.al3x.limitedInventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.al3x.limitedInventory.Util.colorize;

public class BarrierItem {

    private static ItemStack barrierItem = new ItemStack(Material.STRUCTURE_VOID);
    static {
        ItemMeta meta = barrierItem.getItemMeta();
        meta.setDisplayName(colorize("&0"));
        barrierItem.setItemMeta(meta);
    }

    public static ItemStack getBarrierItem() {
        return barrierItem;
    }

    public static boolean isBarrierItem(ItemStack item) {
        return item != null && item.getType() == Material.STRUCTURE_VOID && item.hasItemMeta() && item.getItemMeta().hasDisplayName() && item.getItemMeta().getDisplayName().equals(colorize("&0"));
    }

}
