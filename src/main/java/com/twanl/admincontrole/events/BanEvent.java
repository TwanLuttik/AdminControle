package com.twanl.admincontrole.events;

import com.twanl.admincontrole.AdminControle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.UUID;

public class BanEvent implements Listener {

    public AdminControle plugin = AdminControle.getPlugin(AdminControle.class);

    /*
    @EventHandler
    public void BlockPlace(BlockPlaceEvent e) {

        Block block = e.getBlock();
        Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();


        if (block.getType().equals(Material.TNT)) {

            e.setCancelled(true);
            int playerkicks = plugin.getConfig().getInt(uuid + ".kicks");
            int bankicks = plugin.getConfig().getInt("KicksTillBan");


            if (playerkicks < bankicks) {
                p.kickPlayer(Strings.red + "Placing TNT is not allowed on this server");
                plugin.getConfig().set(uuid + ".kicks", playerkicks + 1);
                plugin.saveConfig();
            } else {
                plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "ban " + p.getName() + " &c&lYou have reach the max of amount of TNT places!");
            }
        } else {
            return;
        }
    }
    */





    @EventHandler
    public void ban() {

        Player p = null;
        UUID uuid = p.getUniqueId();

        int playerkicks = plugin.getConfig().getInt(uuid + ".kicks");
        int bankicks = plugin.getConfig().getInt("KicksTillBan");

        plugin.getConfig().getString("0f4995db-a122-4deb-84c3-d52a9555c816.kicks");






    }

    public void unban() {

    }

    public void checkbanstatus() {

    }
/*
    public void ban() {
        Player p = new p.getName();
        p.sendMessage("test");
    }
    */
}
