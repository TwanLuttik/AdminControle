package com.twanl.admincontrole.events;

import com.twanl.admincontrole.AdminControle;
import com.twanl.admincontrole.util.ConfigManager;
import com.twanl.admincontrole.util.Permissions;
import com.twanl.admincontrole.util.Strings;
import com.twanl.admincontrole.util.UpdateChecker;
import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutChat;
import net.minecraft.server.v1_12_R1.PlayerConnection;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvents implements Listener {

    private AdminControle plugin = AdminControle.getPlugin(AdminControle.class);
    public ConfigManager cfgM;





    @EventHandler
    public void onJoin(PlayerJoinEvent event) {


        /*
        cfgM = new ConfigManager();

        cfgM.setup();
        Player p1 = event.getPlayer();
        UUID uuid = p1.getUniqueId();
        int playerkicks = cfgM.getPlayers().getInt(uuid + ".kicks");
        int bankicks = plugin.getConfig().getInt("KicksTillBan");

        if (playerkicks < bankicks) {

        } else {
            p1.kickPlayer(Strings.redB + "You are banned");
            p1.sendMessage("test");
        }
        */







        // let the player know if there is an update for the plugin.
        if (!plugin.getConfig().getBoolean("update_message")) {

        } else {
            final Player p = event.getPlayer();


            final PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;

            final PacketPlayOutChat packet = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"AdminControle is outdated!\",\"color\":\"red\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://www.spigotmc.org/resources/admincontrole.37556/download?version=175588\"},\"hoverEvent\":{\"action\":\"show_text\",\"value\":{\"text\":\"\",\"extra\":[{\"text\":\"Click to download the newest version of AdminControle\",\"color\":\"gold\"}]}}}"));


            plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                public UpdateChecker checker;
                public void run() {
                    this.checker = new UpdateChecker(plugin);

                    if (p.hasPermission(Permissions.ADMINCONTROLE_UPDATE)) {
                        if (this.checker.isConnected()) {
                            if (this.checker.hasUpdate()) {
                                p.sendMessage(Strings.DgrayBS + "------------------------\n");
                                connection.sendPacket(packet);
                                p.sendMessage(Strings.white + "Your version: " + Strings.green + plugin.getDescription().getVersion() + "\n" +
                                        Strings.white + "Newest version: " + this.checker.getLatestVersion() + "\n" +
                                        Strings.DgrayBS + "------------------------");
                            } else {
                                p.sendMessage(Strings.DgrayBS + "------------------------\n" +
                                        Strings.green + "AdminControle is up to date.\n" +
                                        Strings.DgrayBS + "------------------------");
                            }
                        }
                    }
                }
            }, 20);

        }
    }



}
