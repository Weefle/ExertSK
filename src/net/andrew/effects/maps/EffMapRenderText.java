package net.andrew.effects.maps;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.map.MapView;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.utils.maps.TextMapUtil;

public class EffMapRenderText extends Effect{
	//render text %string% on map [with id] %integer% [starting at %integer%, %integer%]
	private Expression<Number> MapID;
	private static Expression<Number> x;
	private static Expression<Number> y;
	private static Integer x1 = 0;
	private static Integer y1 = 0;
	private static Expression<String> text = null;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int paramInt, Kleenean paramKleenean, ParseResult paramParseResult) {
		MapID = (Expression<Number>) expr[1];
		EffMapRenderText.text = (Expression<String>) expr[0];
		if (expr[2] != null){
			EffMapRenderText.x = (Expression<Number>) expr[2];
			EffMapRenderText.y = (Expression<Number>) expr[3];	
		}
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "render text %string% on map [with id] %integer% [starting at %integer%, %integer%]";
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
		map.addRenderer(new TextMapUtil(text.getSingle(e).toString(), x1, y1));
	}
}

