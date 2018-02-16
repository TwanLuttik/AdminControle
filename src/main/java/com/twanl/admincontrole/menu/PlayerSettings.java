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

public class PlayerSettings implements Listener {

    private Plugin plugin = AdminControle.getPlugin(AdminControle.class);


    public void configInventory(Player p) {
        Inventory SI = plugin.getServer().createInventory(null, 9, Strings.redB + "» " + Strings.DgrayB + "Player's Settings");


        ItemStack kickall = new ItemStack(Material.STAINED_GLASS_PANE,1, (byte) 14);
        ItemMeta kickallMeta = kickall.getItemMeta();
        kickallMeta.setDisplayName(Strings.green + "Kick all players");
        kickall.setItemMeta(kickallMeta);

        ItemStack back = new ItemStack(Material.BARRIER);
        ItemMeta backMeta = back.getItemMeta();
        backMeta.setDisplayName(Strings.green + "Back");
        back.setItemMeta(backMeta);



        SI.setItem(0, kickall);
        SI.setItem(8, back);

        p.openInventory(SI);


    }


}
