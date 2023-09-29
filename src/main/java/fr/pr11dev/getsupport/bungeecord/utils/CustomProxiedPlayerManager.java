package fr.pr11dev.getsupport.bungeecord.utils;

import fr.pr11dev.getsupport.bungeecord.data.CustomProxiedPlayer;
import fr.pr11dev.getsupport.bungeecord.data.Data;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;


public class CustomProxiedPlayerManager {
    public static CustomProxiedPlayer getCustomPlayer(String name) {
        if(Data.players != null && !Data.players.isEmpty()) {
            for (CustomProxiedPlayer player : Data.players) {
                if (player.getPlayer().getName().equalsIgnoreCase(name)) {
                    return player;
                }
            }

            if(ProxyServer.getInstance().getPlayer(name) != null) {
                final CustomProxiedPlayer p = new CustomProxiedPlayer(ProxyServer.getInstance().getPlayer(name));
                Data.players.add(p);
                return p;
            }
            else {
                return null;
            }
        }
        else {
            if(ProxyServer.getInstance().getPlayer(name) != null) {
                final CustomProxiedPlayer p = new CustomProxiedPlayer(ProxyServer.getInstance().getPlayer(name));
                Data.players.add(p);
                return p;
            }
            else {
                return null;
            }
        }

    }

    public static CustomProxiedPlayer getCustomPlayerFromSender(CommandSender sender){
        return getCustomPlayer(sender.getName());
    }

    public static CustomProxiedPlayer getCustomPlayerFromPlayer(ProxiedPlayer player) {
        return getCustomPlayer(player.getName());
    }
}
