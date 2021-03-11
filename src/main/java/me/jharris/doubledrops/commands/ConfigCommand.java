package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class ConfigCommand implements CommandExecutor {

    Plugin plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;
        String food = plugin.getConfig().getString("Food");
        int number = plugin.getConfig().getInt("Number");
        boolean Boolean = plugin.getConfig().getBoolean("Boolean");
        String thirditem = plugin.getConfig().getStringList("FoodList").get(2);

        player.sendMessage(ChatColor.GOLD + "The config options are " + food + " " + number + " " + Boolean + " " + thirditem);

        return true;
    }
}
