package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BanGUIEvent implements Listener {

    Main plugin;

    public BanGUIEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        if (e.getView().getTitle().equalsIgnoreCase(ChatColor.BLUE + "Player List")) {
            if (e.getCurrentItem().getType() == Material.PLAYER_HEAD) {
                Player whotoban = player.getServer().getPlayer(e.getCurrentItem().getItemMeta().getDisplayName());

                plugin.openConfirmMenu(player, whotoban);


            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase("Ban this noob")) {
            switch(e.getCurrentItem().getType()){
                case BARRIER:
                    String name = e.getClickedInventory().getItem(4).getItemMeta().getDisplayName();
                    player.getServer().getBanList(BanList.Type.NAME).addBan(name, "Banned from the GUI", null, player.getName());
                    player.sendMessage("Banned player!");
                    break;
                case RED_WOOL:
                    plugin.openGUI(player);
                    break;
            }
            e.setCancelled(true);
        }

    }
}
