package fr.pr11dev.getsupport.bukkit.utils;

import fr.pr11dev.getsupport.bukkit.data.Ticket;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class AlertStaff {
    public static void alert(Ticket t) {
        for(Player p : Bukkit.getServer().getOnlinePlayers()) {
            if(p.isOp() || p.hasPermission("gs.alert")) {
                p.sendMessage("§c[GetSupport] §7" + t.getPlayer().getName() + " §7a ouvert un ticket");
            }
        }
    }
}
