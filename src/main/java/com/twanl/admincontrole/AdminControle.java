package com.twanl.admincontrole;

import com.twanl.admincontrole.events.EventsClass;
import com.twanl.admincontrole.events.JoinEvents;
import com.twanl.admincontrole.util.ConfigManager;
import com.twanl.admincontrole.util.Strings;
import com.twanl.admincontrole.util.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class AdminControle extends JavaPlugin {

    private UpdateChecker checker;
    protected PluginDescriptionFile pdfFile = getDescription();
    private final String PluginVersionOn = ChatColor.GREEN + "(" + pdfFile.getVersion() + ")";
    private final String PluginVersionOff = ChatColor.RED + "(" + pdfFile.getVersion() + ")";
    public ConfigManager cfgM;


    public void onEnable() {
        loadConfigManager();
        LOAD();

        // this check if there is a newer version of the plugin.
        this.checker = new UpdateChecker(this);
        if (this.checker.isConnected()) {
            if (this.checker.hasUpdate()) {
                getServer().getConsoleSender().sendMessage(Strings.blue + "------------------------");
                getServer().getConsoleSender().sendMessage(Strings.red + "AdminControle is outdated!");
                getServer().getConsoleSender().sendMessage(Strings.white + "Newest version: " + this.checker.getLatestVersion());
                getServer().getConsoleSender().sendMessage(Strings.white + "Your version: " + Strings.green + this.getDescription().getVersion());
                getServer().getConsoleSender().sendMessage("Please download the new version at https://www.spigotmc.org/resources/admincontrol-new-update.37556/");
                getServer().getConsoleSender().sendMessage(Strings.blue + "------------------------");
            } else {
                getServer().getConsoleSender().sendMessage(Strings.blue + "---------------------------------");
                getServer().getConsoleSender().sendMessage(Strings.green + "AdminControle is up to date.");
                getServer().getConsoleSender().sendMessage(Strings.blue + "---------------------------------");
            }
        }


        Bukkit.getConsoleSender().sendMessage(Strings.logName + "Has been enabled " + PluginVersionOn);
    }


    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(Strings.logName + ChatColor.RED + "Has been disabled " + PluginVersionOff);
    }

    public void LOAD() {
        // Register listeners
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getServer().getPluginManager().registerEvents(new JoinEvents(), this);
        //getServer().getPluginManager().registerEvents(new BanEvent(), this);

        // Register commands
        Commands commands = new Commands();
        getCommand("ad").setExecutor(commands);

        //LoadConfig
        getConfig().options().copyDefaults(true);
        saveConfig();
    }


    public void loadConfigManager() {
        cfgM = new ConfigManager();
        cfgM.setup();
        cfgM.savePlayers();
        cfgM.reloadplayers();
    }

}
