package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AntiWither implements Listener {

    private Main plugin;

    public AntiWither(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onWitherCraft(CreatureSpawnEvent e){

        if(plugin.getConfig().getBoolean("WitherSpawning")){
            return;
        }


        if(e.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.BUILD_WITHER)){

            String message = plugin.getConfig().getString("WitherSpawnAttempt");

            for (Entity entity : e.getEntity().getNearbyEntities(5,5,5)) {
                if (entity instanceof Player){
                    Player player = (Player) entity;
                    e.setCancelled(true);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                }
            }


        }
    }
}
