package me.jharris.doubledrops.Events;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.lang.reflect.Array;

public class XrayDetector implements Listener {
    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {
        Block block = event.getBlock();
        Player player = (event.getPlayer());

        if (block.getType() == Material.DIAMOND_ORE || block.getType() == Material.GOLD_ORE) {
            Bukkit.broadcast(ChatColor.RED + player.getName() + " might be using xray at " + block.getWorld().getName() + "(" + block.getX() + "," + block.getY() + "," + block.getZ() + ")", "xray.alerts");




        }
    }
}