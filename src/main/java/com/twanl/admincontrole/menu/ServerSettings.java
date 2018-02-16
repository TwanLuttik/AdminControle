package com.twanl.admincontrole.menu;

import com.twanl.admincontrole.AdminControle;
import com.twanl.admincontrole.util.Strings;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.Arrays;

public class ServerSettings implements Listener {

    private Plugin plugin = AdminControle.getPlugin(AdminControle.class);


    public void configInventory(Player p) {
        Inventory SI = plugin.getServer().createInventory(null, 9, Strings.redB + "» " + Strings.DgrayB + "Server Settings");



        ItemStack serverStop = new ItemStack(Material.PAPER);
        ItemMeta serverStopMeta = serverStop.getItemMeta();
        serverStopMeta.setDisplayName(Strings.green + "Stop Server");
        serverStopMeta.setLore(Arrays.asList(Strings.white + "Click to stop"));
        serverStop.setItemMeta(serverStopMeta);


        ItemStack ServerReload = new ItemStack(Material.PAPER);
        ItemMeta ServerReloadMeta = ServerReload.getItemMeta();
        ServerReloadMeta.setDisplayName(Strings.green + "Reload Server");
        ServerReloadMeta.setLore(Arrays.asList(Strings.white + "Click to reload"));
        ServerReload.setItemMeta(ServerReloadMeta);

        ItemStack whitelist = new ItemStack(Material.PAPER);
        ItemMeta whitelistMeta = whitelist.getItemMeta();
        whitelistMeta.setDisplayName(Strings.green + "Whitelist");
        if (!Bukkit.hasWhitelist()) {
            whitelistMeta.setLore(Arrays.asList(Strings.white + "Click to turn on/off the whitelist", Strings.red + "Whitelist Off"));
        } else {
            whitelistMeta.setLore(Arrays.asList(Strings.white + "Click to turn on/off the whitelist", Strings.green + "Whitelist On"));
        }
        whitelist.setItemMeta(whitelistMeta);

        ItemStack serverinfo = new ItemStack(Material.PAPER);
        ItemMeta serverinfoMeta = serverinfo.getItemMeta();
        serverinfoMeta.setDisplayName(Strings.green + "Server Info");
        serverinfoMeta.setLore(Arrays.asList("",
                Strings.gold + "Server IP: " + Strings.white + plugin.getServer().getIp() + ":" + plugin.getServer().getPort(),
                Strings.gold + "Whitelist: " + Strings.white + plugin.getServer().hasWhitelist(),
                Strings.gold + "Player Online: " + Strings.white + plugin.getServer().getOnlinePlayers().size()));
        serverinfo.setItemMeta(serverinfoMeta);


        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(Strings.green + "Back");
        back.setItemMeta(backMeta);



        SI.setItem(0, serverStop);
        SI.setItem(1, ServerReload);
        SI.setItem(2, whitelist);
        SI.setItem(3, serverinfo);
        SI.setItem(8, back);

        p.openInventory(SI);
    }




}
