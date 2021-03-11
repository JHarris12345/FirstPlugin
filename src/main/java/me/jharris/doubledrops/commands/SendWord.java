package me.jharris.doubledrops.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SendWord implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 0){
                if(args[0].equalsIgnoreCase("hello")){
                    player.sendMessage("Hello there!");

                }else if(args[0].equalsIgnoreCase("tree")){
                    player.sendMessage("You are a tree");

                }else{
                    player.sendMessage(args[0] + " is NOT the word! Try again!");
                }


            }else{
                player.sendMessage(ChatColor.RED + "You need to put a word to send!");
                player.sendMessage("/sendword [word]");
            }



        }else {
            System.out.println("You need to be a player to execute this command!");
        }

        return true;
    }
}
