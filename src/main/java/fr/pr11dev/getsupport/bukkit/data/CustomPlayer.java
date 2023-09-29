package fr.pr11dev.getsupport.bukkit.data;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CustomPlayer {
    private Player player;
    private ArrayList<Ticket> t;

    public CustomPlayer(Player player) {
        this.player = player;
        this.t = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public Ticket getFirstTicket() {
        if(t != null && !t.isEmpty()) {
            return t.get(0);
        }
        return null;
    }

    public ArrayList<Ticket> getTickets() {
        return t;
    }

    public void addTicket(Ticket t) {
        this.t.add(t);
    }

    public void removeTicket(Ticket t) {
        this.t.remove(t);
    }
}
