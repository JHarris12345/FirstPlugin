package me.jharris.doubledrops.commands;

import me.jharris.doubledrops.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class ClearCommand implements CommandExecutor {
    private final Main main;

    public ClearCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (player.isOp() || player.hasPermission("clearinventory")) {
                Inventory inv = player.getInventory();

                if (inv.isEmpty()) {
                    player.sendMessage(ChatColor.RED + "You have nothing to wipe!");
                    return true;

                } else {
                    inv.clear();
                    player.sendMessage(ChatColor.GREEN + "Your inventory has been cleared");
                    return true;
                }

            } else {
                player.sendMessage(ChatColor.RED + "You don't have permission to do this!");
                return true;
            }

        } else {
            main.getLogger().info("You have to be a player to clear your inventory!");
            return true;
        }
    }
}
