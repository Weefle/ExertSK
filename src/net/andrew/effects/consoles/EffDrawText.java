package net.andrew.effects.consoles;

import org.bukkit.event.Event;
import org.bukkit.map.MinecraftFont;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.Console;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffDrawText extends Effect{
	private Expression<String> text;
	private Expression<Console> console;
	private Expression<Number> color;
	private Expression<Number> x;
	private Expression<Number> y;
	private Integer x1;
	private Integer y1;
	private Integer l;
	private CanvasComponent comp;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		text = (Expression<String>) expr[0];
		console = (Expression<Console>) expr[1];
		color = (Expression<Number>) expr[2];
		if (arg1 == 1){
			x = (Expression<Number>) expr[3];
			y = (Expression<Number>) expr[4];
		}
		l = arg1;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "(render|draw) text %string% on [console] %console% with [color] %number% [starting at %number% %number%]";
	}

	@Override
	protected void execute(Event e) {
		Number color1 = color.getSingle(e);
		color1 = Math.round(color1.doubleValue());
		Byte color2 = color1.byteValue();
		Console console1 = console.getSingle(e);
		String text1 = text.getSingle(e);
		if (l == 1){
			Number x3 = x.getSingle(e);
			Number y3 = y.getSingle(e);
			Number x2 = Math.round(x3.doubleValue());
			Number y2 = Math.round(y3.doubleValue());
			x1 = x2.intValue();
			y1 = y2.intValue();
		}
		else{
			x1 = 1;
			y1 = 1;
		}
		comp = console1.newComponent(MinecraftFont.Font.getWidth(text1), MinecraftFont.Font.getHeight())
				.painter((g, context) -> {
		            g.setFont(MinecraftFont.Font);
		            g.draw(1, 1, color2, text1);
				}).create();
		console1.getCanvas().putComponent(x1, y1, comp);
	}

}
