package fr.pr11dev.getsupport.bukkit.utils;

import fr.pr11dev.getsupport.bukkit.getsupport;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class Update {
    private getsupport plugin;
    private int resourceId;

    public Update(getsupport plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getLastestVersion(Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
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
