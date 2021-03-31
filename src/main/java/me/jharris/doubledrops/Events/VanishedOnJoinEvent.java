package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import me.jharris.doubledrops.commands.VanishCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class VanishedOnJoinEvent implements Listener {

    Main plugin;

    public VanishedOnJoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public  void playerJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        for (int i = 0; i < plugin.invislist.size(); i++) {
            player.hidePlayer(plugin, plugin.invislist.get(i));
        }
    }
}
