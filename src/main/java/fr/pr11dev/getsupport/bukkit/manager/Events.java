package fr.pr11dev.getsupport.bukkit.manager;

import fr.pr11dev.getsupport.bukkit.events.PlayerJoin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class Events {
    public static void regsiter() {
        PluginManager pm = Bukkit.getPluginManager();
        Plugin p = pm.getPlugin("getsupport");

        pm.registerEvents(new PlayerJoin(), p);
    }
}
