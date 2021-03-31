package me.jharris.doubledrops.Events;

import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class LaunchpadFallDamageEvent implements Listener {

    @EventHandler
    public void onFallDamage(EntityDamageEvent e) {

        if (e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
            if (LaunchpadPlayerMoveEvent.launchedplayers.contains(e.getEntity())) {
                e.setCancelled(true);
                LaunchpadPlayerMoveEvent.launchedplayers.remove(e.getEntity());
            }
        }
    }
}
