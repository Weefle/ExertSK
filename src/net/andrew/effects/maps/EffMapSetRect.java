package net.andrew.effects.maps;

import org.bukkit.Bukkit;

import org.bukkit.event.Event;
import org.bukkit.map.MapView;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.utils.maps.RectanglePixelMapUtil;

public class EffMapSetRect extends Effect{
	//draw rectangle from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%
	private static Expression<Integer> MapID;
	private static Expression<String> color;
	private static Expression<Integer> x;
	private static Expression<Integer> y;
	private static Expression<Integer> x2;
	private static Expression<Integer> y2;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int paramInt, Kleenean paramKleenean, ParseResult paramParseResult) {
			EffMapSetRect.x = (Expression<Integer>) expr[0];
			EffMapSetRect.y = (Expression<Integer>) expr[1];
			EffMapSetRect.x2 = (Expression<Integer>) expr[2];
			EffMapSetRect.y2 = (Expression<Integer>) expr[3];
			EffMapSetRect.MapID = (Expression<Integer>) expr[4];
			EffMapSetRect.color = (Expression<String>) expr[5];
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "draw rectangle from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		//Bukkit.getServer().broadcastMessage(MapID.getSingle(e).toString() + "" + URL.getSingle(e).toString());
		MapView map = Bukkit.getMap(MapID.getSingle(e).shortValue());
		map.addRenderer(new RectanglePixelMapUtil(color.getSingle(e), x.getSingle(e), y.getSingle(e), x2.getSingle(e), y2.getSingle(e)));
	}
}

