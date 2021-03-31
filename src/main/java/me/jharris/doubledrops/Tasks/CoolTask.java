package me.jharris.doubledrops.Tasks;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CoolTask extends BukkitRunnable {

    Main plugin;

    public CoolTask(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        System.out.println(ChatColor.GREEN + "Task run");

    }
}
