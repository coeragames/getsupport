package fr.pr11dev.getsupport.bungeecord.utils;

import fr.pr11dev.getsupport.bukkit.getsupport;
import fr.pr11dev.getsupport.bungeecord.getsupportBungee;
import net.md_5.bungee.api.ProxyServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class BungeeUpdate {
    private getsupportBungee plugin;
    private int resourceId;

    public BungeeUpdate(getsupportBungee plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLastestVersion(Consumer<String> consumer) {
        ProxyServer.getInstance().getScheduler().runAsync(plugin, () -> {
            try(InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream();
                Scanner scanner = new Scanner(inputStream)) {
                if(scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }

            } catch (IOException e) {
                plugin.getLogger().info("Could not check for updates "+e.getMessage());
            }
        });
    }
}
