package net.andrew.effects.consoles;

import org.bukkit.event.Event;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.Console;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffSetBackConsole extends Effect{
	private Expression<Number> color;
	private Expression<Console> console;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		color = (Expression<Number>) expr[1];
		console = (Expression<Console>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "set background of %console% to [color] %number%";
	}

	@Override
	protected void execute(Event e) {
		Number color1 = color.getSingle(e);
		color1 = Math.round(color1.doubleValue());
		Byte color2 = color1.byteValue();
		Console console1 = console.getSingle(e);
		CanvasComponent comp = console1.newComponent(console1.getFrameWdith() * 128, console1.getFrameHeight() * 128)
				.painter((g, context) -> {
					for (int x = -10; x < g.getWidth() - 10; x++) {
				        for (int y = -7; y < g.getHeight() - 7; y++) {
				            g.draw(x, y, color2);
				        }
				    }
				}).create();
		console1.getCanvas().putComponent(10, 7, comp);
	}

}
