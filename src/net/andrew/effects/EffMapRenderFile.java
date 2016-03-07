package net.andrew.effects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Event;
import org.bukkit.map.MapView;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.utils.ImageMapUtil;
public class EffMapRenderFile extends Effect{
	//render image from FILE %string% on map [with id] %integer% [starting at %integer%, %integer%]
	private Expression<Number> MapID;
	private Expression<String> FileURL;
	private BufferedImage image = null;
	private static Expression<Number> x;
	private static Expression<Number> y;
	private static Integer x1 = 0;
	private static Integer y1 = 0;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int paramInt, Kleenean paramKleenean, ParseResult paramParseResult) {
		MapID = (Expression<Number>) expr[1];
		FileURL = (Expression<String>) expr[0];
		if (expr[2] != null){
			EffMapRenderFile.x = (Expression<Number>) expr[2];
			EffMapRenderFile.y = (Expression<Number>) expr[3];	
		}
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "render image from file %string% on map [with id] %integer% [starting at %integer%, %integer%]";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		if (x != null || y != null){
			x1 = x.getSingle(e).intValue();
			y1 = y.getSingle(e).intValue();
		}
		//Bukkit.getServer().broadcastMessage(MapID.getSingle(e).toString() + "" + FileURL.getSingle(e).toString());
		MapView map = Bukkit.getMap(MapID.getSingle(e).shortValue());
		String url = FileURL.getSingle(e).toString();
		try {
			image = ImageIO.read(new File(url));
		} catch (IOException e2) {
			ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
			console.sendMessage("[ExterSK] " + ChatColor.RED + "Could not load from FILE " + ChatColor.GREEN + FileURL.getSingle(e).toString() + " " + ChatColor.GOLD + e2.getMessage() + ChatColor.RESET);
		}
		map.addRenderer(new ImageMapUtil(image, x1, y1));
	}
}

