package me.jharris.doubledrops;

import me.jharris.doubledrops.Events.*;
import me.jharris.doubledrops.Files.CustomConfig;
import me.jharris.doubledrops.Listeners.CustomEventListeners;
import me.jharris.doubledrops.Listeners.GoodWeatherListener;
import me.jharris.doubledrops.Listeners.SilkSpawnersBlockBreakListener;
import me.jharris.doubledrops.Listeners.SpawnerBreakListener;
import me.jharris.doubledrops.Tasks.CoolTask;
import me.jharris.doubledrops.Tasks.KeepDayTask;
import me.jharris.doubledrops.commands.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;

public final class Main extends JavaPlugin {

    public HashMap<Player, ArmorStand> armorstands = new HashMap<>();
    public ArrayList<Player> invislist = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Hello World!");

        // Events
        getServer().getPluginManager().registerEvents(new BreakBlock(), this);
        getServer().getPluginManager().registerEvents(new MOTDJoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new LaunchpadFallDamageEvent(), this);
        getServer().getPluginManager().registerEvents(new LaunchpadPlayerMoveEvent(this), this);
        getServer().getPluginManager().registerEvents(new SignEvent(), this);
        getServer().getPluginManager().registerEvents(new GUIClickEvent(), this);
        getServer().getPluginManager().registerEvents(new AntiWither(this), this);
        getServer().getPluginManager().registerEvents(new VanishedOnJoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new BanGUIEvent(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMove(this), this);
        getServer().getPluginManager().registerEvents(new TeleportBowEvent(this), this);
        getServer().getPluginManager().registerEvents(new BowJoinEvent(this), this);
        //getServer().getPluginManager().registerEvents(new XrayDetector(this), this);
        getServer().getPluginManager().registerEvents(new CowKill(), this);
        getServer().getPluginManager().registerEvents(new ArmorStandGUIMenuHandler(this), this);

        // Commands
        getServer().getPluginCommand("wipeme").setExecutor(new ClearCommand(this));
        getServer().getPluginCommand("TPBow").setExecutor(new SpawnTPBow(this));
        getServer().getPluginCommand("rtp").setExecutor(new RandomTPCommand(this));
        getServer().getPluginCommand("gui").setExecutor(new GUI(this));
        getCommand("zeus").setExecutor(new ZeusCommand());
        getCommand("removeall").setExecutor(new RemoveEveryEntityCommand());
        getCommand("gameover").setExecutor(new CallCustomEventCommand());
        getCommand("invis").setExecutor(new VanishCommand(this));
        getCommand("goto").setExecutor(new TeleportCommand());
        getCommand("bringall").setExecutor(new TeleportAll());
        getCommand("bangui").setExecutor(new BanCommand(this));
        getCommand("astandgui").setExecutor(new ArnorStandGUICommand(this));
        getCommand("SendWord").setExecutor(new SendWord());
        getCommand("SendWordTo").setExecutor(new SendTargetedWord());
        getCommand("ViewConfig").setExecutor(new ConfigCommand());
        getCommand("Vault").setExecutor(new VaultCommand());
        getCommand("hologram").setExecutor(new HologramCommand());
        getCommand("Armorstand").setExecutor(new ArmorStandCommand());
        getCommand("fly!").setExecutor(new FlyCommand(this));
        getCommand("message").setExecutor(new CustomConfigMessage());
        getCommand("sign").setExecutor(new Sign());

        // Teleport Utils
        TeleportUtils utils = new TeleportUtils(this);

        // Listener (same as events)
        getServer().getPluginManager().registerEvents(new CustomEventListeners(),this);
        getServer().getPluginManager().registerEvents(new SilkSpawnersBlockBreakListener(), this);
        getServer().getPluginManager().registerEvents(new SpawnerBreakListener(), this);
        getServer().getPluginManager().registerEvents(new GoodWeatherListener(), this);

        // Config file
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        // Custom config files
        CustomConfig.setup();
        CustomConfig.get().addDefault("Message", "This is the default message");
        CustomConfig.get().options().copyDefaults(true);
        CustomConfig.save();

        // Running tasks immediately on startup
        BukkitTask cooltask = new CoolTask(this).runTaskLater(this, 100); // This will run the CoolTask 5 seconds after the plugin starts up
        //BukkitTask cooltask2 = new CoolTask(this).runTaskTimer(this, 0, 100); // This will run the CoolTask immediately on startup and then every 5s after
        BukkitTask keepdaytask = new KeepDayTask(this).runTaskTimer(this, 0, 100);

    }

    public void openGUI(Player player) {
        ArrayList<Player> playerlist = new ArrayList<>(Bukkit.getOnlinePlayers());

        Inventory bangui = Bukkit.createInventory(player, 45, ChatColor.BLUE + "Player List");

        // For every player add their name and head to the GUI
        for (int i = 0; i < playerlist.size(); i++) {
            ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
            ItemMeta meta = playerhead.getItemMeta();
            meta.setDisplayName(playerlist.get(i).getPlayer().getName());
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.AQUA + "Player Health: " + playerlist.get(i).getHealth());
            lore.add(ChatColor.AQUA + "Player EXP: " + playerlist.get(i).getExp());
            meta.setLore(lore);
            playerhead.setItemMeta(meta);

            bangui.addItem(playerhead);

            player.openInventory(bangui);
        }
    }

    public void openConfirmMenu(Player player, Player playertoban) {

        Player banme = playertoban;

        Inventory banPlayerMenu = Bukkit.createInventory(player, 9, "Ban this noob");

        // Ban Player
        ItemStack ban = new ItemStack(Material.BARRIER, 1);
        ItemMeta banmeta = ban.getItemMeta();
        banmeta.setDisplayName(ChatColor.RED + "Ban");
        ban.setItemMeta(banmeta);
        banPlayerMenu.setItem(0, ban);

        // Add Player
        ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta playermeta = playerhead.getItemMeta();
        playermeta.setDisplayName(banme.getName());
        playerhead.setItemMeta(playermeta);
        banPlayerMenu.setItem(4, playerhead);

        // Cancel Option
        ItemStack cancel = new ItemStack(Material.RED_WOOL, 1);
        ItemMeta cancelmeta = cancel.getItemMeta();
        cancelmeta.setDisplayName(ChatColor.RED + "Cancel");
        cancel.setItemMeta(cancelmeta);
        banPlayerMenu.setItem(8, cancel);

        player.openInventory(banPlayerMenu);
    }

    public void givePlayerBow(Player player){
        String noperm = this.getConfig().getString("NoPermission");
        String bowname = this.getConfig().getString("TPBowDisplayname");

        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemMeta meta = bow.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', bowname));
        meta.setUnbreakable(true);
        ArrayList<String> lore = new ArrayList<>();
        lore.add(ChatColor.AQUA + "Teleport where you shoot!");
        meta.setLore(lore);
        bow.setItemMeta(meta);
        player.getInventory().addItem(bow);
    }

    public void openMainMenu(Player player){
        Inventory main_menu = Bukkit.createInventory(player, 9, ChatColor.BLUE + "Armor Stand GUI");

        // Options for main menu
        ItemStack create = new ItemStack(Material.ARMOR_STAND);
        ItemMeta createmeta = create.getItemMeta();
        createmeta.setDisplayName(ChatColor.GREEN + "Create");
        ArrayList<String> createlore = new ArrayList<>();
        createlore.add(ChatColor.GRAY + "Create a new armor stand");
        createmeta.setLore(createlore);
        create.setItemMeta(createmeta);

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED + "Close");
        close.setItemMeta(closemeta);

        main_menu.setItem(0, create);
        main_menu.setItem(8, close);
        player.openInventory(main_menu);
    }

    public void openCreateMenu(Player player){
        Inventory create_menu = Bukkit.createInventory(player, 9, ChatColor.GREEN + "Create an Armor Stand");

        ItemStack arms = new ItemStack(Material.ARMOR_STAND);
        ItemStack glow = new ItemStack(Material.BEACON);
        ItemStack armor = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack base = new ItemStack(Material.STONE_SLAB);
        ItemStack complete = new ItemStack(Material.LIME_WOOL);
        ItemStack cancel = new ItemStack(Material.RED_WOOL);

        ItemMeta armsmeta = arms.getItemMeta();
        armsmeta.setDisplayName(ChatColor.GOLD + "Arms");

        ItemMeta glowmeta = glow.getItemMeta();
        glowmeta.setDisplayName(ChatColor.AQUA + "Glow");

        ItemMeta armormeta = armor.getItemMeta();
        armormeta.setDisplayName(ChatColor.YELLOW + "Armor");

        ItemMeta basemeta = base.getItemMeta();
        basemeta.setDisplayName(ChatColor.BLUE + "Base");

        ItemMeta completemeta = complete.getItemMeta();
        completemeta.setDisplayName(ChatColor.GREEN + "Complete & Create");

        ItemMeta cancelmeta = cancel.getItemMeta();
        cancelmeta.setDisplayName(ChatColor.RED + "Cancel");

        arms.setItemMeta(armsmeta);
        glow.setItemMeta(glowmeta);
        armor.setItemMeta(armormeta);
        base.setItemMeta(basemeta);
        complete.setItemMeta(completemeta);
        cancel.setItemMeta(cancelmeta);

        create_menu.setItem(0, arms);
        create_menu.setItem(1, glow);
        create_menu.setItem(2, armor);
        create_menu.setItem(3, base);
        create_menu.setItem(7, complete);
        create_menu.setItem(8, cancel);

        player.openInventory(create_menu);
    }

    public void openConfirmMenu(Player player, Material option) {
        Inventory confirm_menu = Bukkit.createInventory(player, 36, ChatColor.GREEN + "Confirm Option");

        ItemStack option_item = new ItemStack(option);
        ItemMeta option_meta = option_item.getItemMeta();

        if (option == Material.ARMOR_STAND) {
            option_meta.setDisplayName(ChatColor.YELLOW + "Give Arms?");
            option_item.setItemMeta(option_meta);
        } else if (option == Material.BEACON) {
            option_meta.setDisplayName(ChatColor.YELLOW + "Add Glow?");
            option_item.setItemMeta(option_meta);
        } else if (option == Material.STONE_SLAB) {
            option_meta.setDisplayName(ChatColor.YELLOW + "Add Base?");
            option_item.setItemMeta(option_meta);
        }

        ItemStack yes = new ItemStack(Material.LIME_WOOL);
        ItemMeta yesmeta = yes.getItemMeta();
        yesmeta.setDisplayName(ChatColor.GREEN + "YES");
        yes.setItemMeta(yesmeta);

        ItemStack no = new ItemStack(Material.RED_WOOL);
        ItemMeta nometa = no.getItemMeta();
        nometa.setDisplayName(ChatColor.RED + "NO");
        no.setItemMeta(nometa);

        confirm_menu.setItem(13, option_item);
        confirm_menu.setItem(21, yes);
        confirm_menu.setItem(23, no);

        player.openInventory(confirm_menu);
    }

    public void openArmorMenu(Player player) {
        Inventory armormenu = Bukkit.createInventory(player, 45, ChatColor.GOLD + "Choose some armor");

        ItemStack head = new ItemStack(Material.DIAMOND_HELMET);
        ItemStack body = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemStack legs = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);


        ItemStack yes = new ItemStack(Material.LIME_WOOL);
        ItemMeta yesmeta = yes.getItemMeta();
        yesmeta.setDisplayName(ChatColor.GREEN + "YES");
        yes.setItemMeta(yesmeta);

        ItemStack no = new ItemStack(Material.RED_WOOL);
        ItemMeta nometa = no.getItemMeta();
        nometa.setDisplayName(ChatColor.RED + "NO");
        no.setItemMeta(nometa);

        armormenu.setItem(11, head);
        armormenu.setItem(12, body);
        armormenu.setItem(14, legs);
        armormenu.setItem(15, boots);
        armormenu.setItem(40, yes);
        player.openInventory(armormenu);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (command.getName().equals("setFood")) {
            getConfig().set("Food", "Shrimp");
        }

        if (command.getName().equals("ddreload")) {
            Player player = (Player) sender;
            reloadConfig();
            XrayDetector.rareores.clear();
            for (String i : getConfig().getStringList("Ores"))XrayDetector.rareores.add(i);
            CustomConfig.reload();
            player.sendMessage(ChatColor.GREEN + "The config has been reloaded!");
        }
        return true;
    }


}
