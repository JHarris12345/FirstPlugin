package me.jharris.doubledrops;

import me.jharris.doubledrops.Events.*;
import me.jharris.doubledrops.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello World!");

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new GUIClickEvent(), this);
        getServer().getPluginManager().registerEvents(new AntiWither(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new TeleportBowEvent(this), this);
        getServer().getPluginManager().registerEvents(new BowJoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new XrayDetector(), this);
        getServer().getPluginManager().registerEvents(new CowKill(), this);
        getServer().getPluginCommand("wipeme").setExecutor(new ClearCommand(this));
        getServer().getPluginCommand("TPBow").setExecutor(new SpawnTPBow(this));
        getServer().getPluginCommand("gui").setExecutor(new GUI(this));
        getCommand("zeus").setExecutor(new ZeusCommand());
        getCommand("SendWord").setExecutor(new SendWord());
        getCommand("SendWordTo").setExecutor(new SendTargetedWord());
        getCommand("ViewConfig").setExecutor(new ConfigCommand());
        getCommand("Vault").setExecutor(new VaultCommand());
        getCommand("Armorstand").setExecutor(new ArmorStandCommand());
        getCommand("fly!").setExecutor(new FlyCommand(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    public void givePlayerBow(Player player){
        String noperm = this.getConfig().getString("NoPermission");
        String bowname = this.getConfig().getString("TPBowDisplayname");

        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', bowname));
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "Teleport where you shoot!");
        meta.setLore(lore);
        bow.setItemMeta(meta);
        player.getInventory().addItem(bow);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equals("setFood")) {
            getConfig().set("Food", "Shrimp");
        }

        if (command.getName().equals("ddreload")) {
            Player player = (Player) sender;

            reloadConfig();
            player.sendMessage(ChatColor.GREEN + "The config has been reloaded!");
        }
        return true;
    }


}
