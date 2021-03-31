package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.ArrayList;

public class LaunchpadPlayerMoveEvent implements Listener {

    Main plugin;

    public LaunchpadPlayerMoveEvent(Main plugin) {
        this.plugin = plugin;
    }

    public static ArrayList<Player> launchedplayers = new ArrayList<>();

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent e) {

        if (plugin.getConfig().getBoolean("Launchpad.enabled")) {
            Player p = e.getPlayer();
            Location blockunder = p.getLocation();
            blockunder.setY(blockunder.getY() - 1);

            if (p.getLocation().getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("Launchpad.topblock"))) && blockunder.getBlock().getType().equals(Material.valueOf(plugin.getConfig().getString("Launchpad.bottomblock")))){
                p.setVelocity(p.getLocation().getDirection().multiply(2).setY(1));
                launchedplayers.add(p);


            } else return;
        }
    }
}
