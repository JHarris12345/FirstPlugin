package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import net.md_5.bungee.api.ChatMessageType;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class TeleportBowEvent implements Listener {

    Main plugin;

    public TeleportBowEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBowShoot(ProjectileHitEvent e) {

        String message = plugin.getConfig().getString("TPBowTeleportMessage");

        if(e.getEntity() instanceof Arrow){
            Player player = (Player) e.getEntity().getShooter();
            Location location = e.getEntity().getLocation();


            player.teleport(location);
            player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1.0F, 1.0F);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
        }

    }
}
