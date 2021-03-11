package me.jharris.doubledrops.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class BreakBlock implements Listener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Block blockBroken = event.getBlock();
        Player player = (event.getPlayer());

        if (blockBroken.getType() == Material.DIAMOND_ORE) {
            event.setDropItems(false);
            ItemStack coal = new ItemStack(Material.COAL, 10);
            blockBroken.getWorld().dropItemNaturally(blockBroken.getLocation(), coal);
            event.setExpToDrop(100000);
            player.sendMessage(ChatColor.GOLD + "You mined diamond ore and got 10 coal and 100k EXP!");
            System.out.println(ChatColor.GREEN + player.getName() + " mined diamond ore at " + blockBroken.getWorld().getName() + ": " + blockBroken.getX() + "," + blockBroken.getZ() + "!");
        }
    }
}
