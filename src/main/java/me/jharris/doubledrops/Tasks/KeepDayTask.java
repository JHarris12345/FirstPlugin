package me.jharris.doubledrops.Tasks;

import me.jharris.doubledrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class KeepDayTask extends BukkitRunnable {

    Main plugin;

    public KeepDayTask(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        Bukkit.getServer().getWorld("world").setTime(6000);
    }
}
