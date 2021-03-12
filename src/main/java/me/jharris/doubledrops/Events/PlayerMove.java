package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import me.jharris.doubledrops.commands.ConfigCommand;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMove implements Listener {

    Main plugin;

    public PlayerMove(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();

        if(plugin.getConfig().getBoolean("PlayerMove")){
            return;
        }

        if(event.getFrom().getBlockX() == event.getTo().getBlockX() && event.getFrom().getBlockZ() == event.getTo().getBlockZ() && event.getFrom().getBlockY() == event.getTo().getBlockY()) {
            return;
        }

        if (!p.hasPermission("doubledrops.move")) {
            event.setCancelled(true);
            p.sendMessage(ChatColor.RED + "You do not have permission to move in this world!");
        }
    }
}
