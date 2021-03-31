package me.jharris.doubledrops.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TeleportCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0) {
                player.sendMessage("Provide a player to teleport to!");

            } else if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);

                try{
                    player.teleport(target.getLocation());
                } catch (NullPointerException e){
                    player.sendMessage("You cannot teleport to a player that does not exist!");
                }


            } else if (args.length ==2) {
                Player playertosend = Bukkit.getPlayer(args[0]);
                Player target = Bukkit.getPlayer(args[1]);

                try{
                    playertosend.teleport(target.getLocation());
                } catch (NullPointerException e){
                    player.sendMessage("You cannot teleport to a player that does not exist!");
                }

            }


        }
        return true;
    }
}
