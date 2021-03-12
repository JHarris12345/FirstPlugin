package me.jharris.doubledrops.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIClickEvent implements Listener {

    @EventHandler
    public void onGUIClick(InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_GRAY + "Custom GUI")) {

            switch (e.getCurrentItem().getType()){
                case TNT:
                    player.closeInventory();
                    player.setHealth(0.0);
                    player.sendMessage(ChatColor.RED + "You just killed yourself!");
                    break;
                case COOKED_BEEF:
                    player.closeInventory();
                    player.setHealth(20);
                    player.sendMessage(ChatColor.GREEN + "Yum!");
                    break;
                case DIAMOND_SWORD:
                    player.closeInventory();
                    player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
                    player.sendMessage(ChatColor.GOLD + "Don't hurt yourself!");
                    break;
            }


            e.setCancelled(true);
        }
    }
}
