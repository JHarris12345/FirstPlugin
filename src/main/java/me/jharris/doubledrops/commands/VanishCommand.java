package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class VanishCommand implements CommandExecutor {

    Main plugin;

    public VanishCommand(Main plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (plugin.invislist.contains(player)){
                plugin.invislist.remove(player);
                player.sendMessage("You are now visible");
                player.setInvisible(false); //Sets your entity invisible but doesnt set you invisible to commands / tab etc
                for (Player people : Bukkit.getOnlinePlayers()) { //Sets you invisible to commands / tab etc
                    people.showPlayer(plugin, player);
                }

            } else if (!plugin.invislist.contains(player)) {
                plugin.invislist.add(player);
                player.sendMessage("You are now invisible");
                //player.setInvisible(true);
                for (Player people : Bukkit.getOnlinePlayers()) {
                    people.hidePlayer(plugin, player);
                }
            }


        }





        return true;
    }
}
