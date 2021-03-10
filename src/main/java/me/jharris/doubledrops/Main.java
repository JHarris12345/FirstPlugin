package me.jharris.doubledrops;

import me.jharris.doubledrops.commands.ClearCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello World!");

        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginCommand("wipeme").setExecutor(new ClearCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
