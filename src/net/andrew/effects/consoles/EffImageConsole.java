package net.andrew.effects.consoles;

import org.bukkit.event.Event;
import org.bukkit.map.MapPalette;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.Console;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.Main;

public class EffImageConsole extends Effect{
	private Expression<String> URL;
	private Expression<Console> console;
	private URL url1;
	private BufferedImage image;
	private Integer pr;
	private Integer label;
	private File file1;
	private Expression<Number> x;
	private Expression<Number> y;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		URL = (Expression<String>) expr[0];
		console = (Expression<Console>) expr[1];
		pr = arg3.mark;
		label = arg1;
		if (arg1 == 1){
			x = (Expression<Number>) expr[2];
			y = (Expression<Number>) expr[3];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "render image from (URL|FILE) %string% on [to] %console%";
	}

	@Override
	protected void execute(Event e) {
		try {
			url1 = new URL(URL.getSingle(e));
			file1 = new File(URL.getSingle(e));
		}
		catch (MalformedURLException e1) {
			Main.inst().getLogger().warning("Invalid URL " + URL.getSingle(e) + " for render image on console!");
			return;
		}
		try {
			if (pr == 0)
				image = ImageIO.read(url1);
			else if (pr == 1)
				image = ImageIO.read(file1);
		} catch (IOException e2) {
			String s;
			if (pr == 0){
				s = "URL ";
			}
			else{
				s= "FILE ";
			}
			Main.inst().getLogger().warning("Could not retreive image from" + s + URL.getSingle(e) + " Due to:" + e2.getMessage());
			return;
		}
		Console console1 = console.getSingle(e);
		@SuppressWarnings("deprecation")
		CanvasComponent comp = console1.newComponent(image.getWidth(), image.getHeight())
				.painter((g, context) -> {
					for (int x = 0; x < image.getWidth(); x++) {
					      for (int y = 0; y < image.getHeight(); y++) {
					        int clr = image.getRGB(x, y);
					        int red = (clr & 0x00ff0000) >> 16;
					        int green = (clr & 0x0000ff00) >> 8;
					        int blue = clr & 0x000000ff;
					        if((clr>>24) != 0x00 ) {
					        	g.draw(x, y, MapPalette.matchColor(red, green, blue));
					        }
					      }
					}
				}).create();
		Integer x1 = 1;
		Integer y1 = 1;
		if (label == 1){
			Number x2 = x.getSingle(e);
			Number y2 = y.getSingle(e);
			x1 = x2.intValue();
			y1 = y2.intValue();
		}
		console1.getCanvas().putComponent(x1, y1, comp);
	}
}

