package me.jharris.doubledrops.Events;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ArmorStandGUIMenuHandler implements Listener {

    Main plugin;

    public ArmorStandGUIMenuHandler(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        // Menu List
        final String MAIN_MENU = ChatColor.BLUE + "Armor Stand GUI";
        final String CREATE_MENU = ChatColor.GREEN + "Create an Armor Stand";
        final String CONFIRM_MENU = ChatColor.GREEN + "Confirm Option";
        final String ARMOR_MENU = ChatColor.GOLD + "Choose some armor";

        if (e.getView().getTitle().equalsIgnoreCase(MAIN_MENU)) {
            switch (e.getCurrentItem().getType()) {
                case ARMOR_STAND:
                    player.sendMessage("Opened Armor Stand Create Menu");
                    player.closeInventory();
                    // Open the armor stand inventory menu
                    plugin.openCreateMenu(player);
                    break;
                case BARRIER:
                    player.sendMessage("Closing Main Menu");
                    player.closeInventory();
                    break;
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(CREATE_MENU)) {

            if (!plugin.armorstands.containsKey(player)) {
                ArmorStand stand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                stand.setVisible(false);
                plugin.armorstands.put(player, stand);
            }

            switch (e.getCurrentItem().getType()) {
                case ARMOR_STAND:
                    player.sendMessage("Add arms?");
                    plugin.openConfirmMenu(player, Material.ARMOR_STAND);
                    break;
                case BEACON:
                    player.sendMessage("Add Glow?");
                    plugin.openConfirmMenu(player, Material.BEACON);
                    break;
                case DIAMOND_CHESTPLATE:
                    player.sendMessage("Choose Armor?");
                    // Armor select menu
                    plugin.openArmorMenu(player);
                    break;
                case STONE_SLAB:
                    player.sendMessage("Add base?");
                    plugin.openConfirmMenu(player, Material.STONE_SLAB);
                    break;
                case LIME_WOOL:
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("create-message")));
                    player.closeInventory();
                    if (plugin.armorstands.containsKey(player)) {
                        ArmorStand stand = plugin.armorstands.get(player);
                        stand.setVisible(true);
                        plugin.armorstands.remove(player);
                    }
                    break;
                case RED_WOOL:
                    player.sendMessage("Cancelled creation");
                    player.closeInventory();
                    if (plugin.armorstands.containsKey(player)) {
                        ArmorStand stand = plugin.armorstands.get(player);
                        stand.remove();
                        plugin.armorstands.remove(player);
                    }
                    break;
            }
            e.setCancelled(true);
        } else if (e.getView().getTitle().equalsIgnoreCase(CONFIRM_MENU)) {
            if (e.getClickedInventory().contains(Material.ARMOR_STAND)) {
                switch (e.getCurrentItem().getType()){
                    case LIME_WOOL:
                        player.sendMessage("Option confirmed");
                        if (plugin.armorstands.containsKey(player)) {
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setArms(true);
                        }
                        plugin.openCreateMenu(player);
                        break;
                    case RED_WOOL:
                        player.sendMessage("Option cancelled");
                        if (plugin.armorstands.containsKey(player)) {
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setArms(false);
                        }
                        plugin.openCreateMenu(player);
                        break;
                }
            } else if (e.getClickedInventory().contains(Material.BEACON)) {
                switch (e.getCurrentItem().getType()){
                    case LIME_WOOL:
                        player.sendMessage("Option confirmed");
                        if (plugin.armorstands.containsKey(player)) {
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setGlowing(true);
                        }
                        plugin.openCreateMenu(player);
                        break;
                    case RED_WOOL:
                        player.sendMessage("Option cancelled");
                        if (plugin.armorstands.containsKey(player)) {
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setGlowing(false);
                        }
                        plugin.openCreateMenu(player);
                        break;
                }
            } else if (e.getClickedInventory().contains(Material.STONE_SLAB)) {
                switch (e.getCurrentItem().getType()){
                    case LIME_WOOL:
                        player.sendMessage("Option confirmed");
                        if (plugin.armorstands.containsKey(player)) {
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setBasePlate(true);
                        }
                        plugin.openCreateMenu(player);
                        break;
                    case RED_WOOL:
                        player.sendMessage("Option cancelled");
                        if (plugin.armorstands.containsKey(player)) {
                            ArmorStand stand = plugin.armorstands.get(player);
                            stand.setBasePlate(false);
                        }
                        plugin.openCreateMenu(player);
                        break;
                }
            }
            e.setCancelled(true);

        } else if (e.getView().getTitle().equalsIgnoreCase(ARMOR_MENU)) {
            if (plugin.armorstands.containsKey(player)) {
                ArmorStand stand = plugin.armorstands.get(player);
                switch (e.getCurrentItem().getType()) {
                    case  DIAMOND_HELMET:
                        if (stand.getHelmet().getType().equals(Material.DIAMOND_HELMET)) {
                            stand.setHelmet(null);
                            player.sendMessage("Removed");
                        } else {
                            stand.setHelmet(new ItemStack(Material.DIAMOND_HELMET));
                            player.sendMessage("Added");
                        }
                        break;
                    case  DIAMOND_CHESTPLATE:
                        if (stand.getChestplate().getType().equals(Material.DIAMOND_CHESTPLATE)) {
                            stand.setChestplate(null);
                            player.sendMessage("Removed");
                        } else {
                            stand.setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
                            player.sendMessage("Added");
                        }
                        break;
                    case  DIAMOND_LEGGINGS:
                        if (stand.getLeggings().getType().equals(Material.DIAMOND_LEGGINGS)) {
                            stand.setLeggings(null);
                            player.sendMessage("Removed");
                        } else {
                            stand.setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
                            player.sendMessage("Added");
                        }
                        break;
                    case  DIAMOND_BOOTS:
                        if (stand.getBoots().getType().equals(Material.DIAMOND_BOOTS)) {
                            stand.setBoots(null);
                            player.sendMessage("Removed");
                        } else {
                            stand.setBoots(new ItemStack(Material.DIAMOND_BOOTS));
                            player.sendMessage("Added");
                        }
                        break;
                    case LIME_WOOL:
                        player.sendMessage("Armor confirmed");
                        plugin.openCreateMenu(player);
                }
            }
            e.setCancelled(true);
        }
    }
}


