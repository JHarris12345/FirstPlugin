package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class MOTDJoinEvent implements Listener {

    Main plugin;

    public MOTDJoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        if (plugin.getConfig().getBoolean("Motd.enabled")) {
            for (int i = 0; i < plugin.getConfig().getList("Motd.message").size(); i++) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(plugin.getConfig().getList("Motd.message").get(i))));
            }

        } else return;



    }
}
