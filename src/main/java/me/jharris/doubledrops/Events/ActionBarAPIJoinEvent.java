package me.jharris.doubledrops.Events;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

/* To utilise the action bar API, I downloaded the plugin from https://www.spigotmc.org/resources/actionbarapi-1-8-1-14-2.1315/
and stored the jar somewhere safe (java > maven). Then went to File > Project Structure > Libraries > Add a new one > Select the jar
> click ok then apply then ok
*/

public class ActionBarAPIJoinEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ActionBarAPI.sendActionBar(e.getPlayer(), ChatColor.GREEN + "Welcome to the server!");


    }
}
