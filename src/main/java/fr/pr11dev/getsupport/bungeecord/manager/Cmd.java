package fr.pr11dev.getsupport.bungeecord.manager;

import fr.pr11dev.getsupport.bungeecord.commands.TicketCommandBungee;
import fr.pr11dev.getsupport.bungeecord.getsupportBungee;
import net.md_5.bungee.api.ProxyServer;

public class Cmd {

    public static void register() {
        ProxyServer.getInstance().getPluginManager().registerCommand(getsupportBungee.getInstance(), new TicketCommandBungee());
    }
}
