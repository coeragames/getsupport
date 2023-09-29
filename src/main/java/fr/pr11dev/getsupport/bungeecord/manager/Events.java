package fr.pr11dev.getsupport.bungeecord.manager;

import fr.pr11dev.getsupport.bungeecord.events.PlayerJoin;
import fr.pr11dev.getsupport.bungeecord.getsupportBungee;
import net.md_5.bungee.api.ProxyServer;

public class Events {
    public static void register() {
        ProxyServer.getInstance().getPluginManager().registerListener(getsupportBungee.getInstance(), new PlayerJoin());
    }
}
