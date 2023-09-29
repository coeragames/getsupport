package fr.pr11dev.getsupport.bungeecord;

import fr.pr11dev.getsupport.bungeecord.manager.Cmd;
import fr.pr11dev.getsupport.bungeecord.utils.BungeeUpdate;
import fr.pr11dev.getsupport.bungeecord.manager.Events;
import fr.pr11dev.getsupport.bungeecord.utils.DataManagmentBungee;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.bstats.bungeecord.Metrics;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public class getsupportBungee extends Plugin {


    public Configuration config;
    static getsupportBungee instance;

    @Override
    public void onEnable() {
        getProxy().getConsole().sendMessage("§a[§bGetsupport§a] §bPlugin §aBungeeCord §bactivé !");
        instance = this;
        Cmd.register();
        Events.register();

        new BungeeUpdate(this, 91749).getLastestVersion(version -> {
            if(this.getDescription().getVersion().equals(version)) {
                ProxyServer.getInstance().getLogger().log(Level.INFO,"§c[§6GetSupport§c] §7Vous utilisez la dernière version du plugin");
            } else {
                ProxyServer.getInstance().getLogger().log(Level.INFO,"§c[§6GetSupport§c] §7Vous utilisez la version §6"+this.getDescription().getVersion()+"§7, la dernière version est §6" +version);
            }
        });

        if (!getDataFolder().exists())
            getDataFolder().mkdir();
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists()) {
            try (InputStream in = getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        DataManagmentBungee.load();

        new Metrics(this, 15023);
    }

    @Override
    public void onDisable() {
        DataManagmentBungee.save();
        getProxy().getConsole().sendMessage("§a[§bGetsupport§a] §bPlugin §aBungeeCord §bdesactivé !");
    }

    public static getsupportBungee getInstance() {
        return instance;
    }
}
