package fr.pr11dev.getsupport.bungeecord.data;

import fr.pr11dev.getsupport.bungeecord.utils.CustomProxiedPlayerManager;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BungeeTicket {
    public BungeeTicket(ProxiedPlayer demander, String request) {
        this.player = demander;
        this.message = request;
        this.claimed = false;
        this.caseOpeningTime = LocalDateTime.now();
        this.ticket_id = "ticket_"+player.getUniqueId().toString()+"_"+getFormattedCaseTime();
        Data.tickets.add(this);
        CustomProxiedPlayerManager.getCustomPlayerFromPlayer(demander).addTicket(this);
    }

    public void claim(ProxiedPlayer operator) {
        this.operator = operator;
        this.claimed = true;
        this.claimedTime = LocalDateTime.now();
    }

    public ProxiedPlayer getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCaseTime () {
        return caseOpeningTime;
    }

    public String getFormattedCaseTime() {
        return caseOpeningTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public LocalDateTime getClaimedTime() {
        return claimedTime;
    }

    public String getFormattedClaimedTime() {
        return claimedTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public ProxiedPlayer getOperator() {
        return operator;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public String getTicketId() {
        return ticket_id;
    }


    public void close() {
        CustomProxiedPlayerManager.getCustomPlayerFromPlayer(player).removeTicket(this);
        this.claimedTime = null;
        this.message = null;
        this.caseOpeningTime = null;
        this.operator = null;
        this.player = null;
        this.claimed = false;
        Data.tickets.remove(this);
    }


    private ProxiedPlayer player;
    private String message;
    private LocalDateTime caseOpeningTime;
    private LocalDateTime claimedTime;
    private ProxiedPlayer operator;
    private boolean claimed;
    private String ticket_id;
}
