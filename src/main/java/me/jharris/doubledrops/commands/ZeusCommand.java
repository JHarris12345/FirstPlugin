package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ZeusCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("doubledrops.zeus")) {
                player.setInvulnerable(true);
                player.sendMessage(ChatColor.GREEN + "You are now invincible!");
            }else{
                player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
            }
            return true;

        } else {
            System.out.println("You must be a player to use this command!");
        }
        return true;
    }
}
