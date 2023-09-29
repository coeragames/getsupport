package fr.pr11dev.getsupport.bungeecord.events;

import fr.pr11dev.getsupport.bungeecord.data.BungeeOfflineTicket;
import fr.pr11dev.getsupport.bungeecord.data.BungeeTicket;
import fr.pr11dev.getsupport.bungeecord.data.Data;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.logging.Level;

public class PlayerJoin implements Listener {
    @EventHandler
    public void PlayerJoinEvent(ServerConnectEvent e) {
        for(BungeeOfflineTicket bot : Data.offlineTickets) {
            if(bot.getUuid().equals(e.getPlayer().getUniqueId())){
                BungeeTicket t = bot.getTicket();
                bot.remove();
                t.getPlayer().sendMessage("§a[§bGetsupport§a] §Un ancien ticket vous appartenant a été récupéré.");
                ProxyServer.getInstance().getLogger().log(Level.INFO, "Le ticket de "+t.getPlayer().getDisplayName()+" a été récupéré");
                return;
            }
        }

    }
}
