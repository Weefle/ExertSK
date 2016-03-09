package net.andrew;

import java.io.IOException;
import java.util.logging.Logger;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapPalette;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.andrew.effects.maps.EffClearMap;
import net.andrew.effects.maps.EffMapRenderFile;
import net.andrew.effects.maps.EffMapRenderText;
import net.andrew.effects.maps.EffMapRenderURL;
import net.andrew.effects.maps.EffMapSetBorderRect;
import net.andrew.effects.maps.EffMapSetLine;
import net.andrew.effects.maps.EffMapSetPixel;
import net.andrew.effects.maps.EffMapSetRect;
import net.andrew.expressions.maps.ExprMapID;
import net.andrew.metrics.Metrics;
import net.andrew.utils.maps.ColorMapUtil;

@SuppressWarnings("unused")
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
			Registry.SkriptMaps();
			Registry.SkriptBook();
			Registry.Random();
			Plugin mcMMO = Bukkit.getServer().getPluginManager().getPlugin("mcMMO");
			Plugin NameTags = Bukkit.getServer().getPluginManager().getPlugin("Nametags");
			if (mcMMO != null) {
				getLogger().info("Hooked into mcMMO. Hi mcMMO!");
				Registry.mcMMO(mcMMO);
			}
			if (NameTags != null){
				getLogger().info("Hooked into Nametags!");
				Registry.nameTags(NameTags);
			}
		}
		else{
			getLogger().warning("How is this plugin still running?");
		}

		
	}
}
