package com.twanl.admincontrole.events;

import com.twanl.admincontrole.AdminControle;
import com.twanl.admincontrole.menu.ConfigSettings;
import com.twanl.admincontrole.menu.MainInventory;
import com.twanl.admincontrole.menu.PlayerSettings;
import com.twanl.admincontrole.menu.ServerSettings;
import com.twanl.admincontrole.util.Strings;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.twanl.admincontrole.util.PATHS.*;
import static com.twanl.admincontrole.util.PATHS.KICK_MESSAGE;
import static com.twanl.admincontrole.util.Permissions.ADMINCONTROLE_KICK_BYPASS;

public class EventsClass implements Listener {


    private AdminControle plugin = AdminControle.getPlugin(AdminControle.class);

    private ConfigSettings cs = new ConfigSettings();
    private ServerSettings ss = new ServerSettings();
    private MainInventory mi = new MainInventory();
    private PlayerSettings ps = new PlayerSettings();
    private long sleepTime;


    @EventHandler
    public void InvenClick(InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        Inventory open = e.getInventory();

        ItemStack item = e.getCurrentItem();
        if (open == null) {
            return;
        }


        // Main Menu
        if (open.getName().equals(Strings.redB + "» " + Strings.DgrayB + "AdminControle")) {
            e.setCancelled(true);
            if (item == null || !item.hasItemMeta()) {
                return;
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Config Settings")) {
                p.closeInventory();
                cs.configInventory(p);
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.red + "Server Settings")) {
                p.closeInventory();
                ss.configInventory(p);
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.red + "Player's Settings")) {
                p.closeInventory();
                ps.configInventory(p);
            }


            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Credits")) {
                p.closeInventory();

                p.sendMessage(Strings.DgrayBIS + "-----------------------------\n" +
                        Strings.goldB + "          AdminControle\n" +
                        Strings.gold + "             Version: " + plugin.getDescription().getVersion() + "\n" +
                        Strings.DgrayBIS + "-----------------------------\n" +
                        Strings.goldB + "             Authors\n" +
                        Strings.gold + "        Twan Luttik " + Strings.gray + "(Developer)\n" +
                        Strings.gold + "        Ramon Peek " + Strings.gray + "(Developer)\n" +
                        Strings.DgrayBIS + "-----------------------------");

            }
        }


        // Config settings
        if (open.getName().equals(Strings.redB + "» " + Strings.DgrayB + "Config Settings")) {
            e.setCancelled(true);
            if (item == null || !item.hasItemMeta()) {
                return;
            }



            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Update message")) {
                if (!plugin.getConfig().getBoolean(UPDATE_MESSAGE)) {
                    plugin.getConfig().set(UPDATE_MESSAGE, true);
                } else {
                    plugin.getConfig().set(UPDATE_MESSAGE, false);
                }
                cs.configInventory(p);
                plugin.saveConfig();
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Kick players with OP")) {
                if (!plugin.getConfig().getBoolean(KICK_OPS)) {
                    plugin.getConfig().set(KICK_OPS, true);
                } else {
                    plugin.getConfig().set(KICK_OPS, false);
                }
                cs.configInventory(p);
                plugin.saveConfig();
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "test")) {
                p.closeInventory();

            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Back")) {
                p.closeInventory();
                mi.mainInventory(p);
            }
        }




        // Server Settigns
        if (open.getName().equals(Strings.redB + "» " + Strings.DgrayB + "Server Settings")) {


            e.setCancelled(true);
            if (item == null || !item.hasItemMeta()) {
                return;
            }


            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Stop Server")) {
                /*

                p.closeInventory();

                int ticks = (int) plugin.getConfig().get(SERVER_STOP_COUNTDOWN);
                int seconds = ticks / 20;
                String s = seconds + "";


                if (plugin.getConfig().getString(SERVER_RELOAD_COUNTDOWN).equalsIgnoreCase("0")) {
                    // don't send any message
                }


                if (!plugin.getConfig().getBoolean(SERVER_STOP_BROADCAST)) {
                    p.sendMessage(Strings.yellow + "Server Shutdown in " + Strings.whiteB + s + " seconds!");
                } else {
                    Bukkit.broadcastMessage(Strings.yellow + "Server Shutdown in " + Strings.whiteB + s + " seconds!");
                }

                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {

                        p.sendMessage(Strings.gold + "Server Shutdown!");
                        //Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "stop");
                        //Bukkit.shutdown();
                        Bukkit.getServer().shutdown();


                    }
                }, plugin.getConfig().getInt(SERVER_STOP_COUNTDOWN));

*/
            }


            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Reload Server")) {

                int ticks = (int) plugin.getConfig().get(SERVER_RELOAD_COUNTDOWN);
                int seconds = ticks / 20;
                String s = seconds + "";

                p.closeInventory();

                if (plugin.getConfig().getString(SERVER_RELOAD_COUNTDOWN).equalsIgnoreCase("0")) {
                    // don't send any message
                } else {
                    if (!plugin.getConfig().getBoolean(SERVER_RELOAD_BROADCAST)) {

                        p.sendMessage(Strings.yellow + "Server reloading in " + Strings.whiteB +
                                s + " seconds!");
                    } else {
                        Bukkit.broadcastMessage(Strings.yellow + "Server reloading in " + Strings.whiteB +
                                s + " seconds!");
                    }
                }

                plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                    public void run() {

                        Bukkit.reload();
                        p.sendMessage(Strings.gold + "Server Reloaded!");
                    }
                }, plugin.getConfig().getInt(SERVER_RELOAD_COUNTDOWN));

            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Whitelist")) {

                if (!Bukkit.hasWhitelist()) {
                    Bukkit.setWhitelist(true);
                    ss.configInventory(p);
                } else {
                    Bukkit.setWhitelist(false);
                    ss.configInventory(p);
                }


            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Server Info")) {
                //no event
            }


            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Back")) {
                p.closeInventory();
                mi.mainInventory(p);
            }


        }


        //Player's settings menu
        if (open.getName().equals(Strings.redB + "» " + Strings.DgrayB + "Player's Settings")) {
            e.setCancelled(true);
            if (item == null || !item.hasItemMeta()) {
                return;
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Kick all players")) {
                for (Player p1 : Bukkit.getOnlinePlayers()) {
                    if (!plugin.getConfig().getBoolean(KICK_OPS) && p1.hasPermission(ADMINCONTROLE_KICK_BYPASS)) {
                        if (p1.isOp()) {

                        } else {
                            p1.kickPlayer(String.valueOf(plugin.getConfig().get(KICK_MESSAGE)));
                        }
                    } else {
                        if (p1.isOp()) {
                            p1.kickPlayer(String.valueOf(plugin.getConfig().get(KICK_MESSAGE)));
                        } else {
                            p1.kickPlayer(String.valueOf(plugin.getConfig().get(KICK_MESSAGE)));
                        }

                    }

                }
            }

            if (item.getItemMeta().getDisplayName().equals(Strings.green + "Back")) {
                p.closeInventory();
                mi.mainInventory(p);
            }
        }
    }
}