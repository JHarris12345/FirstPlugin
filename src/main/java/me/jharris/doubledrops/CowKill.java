package me.jharris.doubledrops;

import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.jetbrains.annotations.NotNull;

public class CowKill implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event) {

        if (event.getDamager() instanceof Player) {
            Entity mob = event.getEntity();
            Player player = (Player) event.getDamager();

            if (mob.getType() == EntityType.COW) {
                event.setDamage(0);
                player.sendMessage(ChatColor.RED + "You tried to kill the invincible cow. It won't work!");
                System.out.println(ChatColor.RED + player.getName() + " tried to kill a cow at " + mob.getWorld().getName() + "(" + mob.getLocation().getBlockX() + "," + mob.getLocation().getBlockZ() + ")");
            }
        }
    }
}
