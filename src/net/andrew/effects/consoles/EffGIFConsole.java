package net.andrew.effects.consoles;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.map.MapPalette;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.Console;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.Main;

public class EffGIFConsole extends Effect{
	private Integer label;
	//EXPR:
	private Expression<String> url;
	private Expression<Console> console;
	private Expression<Number> x;
	private Expression<Number> y;
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		this.label = label;
		url = (Expression<String>) expr[0];
		console = (Expression<Console>) expr[1];
		if (label == 1){
			x = (Expression<Number>) expr[2];
			y = (Expression<Number>) expr[3];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean bool) {
		return "render gif [image] from (URL|FILE) %string% on [to] %console% [starting at %number%[,] %number%]";
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		ImageInputStream is;
		CanvasComponent comp;
		Console console1 = console.getSingle(e);
		Integer x1;
		Integer y1;
		if (label == 1){
			Number x2 = x.getSingle(e);
			Number y2 = y.getSingle(e);
			x2 = Math.round(x2.doubleValue());
			y2 = Math.round(y2.doubleValue());
			x1 = x2.intValue();
			y1 = y2.intValue();
		}
		else{
			x1 = 1;
			y1 = 1;
		}
		if (label == 0){
			try {
				InputStream is2 = new URL(url.getSingle(e)).openStream();
				is = ImageIO.createImageInputStream(is2);
			} catch (Exception e1) {
				Main.inst().getLogger().warning("Could not retreive image from URL " + url.getSingle(e) + " due to " + e1.getMessage() + "!");
				return;
			}
		}
		else{
			try {
				InputStream is2 = new FileInputStream(url.getSingle(e));
				is = ImageIO.createImageInputStream(is2);
			} catch (Exception e1) {
				Main.inst().getLogger().warning("Could not retreive image from FILE " + url.getSingle(e) + " due to " + e1.getMessage() + "!");
				return;
			}
		}
		try {
		    ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
		    reader.setInput(is);
		    int count = reader.getNumImages(true);
		    CanvasComponent prevcomp = null;
		    for (int index = 0; index < count; index++) {
		        BufferedImage frame = reader.read(index);
		        System.out.println(Integer.toString(count));
		        comp = console1.newComponent(frame.getWidth(), frame.getHeight())
						.painter((g, context) -> {
							for (int x = 0; x < frame.getWidth(); x++) {
							      for (int y = 0; y < frame.getHeight(); y++) {
							        int clr = frame.getRGB(x, y);
							        int red = (clr & 0x00ff0000) >> 16;
							        int green = (clr & 0x0000ff00) >> 8;
							        int blue = clr & 0x000000ff;
							        if((clr>>24) != 0x00 ) {
							        	g.draw(x, y, MapPalette.matchColor(red, green, blue));
							        }
							      }
							}
						}).create();
		        if (prevcomp != null){
		        	console1.getCanvas().removeComponent(prevcomp);
		        }
		        prevcomp = comp;
		        console1.getCanvas().putComponent(x1, y1, comp);
		        console1.getCanvas().repaint();
		        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.inst(), new Runnable() {
					@Override
					public void run() {
					}
				}, 10);
		    }
		} catch (IOException ex) {
			Main.inst().getLogger().warning("Could not retreive frames from gif " + url.getSingle(e) + " due to " + ex.getMessage() + "!");
		}
		
	}

}
