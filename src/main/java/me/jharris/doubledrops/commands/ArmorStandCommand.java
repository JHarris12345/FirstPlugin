package me.jharris.doubledrops.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;
import org.jetbrains.annotations.NotNull;

public class ArmorStandCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){

            Player player = (Player) sender;

            ArmorStand armorstand = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
            armorstand.setHelmet(new ItemStack(Material.REDSTONE_BLOCK));
            armorstand.setGlowing(true);
            armorstand.setItemInHand(new ItemStack(Material.DIAMOND_AXE));
            armorstand.setArms(true);
            //You can go to https://haselkern.com/Minecraft-ArmorStand/ to get angles for the armor stand parts (in degrees like below)//
            armorstand.setRightArmPose(new EulerAngle(Math.toRadians(0), Math.toRadians(0), Math.toRadians(0)));


        }

        return true;
    }
}
