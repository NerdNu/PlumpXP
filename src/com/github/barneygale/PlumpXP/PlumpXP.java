package com.github.barneygale.PlumpXP;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PlumpXP extends JavaPlugin {

    private final PlumpXPListener xplistener = new PlumpXPListener(this);
    public final Configuration config = new Configuration(this);


    public void onEnable() {
        //Load config
        File config_file = new File(getDataFolder(), "config.yml");
        if (!config_file.exists()) {
            getConfig().options().copyDefaults(true);
            saveConfig();
        }
        config.load();

        //Register listener
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(xplistener, this);
    }


    public boolean onCommand(CommandSender sender, Command command, String name, String[] args) {
        if (command.getName().equalsIgnoreCase("reloadplumpxp")) {
            config.load();
            return true;
        }
        return false;
    }


}