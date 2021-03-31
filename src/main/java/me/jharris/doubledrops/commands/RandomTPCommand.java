package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import me.jharris.doubledrops.TeleportUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class RandomTPCommand implements CommandExecutor {

    Main plugin;

    public RandomTPCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.teleport(TeleportUtils.generatelocation(player));

            } else if (args.length == 1){
                if (player.hasPermission("rtp.other")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    target.teleport(TeleportUtils.generatelocation(target));
                }
            }

        }



        return true;
    }
}
