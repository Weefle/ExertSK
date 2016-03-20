package net.andrew;

import java.io.IOException;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ch.njol.skript.Skript;
import net.andrew.metrics.Metrics;
import net.andrew.utils.random.Lag;

public class Main extends JavaPlugin{
	private FileConfiguration config;
	private static Main instance;
	public static Main inst() {
		  return instance;
		}
	@Override
	public void onEnable(){
		instance = this;
		this.config = this.getConfig();
		saveDefaultConfig();
		Config.start(this.config);
		Logger log = this.getLogger();
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Lag(), 100L, 1L);
	    try {
	        Metrics metrics = new Metrics(this);
	        metrics.start();
	        log.info("Hooked into metrics!");
	    } catch (IOException e) {
	        log.severe("Could not enable metrics. Not trying again" + ChatColor.RESET);
	    }
	    
		if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
		    //SKRIPT REGISTERING
			Skript.registerAddon(this);
			Registry.SkriptBook();
			Registry.Random();
			Plugin mcMMO = Bukkit.getServer().getPluginManager().getPlugin("mcMMO");
			Plugin NameTags = Bukkit.getServer().getPluginManager().getPlugin("Nametags");
			Plugin Consoles = Bukkit.getServer().getPluginManager().getPlugin("ConsolesCore");
			if (mcMMO != null) {
				getLogger().info("Hooked into mcMMO. Hi mcMMO!");
				Registry.mcMMO(mcMMO);
			}
			if (NameTags != null){
				getLogger().info("Hooked into Nametags!");
				Registry.nameTags(NameTags);
			}
			if (Consoles != null){
				getLogger().info("Hooked into ConsolesCore! Do you have map support?");
				Registry.consoles(Consoles);
			}
		}
		else{
			getLogger().warning("How is this plugin still running?");
		}

		
	}
}
