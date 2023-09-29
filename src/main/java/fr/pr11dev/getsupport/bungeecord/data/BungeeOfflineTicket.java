package fr.pr11dev.getsupport.bungeecord.data;

import net.md_5.bungee.api.ProxyServer;

import java.util.UUID;

public class BungeeOfflineTicket {
    private UUID uuid;
    private String message;
    private boolean claimed;
    private UUID uuid_operator;
    private String ticketId;

    public BungeeOfflineTicket(UUID uuid, String message) {
        this.uuid = uuid;
        this.message = message;
        this.ticketId = uuid + "_" + message + "_" + UUID.randomUUID();
        Data.offlineTickets.add(this);
    }

    public void claim(UUID uuid_operator) {
        claimed = true;
        this.uuid_operator = uuid_operator;
    }

    public BungeeTicket getTicket() {
        BungeeTicket t  = new BungeeTicket(ProxyServer.getInstance().getPlayer(uuid), message);
        if(claimed) {
            t.claim(ProxyServer.getInstance().getPlayer(uuid_operator));
        }

        return t;
    }

    public void remove() {
        Data.offlineTickets.remove(this);
        this.uuid = null;
        this.message = null;
        this.claimed = false;
        this.uuid_operator = null;
    }

    public String getMessage() {
        return message;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public UUID getUuid_operator() {
        return uuid_operator;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getTicketId() {
        return ticketId;
    }

}
