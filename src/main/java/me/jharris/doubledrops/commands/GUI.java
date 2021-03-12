package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GUI implements CommandExecutor {

    Main plugin;

    public GUI(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(player, 9, ChatColor.DARK_GRAY + "Custom GUI");

            ItemStack suicide = new ItemStack(Material.TNT);
            ItemStack feed = new ItemStack(Material.COOKED_BEEF);
            ItemStack sword = new ItemStack(Material.DIAMOND_SWORD);

            ItemMeta suicidemeta = suicide.getItemMeta();
            suicidemeta.setDisplayName(ChatColor.RED + "Suicide");
            ArrayList<String> suicidelore = new ArrayList<>();
            suicidelore.add(ChatColor.GOLD + "Kill yourself");
            suicidemeta.setLore(suicidelore);
            suicide.setItemMeta(suicidemeta);

            ItemMeta feedmeta = feed.getItemMeta();
            feedmeta.setDisplayName(ChatColor.LIGHT_PURPLE + "Feed");
            ArrayList<String> feedlore = new ArrayList<>();
            feedlore.add(ChatColor.GOLD + "Full hunger");
            feedmeta.setLore(feedlore);
            feed.setItemMeta(feedmeta);

            ItemMeta swordmeta = sword.getItemMeta();
            swordmeta.setDisplayName(ChatColor.AQUA + "Sword");
            ArrayList<String> swordlore = new ArrayList<>();
            swordlore.add(ChatColor.GOLD + "Get a sword");
            swordmeta.setLore(swordlore);
            sword.setItemMeta(swordmeta);

            ItemStack[] menuitems = {suicide, feed, sword};
            gui.setContents(menuitems);

            player.openInventory(gui);
        }


        return true;
    }
}
