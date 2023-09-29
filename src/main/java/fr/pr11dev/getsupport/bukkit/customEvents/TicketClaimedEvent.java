package fr.pr11dev.getsupport.bukkit.customEvents;

import fr.pr11dev.getsupport.bukkit.data.Ticket;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class TicketClaimedEvent extends Event {
    private Ticket t;
    private static final HandlerList handlers = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    public TicketClaimedEvent(Ticket ticket) {
        this.t = ticket;
    }

    public Ticket getTicket() {
        return this.t;
    }
}
