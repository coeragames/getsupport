package fr.pr11dev.getsupport.bungeecord.data;

import fr.pr11dev.getsupport.bukkit.data.Ticket;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CustomProxiedPlayer {
    private ProxiedPlayer player;
    private ArrayList<BungeeTicket> t;

    public CustomProxiedPlayer(ProxiedPlayer player) {
        this.player = player;
        this.t = new ArrayList<>();
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public BungeeTicket getFirstTicket() {
        if(t != null && !t.isEmpty()) {
            return t.get(0);
        }
        return null;
    }

    public ArrayList<BungeeTicket> getTickets() {
        return t;
    }

    public void addTicket(BungeeTicket t) {
        this.t.add(t);
    }

    public void removeTicket(BungeeTicket t) {
        this.t.remove(t);
    }
}
