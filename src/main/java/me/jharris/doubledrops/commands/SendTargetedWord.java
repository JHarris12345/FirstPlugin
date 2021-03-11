package me.jharris.doubledrops.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SendTargetedWord implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (sender instanceof Player) {
            if (args.length > 1) {
                Player target = Bukkit.getPlayerExact(args[0]);

                if (target instanceof Player) {
                    target.sendMessage(ChatColor.GREEN + player.getName() + " has just sent you the word '" + args[1] + "'!");
                } else {
                    player.sendMessage(ChatColor.RED + args[0] + " is not a valid player!");
                }


            } else {
                player.sendMessage(ChatColor.RED + "That is not the correct format!");
                player.sendMessage("/sendword [player] [word]");
            }

        }
        return true;
    }
}
