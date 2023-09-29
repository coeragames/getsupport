package fr.pr11dev.getsupport.bungeecord.customevents;

import fr.pr11dev.getsupport.bungeecord.data.BungeeTicket;
import net.md_5.bungee.api.plugin.Event;

public class BungeeTicketCreatedEvent extends Event {
    private BungeeTicket ticket;

    public BungeeTicketCreatedEvent(BungeeTicket ticket) {
        this.ticket = ticket;
    }

    public BungeeTicket getTicket() {
        return ticket;
    }
}
