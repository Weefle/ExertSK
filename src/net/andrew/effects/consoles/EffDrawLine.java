package net.andrew.effects.consoles;

import org.bukkit.event.Event;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.Console;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffDrawLine extends Effect{
	private Expression<Number> x1;
	private Expression<Number> y1;
	private Expression<Number> x2;
	private Expression<Number> y2;
	private Expression<Number> color;
	private Expression<Console> console;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		x1 = (Expression<Number>) expr[0];
		y1 = (Expression<Number>) expr[1];
		x2 = (Expression<Number>) expr[2];
		y2 = (Expression<Number>) expr[3];
		color = (Expression<Number>) expr[4];
		console = (Expression<Console>) expr[5];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "(render|draw) line from [x][ ]%number%[,] [y][ ]%number% to [x][ ]%number%[,] [y][ ]%number% with color %number% on [console] %console%";
	}

	@Override
	protected void execute(Event e) {
		Number x1a = Math.round(x1.getSingle(e).doubleValue());
		Number y1a = Math.round(y1.getSingle(e).doubleValue());
		Number x2a = Math.round(x2.getSingle(e).doubleValue());
		Number y2a = Math.round(y2.getSingle(e).doubleValue());
		Number color1 = Math.round(color.getSingle(e).doubleValue());
		Integer x1b = x1a.intValue();
		Integer y1b = y1a.intValue();
		Integer x2b = x2a.intValue();
		Integer y2b = y2a.intValue();
		Integer colora = color1.intValue();
		Byte colorb = colora.byteValue();
		Console console1 = console.getSingle(e);
		CanvasComponent comp = console1.newComponent(1, 1)
				.painter((g, context) -> {
					Integer x = x1b;
					Integer y = y1b;
					Integer x2 = x2b;
					Integer y2 = y2b;
					int w = x2 - x ;
	                int h = y2 - y ;
	                int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
	                if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
	                if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
	                if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
	                int longest = Math.abs(w) ;
	                int shortest = Math.abs(h) ;
	                if (!(longest>shortest)) {
	                    longest = Math.abs(h) ;
	                    shortest = Math.abs(w) ;
	                    if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
	                    dx2 = 0 ;            
	                }
	                int numerator = longest >> 1 ;
	                for (int i=0;i<=longest;i++) {
	                    g.draw(x, y, colorb);
	                    numerator += shortest ;
	                    if (!(numerator<longest)) {
	                        numerator -= longest ;
	                        x += dx1 ;
	                        y += dy1 ;
	                    } else {
	                        x += dx2 ;
	                        y += dy2 ;
	                    }
	                }
				}).create();
				console1.getCanvas().putComponent(x1b, y1b, comp);
	}

}
