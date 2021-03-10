package me.jharris.doubledrops;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakBlock implements Listener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();

        if (blockBroken.getType() == Material.DIAMOND_ORE) {
            event.setDropItems(false);
            ItemStack acaciaBoat = new ItemStack(Material.ACACIA_BOAT, 2);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), acaciaBoat);
        }
    }
}
