package me.jharris.doubledrops;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        if (!p.hasPermission("doubledrops.move")) {
            event.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You do not have permission to move in this world!");
        }
    }
}
