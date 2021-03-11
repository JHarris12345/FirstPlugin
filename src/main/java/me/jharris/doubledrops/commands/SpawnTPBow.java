package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SpawnTPBow implements CommandExecutor {

    private Main plugin;

    public SpawnTPBow(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            String noperm = plugin.getConfig().getString("NoPermission");
            String bowname = plugin.getConfig().getString("TPBowDisplayname");

            if(player.hasPermission("tpbow.spawnbow")){
                plugin.givePlayerBow(player);

            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
            }



        }else{
            System.out.println("You must be a player to do this!");
        }






        return true;
    }
}
