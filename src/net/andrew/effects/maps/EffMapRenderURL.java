package net.andrew.effects.maps;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Event;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapView;

import java.net.URL;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.utils.maps.ImageMapUtil;

public class EffMapRenderURL extends Effect{
	//render image from URL %string% on map [with id] %integer% [starting at %integer%, %integer%]
	private Expression<Number> MapID;
	private Expression<String> URL;
	private static Expression<Number> x;
	private static Expression<Number> y;
	private static Integer x1 = 0;
	private static Integer y1 = 0;
	private static BufferedImage image = null;
	private static Integer alias = 0;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int i, Kleenean paramKleenean, ParseResult paramParseResult) {
		MapID = (Expression<Number>) expr[1];
		URL = (Expression<String>) expr[0];
		if (expr[2] != null){
			EffMapRenderURL.x = (Expression<Number>) expr[2];
			EffMapRenderURL.y = (Expression<Number>) expr[3];	
		}
		EffMapRenderURL.alias = i;
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "render image from url %string% on map [with id] %integer% [starting at %integer%, %integer%]";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		if (x != null || y != null){
			x1 = x.getSingle(e).intValue();
			y1 = y.getSingle(e).intValue();
		}
		//Bukkit.getServer().broadcastMessage(MapID.getSingle(e).toString() + "" + URL.getSingle(e).toString());
		MapView map = Bukkit.getMap(MapID.getSingle(e).shortValue());
		String url = URL.getSingle(e).toString();
		try {
			image = ImageIO.read(new URL(url));
		} catch (IOException e2) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage("[ExterSK] " + ChatColor.RED + "Could not load from URL " + ChatColor.GREEN + URL.getSingle(e).toString() + " " + ChatColor.GOLD + e2.getMessage() + ChatColor.RESET);
			return;
		}
		if (EffMapRenderURL.alias == 1){
			image = MapPalette.resizeImage(image);
		}
		map.addRenderer(new ImageMapUtil(image, x1, y1));
	}
}

