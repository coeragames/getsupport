package fr.pr11dev.getsupport.bukkit.manager;

import fr.pr11dev.getsupport.bukkit.commands.TicketCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;

public class Commands {
    public static void register() {
        Plugin p = Bukkit.getPluginManager().getPlugin("getsupport");

        PluginCommand pTicket = p.getServer().getPluginCommand("ticket");
        pTicket.setExecutor(new TicketCommand());
    }
}
