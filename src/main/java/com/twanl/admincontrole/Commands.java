package com.twanl.admincontrole;

import com.twanl.admincontrole.menu.MainInventory;
import com.twanl.admincontrole.util.Permissions;
import com.twanl.admincontrole.util.Strings;
import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class Commands extends CommandExecute implements Listener, CommandExecutor {

    private Plugin plugin = AdminControle.getPlugin(AdminControle.class);
    public File configFile;


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.logName + ChatColor.RED + "Only a player can execute commands!");
            return true;
        }






        if (cmd.getName().equalsIgnoreCase("ad")) {
            if (args.length == 0) {

                if (p.hasPermission(Permissions.ADMINCONTROLE_GUI)) {
                    MainInventory i = new MainInventory();
                    i.mainInventory(p);

                } else {
                    p.sendMessage(Strings.noPerm);
                }

            } else if (args[0].equalsIgnoreCase("reload")) {
                if (p.hasPermission(Permissions.ADMINCONTROLE_RELOAD)) {

                    plugin.saveDefaultConfig();
                    plugin.reloadConfig();
                    plugin.getServer().getConsoleSender().sendMessage(Strings.logName + Strings.green + "Config File Reloaded Succsesfully!");
                    p.sendMessage(Strings.goldI + "Config File Reloaded Succsesfully!");

                } else {
                    p.sendMessage(Strings.noPerm);
                }

            } else if (args[0].equalsIgnoreCase("help")) {
                if (p.hasPermission(Permissions.ADMINCONTROLE_HELP)) {
                    p.sendMessage(Strings.DgrayBIS + "-----------------------------\n" +
                            Strings.goldB + "           Commands\n" +
                            Strings.DgrayBIS + "-----------------------------\n" +
                            Strings.gold + "/ad " + Strings.white + "opens the gui\n" +
                            Strings.gold + "/ad cc " + Strings.white + "create a new config\n" +
                            Strings.gold + "/ad help " + Strings.white + "shows this page\n" +
                            Strings.gold + "/ad reload " + Strings.white + "Reload the config file\n" +
                            Strings.DgrayBIS + "-----------------------------");
                } else {
                    p.sendMessage(Strings.noPerm);
                }

            } else if (args[0].equalsIgnoreCase("cc")) {
                if (p.hasPermission(Permissions.ADMINCONTROLE_CREATECONFIG)) {

                    configFile = new File(plugin.getDataFolder(), "config.yml");
                    File file2 = new File(plugin.getDataFolder(), "configBackup.yml");


                    if (configFile.exists()) {
                        plugin.getServer().getConsoleSender().sendMessage(Strings.logName + Strings.green + "Succsesfully created a new config file!");
                        p.sendMessage(Strings.goldI + "Succsesfully created a new config file!");

                        configFile.renameTo(file2);
                        plugin.saveDefaultConfig();

                    } else {
                        plugin.getServer().getConsoleSender().sendMessage(Strings.logName + Strings.green + "File doesn't exist!");
                        p.sendMessage(Strings.goldI + "File doesn't exist!");
                    }

                } else {
                    p.sendMessage(Strings.noPerm);
                }
            }

        }

        return true;
    }
}