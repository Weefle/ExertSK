package net.andrew;

import java.io.IOException;
import java.util.logging.Logger;
import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapPalette;
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
import net.andrew.effects.EffClearMap;
import net.andrew.effects.EffMapRenderFile;
import net.andrew.effects.EffMapRenderText;
import net.andrew.effects.EffMapRenderURL;
import net.andrew.effects.EffMapSetBorderRect;
import net.andrew.effects.EffMapSetLine;
import net.andrew.effects.EffMapSetPixel;
import net.andrew.effects.EffMapSetRect;
import net.andrew.expressions.ExprMapID;
import net.andrew.metrics.Metrics;
import net.andrew.utils.ColorMapUtil;

@SuppressWarnings("unused")
public class Main extends JavaPlugin{
	private FileConfiguration config;

	@Override
	public void onEnable(){
		if (Bukkit.getPluginManager().getPlugin("Skript") != null) {
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
		    //SKRIPT REGISTERING
			Skript.registerAddon(this);
			//CLEAR MAP
			Skript.registerEffect(EffClearMap.class, "clear map %integer%");
			//RENDER IMAGE FROM URL
			Skript.registerEffect(EffMapRenderURL.class, "render image from url %string% on map [with id] %integer% [starting at %integer%, %integer%]");
			//RENDER IMAGE FROM FILE
			Skript.registerEffect(EffMapRenderFile.class, "render image from file %string% on map [with id] %integer% [starting at %integer%, %integer%]");
			//RENDER IMAGE FROM FILE
			Skript.registerEffect(EffMapRenderText.class, "render text %string% on map [with id] %integer% [starting at %integer%, %integer%]");
			//SET PIXEL ON MAP TO "COLOR"
			Skript.registerEffect(EffMapSetPixel.class, "set pixel at %integer%[,] %integer% on map [with id] %integer% to [color] %string%");
			//DRAW LINE ON MAP WITH COLOR
			Skript.registerEffect(EffMapSetLine.class, "draw line from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%");
			//DRAW RECTANGLE ON MAP WITH COLOR
			Skript.registerEffect(EffMapSetRect.class, "draw rectangle from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%");
			//DRAW RECTANGLE BORDER ON MAP WITH COLOR
			//Skript.registerEffect(EffMapSetBorderRect.class, "draw rect[angle] border from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%");
			//MAP ID EXPR
			Skript.registerExpression(ExprMapID.class, Integer.class, ExpressionType.PROPERTY, "map id of %itemstack%", "%itemstack%'s map id");
			//MAP INIT EVT
			Skript.registerEvent("Map Initialize", SimpleEvent.class, MapInitializeEvent.class, "map initialize", "map init");
			//EVENT-INTEGER
			EventValues.registerEventValue(MapInitializeEvent.class, Number.class, new Getter<Number, MapInitializeEvent>() {
				@Override
				@SuppressWarnings("deprecation")
				public Long get(MapInitializeEvent e) {
					return Long.valueOf(e.getMap().getId());
				}
			}, 0);
		}
	}
}
