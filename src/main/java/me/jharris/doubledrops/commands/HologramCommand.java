package me.jharris.doubledrops.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HologramCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(player.getLocation().add(0, -2, 0), EntityType.ARMOR_STAND);
            hologram.setVisible(false);
            hologram.setGravity(false);
            hologram.setCustomNameVisible(true);
            hologram.setCustomName(ChatColor.AQUA + "Hologram");
            // Second line
            ArmorStand hologram2 = (ArmorStand) player.getWorld().spawnEntity(hologram.getLocation().add(0, -0.3, 0), EntityType.ARMOR_STAND);
            hologram2.setVisible(false);
            hologram2.setGravity(false);
            hologram2.setCustomNameVisible(true);
            hologram2.setCustomName(ChatColor.GOLD + "Line 2");
        }
        return true;
    }
}
