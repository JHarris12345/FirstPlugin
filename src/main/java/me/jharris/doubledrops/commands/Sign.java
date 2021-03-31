package me.jharris.doubledrops.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Sign implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length != 2) {
                player.sendMessage("You need to specify a line and a word!");
                player.sendMessage("Usage: /sign [line] [text]");

            } else if (args.length == 2) {
                player.getWorld().getBlockAt(player.getLocation()).setType(Material.OAK_SIGN);
                org.bukkit.block.Sign sign = (org.bukkit.block.Sign) player.getWorld().getBlockAt(player.getLocation()).getState();

                int line = Integer.parseInt(args[0]) - 1;
                String word = args[1];
                sign.setLine(line, word);
                sign.update();

            }

          /* player.getWorld().getBlockAt(player.getLocation()).setType(Material.BIRCH_SIGN);

           org.bukkit.block.Sign sign1 = (org.bukkit.block.Sign) player.getWorld().getBlockAt(player.getLocation()).getState();
           sign1.setLine(0, "Line 1!");
           sign1.update();
           */


        }



        return true;
    }
}
