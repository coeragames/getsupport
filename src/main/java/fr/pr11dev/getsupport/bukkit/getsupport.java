package fr.pr11dev.getsupport.bukkit;

import fr.pr11dev.getsupport.bukkit.data.Data;
import fr.pr11dev.getsupport.bukkit.manager.Commands;
import fr.pr11dev.getsupport.bukkit.utils.DataManagement;
import fr.pr11dev.getsupport.bukkit.utils.Update;
import fr.pr11dev.getsupport.bukkit.manager.Events;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;

public class getsupport extends JavaPlugin {

    private final File config = new File("./plugins/getsupport/config.yml");
    static getsupport instance;

    public void onEnable() {
        Bukkit.getServer().getConsoleSender().sendMessage("§c[§6GetSupport§c] §7Le plugin s'allume");

        Data.tickets = new ArrayList<>();
        instance = this;
        Commands.register();
        Events.regsiter();

        new Update(this, 91749).getLastestVersion(version -> {
            if(this.getDescription().getVersion().equals(version)) {
                Bukkit.getLogger().log(Level.INFO,"§c[§6GetSupport§c] §7Vous utilisez la dernière version du plugin");
            } else {
                Bukkit.getLogger().log(Level.INFO,"§c[§6GetSupport§c] §7Vous utilisez la version §6"+this.getDescription().getVersion()+"§7, la dernière version est §6" +version);
            }
        });

        if(!config.exists()) {
            saveDefaultConfig();
        }

        DataManagement.load();

        new Metrics(this, 15022);
    }

    public void onDisable() {
        DataManagement.save();
        Bukkit.getServer().getConsoleSender().sendMessage("§c[§6GetSupport§c] §7Le plugin s'arrete");
    }

    public static getsupport getInstance() {
        return instance;
    }
}
