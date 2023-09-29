package fr.pr11dev.getsupport.bukkit.utils;

import fr.pr11dev.getsupport.bukkit.data.CustomPlayer;
import fr.pr11dev.getsupport.bukkit.data.Data;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static org.bukkit.Bukkit.getServer;

public class CustomPlayerManager {

    public static CustomPlayer getCustomPlayer(String name) {
        if(Data.players != null && !Data.players.isEmpty()) {
            for (CustomPlayer player : Data.players) {
                if (player.getPlayer().getName().equalsIgnoreCase(name)) {
                    return player;
                }
            }

            if(getServer().getPlayerExact(name) != null) {
                final CustomPlayer p = new CustomPlayer(getServer().getPlayer(name));
                Data.players.add(p);
                return p;
            }
            else {
                return null;
            }
        }
        else {
            if(getServer().getPlayerExact(name) != null) {
                final CustomPlayer p = new CustomPlayer(getServer().getPlayer(name));
                Data.players.add(p);
                return p;
            }
            else {
                return null;
            }
        }

    }

    public static CustomPlayer getCustomPlayerFromSender(CommandSender sender){
        return getCustomPlayer(sender.getName());
    }

    public static CustomPlayer getCustomPlayerFromPlayer(Player player) {
        return getCustomPlayer(player.getName());
    }
}
