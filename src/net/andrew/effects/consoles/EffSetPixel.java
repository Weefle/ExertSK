package net.andrew.effects.consoles;

import org.bukkit.event.Event;
import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.Console;


import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetPixel extends Effect{
	private Expression<Number> x;
	private Expression<Number> y;
	private Expression<Number> color;
	private Expression<Console> console;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		x = (Expression<Number>) expr[0];
		y = (Expression<Number>) expr[1];
		color = (Expression<Number>) expr[2];
		console = (Expression<Console>) expr[3];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "set pixel at [x][ ]%number%[,] [y][ ]%number% to [color] %number% on console %console%";
	}

	@Override
	protected void execute(Event e) {
		Number x1 = x.getSingle(e);
		Number y1 = y.getSingle(e);
		Number color1 = color.getSingle(e);
		x1 = Math.round(x1.doubleValue());
		y1 = Math.round(y1.doubleValue());
		color1 = Math.round(color1.doubleValue());
		Integer x2 = x1.intValue();
		Integer y2 = x1.intValue();
		Byte color2 = color1.byteValue();
		Console console1 = console.getSingle(e);
		CanvasComponent comp = console1.newComponent(1, 1)
		.painter((g, context) -> {
			g.draw(x2, y2, color2);
		}).create();
		console1.getCanvas().putComponent(x2, y2, comp);
		}
		
}
