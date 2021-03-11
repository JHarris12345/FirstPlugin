package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FlyCommand implements CommandExecutor {

    private ArrayList<Player> flightlist = new ArrayList<>();
    private Main plugin;

    //Right click --> generate --> constructor//
    public FlyCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length == 0) {
                flyMethod(player);

            }else if(args.length == 1){
                if(player.hasPermission("flyplugin.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    flyMethod(target);
                }else{
                    player.sendMessage(ChatColor.RED + "You don't have permission to make other players fly!");
                }
            }

        }else {
            System.out.println("You must be a player to do this!");
        }


        return true;
    }
    //Stop dupe code by making a method that you can sub variables in for like above (putting flyMethod(target) replaces all player.X with target.X etc//
    private void flyMethod(Player player) {
        String flyenablemessage = plugin.getConfig().getString("FlyEnable");
        String flydisablemessage = plugin.getConfig().getString("FlyDisable");

        if (player.hasPermission("flyplugin.fly")) {

            if (flightlist.contains(player)) {
                flightlist.remove(player);
                player.setAllowFlight(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', flydisablemessage));
            } else {
                flightlist.add(player);
                player.setAllowFlight(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', flyenablemessage));
            }

        } else {
            player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
        }
    }
}
