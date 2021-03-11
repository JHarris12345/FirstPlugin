package me.jharris.doubledrops;

import me.jharris.doubledrops.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello World!");

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new XrayDetector(), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(), this);
        getServer().getPluginManager().registerEvents(new CowKill(), this);
        getServer().getPluginCommand("wipeme").setExecutor(new ClearCommand(this));
        getCommand("zeus").setExecutor(new ZeusCommand());
        getCommand("SendWord").setExecutor(new SendWord());
        getCommand("SendWordTo").setExecutor(new SendTargetedWord());
        getCommand("ViewConfig").setExecutor(new ConfigCommand());
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equals("setFood")) {
            getConfig().set("Food", "Shrimp");
        }

        return true;
    }
}
