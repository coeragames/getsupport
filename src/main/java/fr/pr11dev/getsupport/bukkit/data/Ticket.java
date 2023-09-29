package fr.pr11dev.getsupport.bukkit.data;

import fr.pr11dev.getsupport.bukkit.utils.CustomPlayerManager;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {
    public Ticket(Player asker, String request) {
        this.player = asker;
        this.message = request;
        this.claimed = false;
        this.caseOpeningTime = LocalDateTime.now();
        this.ticket_id = "ticket_"+player.getUniqueId().toString()+"_"+getFormattedCaseTime();
        Data.tickets.add(this);
        CustomPlayerManager.getCustomPlayerFromPlayer(asker).addTicket(this);
    }

    public void claim(Player operator) {
        this.operator = operator;
        this.claimed = true;
        this.claimedTime = LocalDateTime.now();
    }

    public Player getPlayer() {
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

    public Player getOperator() {
        return operator;
    }

    public boolean isClaimed() {
        return claimed;
    }

    public String getTicketId() {
        return ticket_id;
    }


    public void close() {
        CustomPlayerManager.getCustomPlayerFromSender(player).removeTicket(this);
        this.claimedTime = null;
        this.message = null;
        this.caseOpeningTime = null;
        this.operator = null;
        this.player = null;
        this.claimed = false;
        Data.tickets.remove(this);
    }


        private Player player;
        private String message;
        private LocalDateTime caseOpeningTime;
        private LocalDateTime claimedTime;
        private Player operator;
        private boolean claimed;
        private String ticket_id;
}
