package com.twanl.admincontrole.menu;

import com.twanl.admincontrole.AdminControle;
import com.twanl.admincontrole.util.Strings;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class MainInventory implements Listener {


    private Plugin plugin = AdminControle.getPlugin(AdminControle.class);


    public void mainInventory(Player p) {
        Inventory MI = plugin.getServer().createInventory(null, 27, Strings.redB + "» " + Strings.DgrayB + "AdminControle");


        ItemStack settings = new ItemStack(Material.PAPER);
        ItemMeta settingsMeta = settings.getItemMeta();
        settingsMeta.setDisplayName(Strings.green + "Config Settings");
        settings.setItemMeta(settingsMeta);

        ItemStack credits = new ItemStack(Material.BOOK);
        ItemMeta creditsMeta = credits.getItemMeta();
        creditsMeta.setDisplayName(Strings.green + "Credits");
        credits.setItemMeta(creditsMeta);

        ItemStack serversettings = new ItemStack(Material.REDSTONE);
        ItemMeta serversettingsMeta = serversettings.getItemMeta();
        serversettingsMeta.setDisplayName(Strings.red + "Server Settings");
        serversettings.setItemMeta(serversettingsMeta);

        ItemStack playersettings = new ItemStack(Material.SKULL_ITEM);
        ItemMeta playersettingsMeta = playersettings.getItemMeta();
        playersettingsMeta.setDisplayName(Strings.red + "Player's Settings");
        playersettings.setItemMeta(playersettingsMeta);


        MI.setItem(10, serversettings);
        MI.setItem(11, playersettings);
        MI.setItem(26, credits);
        MI.setItem(18, settings);

        p.openInventory(MI);
    }

}