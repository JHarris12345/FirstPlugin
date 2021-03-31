package me.jharris.doubledrops;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Random;

public class TeleportUtils {

    static Main plugin;

    public TeleportUtils(Main plugin) {
        this.plugin = plugin;
    }

    public static HashSet<Material> badblocks = new HashSet<>();

    static{
        badblocks.add(Material.LAVA);
    }

    // This class holds methods for generating locations for the randomTP
    public static Location generatelocation(Player player) {


        // Allow creation of a random number
        Random random = new Random();

        int x = 0;
        int y = 0;
        int z = 0;

        if (plugin.getConfig().getBoolean("RTP.worldborder")){
            // This will generate a number from 0 --> 1000
            x = random.nextInt(plugin.getConfig().getInt("RTP.border"));
            y = 150;
            z = random.nextInt(plugin.getConfig().getInt("RTP.border"));

        } else if (!plugin.getConfig().getBoolean("RTP.worldborder")){
            x = random.nextInt(25000);
            y = 150;
            z = random.nextInt(25000);
        }

        Location randomlocation = new Location(player.getWorld(),x, y, z);

        y = randomlocation.getWorld().getHighestBlockYAt(randomlocation);
        randomlocation.setY(y);

        while (!isLocationSafe(randomlocation)){
            randomlocation = generatelocation(player);
        }
        return randomlocation;
    }

    public static boolean isLocationSafe(Location location) {

        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x, y, z);
        Block below = location.getWorld().getBlockAt(x, y - 1, z);
        Block above = location.getWorld().getBlockAt(x, y + 1, z);

        return !(badblocks.contains(below.getType()) || (block.getType().isSolid()) || (above.getType().isSolid()));


    }

}
