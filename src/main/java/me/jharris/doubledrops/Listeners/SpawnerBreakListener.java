package me.jharris.doubledrops.Listeners;

import me.jharris.doubledrops.Events.SilkSpawwnersBlockSpawnerEvent;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;

public class SpawnerBreakListener implements Listener {

    @EventHandler
    public void onSpawnerBreak(SilkSpawwnersBlockSpawnerEvent e) {

        CreatureSpawner cs = (CreatureSpawner) e.getSpawner().getState();
        ItemStack spawnertogive = new ItemStack(Material.SPAWNER);
        BlockStateMeta meta = (BlockStateMeta) spawnertogive.getItemMeta();
        CreatureSpawner css = (CreatureSpawner) meta.getBlockState();

        css.setSpawnedType(cs.getSpawnedType());
        meta.setBlockState(css);
        spawnertogive.setItemMeta(meta);

        e.getBreaker().getInventory().addItem(spawnertogive);

        e.getBreaker().sendMessage("You have silked a spawner!");



    }
}
