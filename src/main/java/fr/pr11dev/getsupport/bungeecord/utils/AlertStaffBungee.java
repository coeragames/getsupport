package fr.pr11dev.getsupport.bungeecord.utils;

import fr.pr11dev.getsupport.bungeecord.data.BungeeTicket;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class AlertStaffBungee {
    public static void alert(BungeeTicket t) {
        for(ProxiedPlayer p : ProxyServer.getInstance().getPlayers()) {
            if(p.hasPermission("gs.alert")) {
                p.sendMessage("§c[GetSupport] §7" + t.getPlayer().getName() + " §7a ouvert un ticket");
            }
        }
    }
}
