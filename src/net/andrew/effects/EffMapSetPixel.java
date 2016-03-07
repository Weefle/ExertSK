package net.andrew.effects;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.map.MapView;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.utils.SetPixelMapUtil;

public class EffMapSetPixel extends Effect{
	//set pixel at %integer%, %integer% on map [with id] %integer% to [color] %string%
	private static Expression<Number> MapID;
	private static Expression<String> color;
	private static Expression<Integer> x;
	private static Expression<Integer> y;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int paramInt, Kleenean paramKleenean, ParseResult paramParseResult) {
			EffMapSetPixel.x = (Expression<Integer>) expr[0];
			EffMapSetPixel.y = (Expression<Integer>) expr[1];
			EffMapSetPixel.MapID = (Expression<Number>) expr[2];
			EffMapSetPixel.color = (Expression<String>) expr[3];
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "set pixel at %integer%, %integer% on map [with id] %integer% to [color] %string%";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		//Bukkit.getServer().broadcastMessage(MapID.getSingle(e).toString() + "" + URL.getSingle(e).toString());
		MapView map = Bukkit.getMap(MapID.getSingle(e).shortValue());
		map.addRenderer(new SetPixelMapUtil(color.getSingle(e), EffMapSetPixel.x.getSingle(e), EffMapSetPixel.y.getSingle(e)));
	}
}

