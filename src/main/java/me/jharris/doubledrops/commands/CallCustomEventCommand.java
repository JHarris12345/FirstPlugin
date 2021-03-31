package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Events.CustomEvent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CallCustomEventCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 1){
                Player loser = Bukkit.getPlayer(args[0]);

                // Calling custom event
                Bukkit.getServer().getPluginManager().callEvent(new CustomEvent(player, loser, 30));
            }
        }



        return true;
    }
}
