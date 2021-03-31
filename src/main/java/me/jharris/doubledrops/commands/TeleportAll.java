package me.jharris.doubledrops.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportAll implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (Bukkit.getOnlinePlayers().size() == 1) {
                player.sendMessage("No other players are on right now");
            } else {
                int numberofplayers = 0;
                for (Player p : Bukkit.getServer().getOnlinePlayers()) {
                    p.teleport(player.getLocation());
                    numberofplayers++; //Adds 1 to the int set above (0) every time it finds a "p" (online player)
                    player.sendMessage("Teleported " + (numberofplayers - 1 )+ " players to you");
                }
            }
        }


        return true;
    }
}
