package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.awt.print.PrinterGraphics;
import java.beans.Expression;
import java.lang.reflect.Array;
import java.sql.SQLOutput;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;


public class XrayDetector implements Listener {

    Main plugin;
    public static List<String> rareores = new ArrayList<String>();


    public XrayDetector(Main plugin) {
        this.plugin = plugin;
        for (String i : plugin.getConfig().getStringList("Ores"))rareores.add(i);
    }


    HashMap<String, Integer> xrayers = new HashMap<String, Integer>();


    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent event) {

        Block block = event.getBlock();
        String player = (event.getPlayer().getName());
        Integer current = xrayers.get(player);
        Player staff = Bukkit.getPlayer("Jharris12345");


            if (rareores.contains(block.getType().name())) {
                if (!xrayers.containsKey(player)) {

                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (xrayers.get(player) > 9) {
                                Integer blocknumber = xrayers.get(player);
                                xrayers.remove(player);
                                Bukkit.broadcast(ChatColor.RED + player + " might be using xray! (" + blocknumber + " rare ores mined in the last 10s)", "xray.alerts");
                            } else {
                                xrayers.remove(player);
                                staff.sendMessage(ChatColor.RED + player + " wasnt using xray in this 10s time frame!");
                            }
                        }
                    }, 200L);

                    xrayers.put(player, 1);
                    System.out.println(xrayers);

                } else {
                    xrayers.put(player, current + 1);
                    System.out.println(xrayers);
                }
            } else {
                staff.sendMessage("No match " + block.getType().name());
                System.out.println(rareores);


            }
            return;
        }
    }
