package fr.pr11dev.getsupport.bukkit.events;

import fr.pr11dev.getsupport.bukkit.data.Data;
import fr.pr11dev.getsupport.bukkit.data.OfflineTicket;
import fr.pr11dev.getsupport.bukkit.data.Ticket;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.logging.Level;
public class PlayerJoin implements Listener {
    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent e) {
        for(OfflineTicket ot : Data.offlineTickets) {
            if(ot.getUuid().equals(e.getPlayer().getUniqueId())) {
                Ticket t = ot.getTicket();
                ot.remove();
                t.getPlayer().sendMessage("§a[§bGetsupport§a] §Un ancien ticket vous appartenant a été récupéré.");
                Bukkit.getLogger().log(Level.INFO, "Le ticket de "+t.getPlayer().getDisplayName()+" a été récupéré");
            }
        }
    }
}
