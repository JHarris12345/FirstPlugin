package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BowJoinEvent implements Listener {

    Main plugin;

    public BowJoinEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void bowOnJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();

        String noperm = plugin.getConfig().getString("NoPermission");
        String bowname = plugin.getConfig().getString("TPBowDisplayname");

        //Putting "getBoolean("GiveTPBowOnJoin")){" is the same as saying if it is equal to true. It defaults to assuming true//
        if(plugin.getConfig().getBoolean("GiveTPBowOnJoin")){
            plugin.givePlayerBow(player);

        }


    }


}
