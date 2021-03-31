package me.jharris.doubledrops.Listeners;

import me.jharris.doubledrops.Events.CustomEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class CustomEventListeners implements Listener {

    @EventHandler
    public void onGameEnd(CustomEvent e) {
        Bukkit.getServer().broadcastMessage("Game has ended!");
        Bukkit.getServer().broadcastMessage("Winner: " + e.getWinner().getName());
        Bukkit.getServer().broadcastMessage("Loser: " + e.getLoser().getName());
    }
}
