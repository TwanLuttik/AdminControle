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

import java.util.Arrays;

import static com.twanl.admincontrole.util.PATHS.KICK_OPS;
import static com.twanl.admincontrole.util.PATHS.UPDATE_MESSAGE;

public class ConfigSettings implements Listener {

    private Plugin plugin = AdminControle.getPlugin(AdminControle.class);


    public void configInventory(Player p) {
        Inventory CI = plugin.getServer().createInventory(null, 9, Strings.redB + "» " + Strings.DgrayB + "Config Settings");


        ItemStack update = new ItemStack(Material.PAPER);
        ItemMeta updateMeta = update.getItemMeta();
        updateMeta.setDisplayName(Strings.green + "Update message");
        if (!plugin.getConfig().getBoolean(UPDATE_MESSAGE)) {
            updateMeta.setLore(Arrays.asList(Strings.red + "Off"));
        } else {
            updateMeta.setLore(Arrays.asList(Strings.green + "On"));
        }
        update.setItemMeta(updateMeta);

        ItemStack kickall = new ItemStack(Material.PAPER);
        ItemMeta kickallMeta = kickall.getItemMeta();
        kickallMeta.setDisplayName(Strings.green + "Kick players with OP");
        if (!plugin.getConfig().getBoolean(KICK_OPS)) {
            kickallMeta.setLore(Arrays.asList(Strings.red +"No"));
        } else {
            kickallMeta.setLore(Arrays.asList(Strings.green +"Yes"));
        }
        kickall.setItemMeta(kickallMeta);


        ItemStack test = new ItemStack(Material.PAPER);
        ItemMeta testMeta = test.getItemMeta();
        testMeta.setDisplayName(Strings.green + "test");
        test.setItemMeta(testMeta);


        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(Strings.green + "Back");
        back.setItemMeta(backMeta);




        CI.setItem(0, update);
        CI.setItem(1, kickall);
        //CI.setItem(2, test);
        CI.setItem(8, back);

        p.openInventory(CI);
    }
}