package me.jharris.doubledrops.Listeners;

import me.jharris.doubledrops.Events.SilkSpawwnersBlockSpawnerEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class SilkSpawnersBlockBreakListener implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {

        Block blockbroken = e.getBlock();

        if (blockbroken.getType() == (Material.SPAWNER) && e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && e.getPlayer().hasPermission("silktouch.use")) {
            Bukkit.getServer().getPluginManager().callEvent(new SilkSpawwnersBlockSpawnerEvent(e.getPlayer(), blockbroken));

        } else if (blockbroken.getType() == (Material.SPAWNER) && e.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && !(e.getPlayer().hasPermission("silktouch.use"))) {
            e.getPlayer().sendMessage("You do not have permission to get spawners via silktouch!");
            e.setCancelled(true);

        }
    }
}
