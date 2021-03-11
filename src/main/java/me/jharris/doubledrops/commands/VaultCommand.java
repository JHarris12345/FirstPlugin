package me.jharris.doubledrops.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VaultCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player player = (Player) sender;

            Inventory vault = Bukkit.createInventory(player, 9, ChatColor.DARK_GRAY + "Infinite Vault");

            ItemStack item1 = new ItemStack(Material.DIAMOND, 2);
            ItemStack item2 = new ItemStack(Material.GOLD_INGOT, 10);

            vault.setItem(4, item1);

            //This line will add "item2" into the 1st available slot//
            vault.addItem(item2);

            //This is the same as above but since they are the same item, it will attempt to stack the item 1st before finding the next available slot//
            vault.addItem(item2);

            //This is to get an array of items (multiple items in 1 variable). The "setContents" will override all previous code telling the plugin what items to put in each slot etc//
            ItemStack[] items = {item1, item2};

            item1.addUnsafeEnchantment(Enchantment.DURABILITY, 100);
            ItemMeta meta = item1.getItemMeta();
            meta.setDisplayName(ChatColor.AQUA + "Diamonddss");
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GREEN + "Lore line 1");
            lore.add(ChatColor.DARK_PURPLE + "Lore line 2");
            meta.setLore(lore);
            item1.setItemMeta(meta);

            vault.setContents(items);


            player.openInventory(vault);



        }else{
            System.out.println("You need to be a player to execute this command!");
        }



        return true;
    }
}
