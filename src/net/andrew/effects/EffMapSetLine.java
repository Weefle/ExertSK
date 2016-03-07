package net.andrew.effects;

import org.bukkit.Bukkit;

import org.bukkit.event.Event;
import org.bukkit.map.MapView;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.utils.LinePixelMapUtil;

public class EffMapSetLine extends Effect{
	//draw line from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%
	private static Expression<Integer> MapID;
	private static Expression<String> color;
	private static Expression<Integer> x;
	private static Expression<Integer> y;
	private static Expression<Integer> x2;
	private static Expression<Integer> y2;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int paramInt, Kleenean paramKleenean, ParseResult paramParseResult) {
			EffMapSetLine.x = (Expression<Integer>) expr[0];
			EffMapSetLine.y = (Expression<Integer>) expr[1];
			EffMapSetLine.x2 = (Expression<Integer>) expr[2];
			EffMapSetLine.y2 = (Expression<Integer>) expr[3];
			EffMapSetLine.MapID = (Expression<Integer>) expr[4];
			EffMapSetLine.color = (Expression<String>) expr[5];
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "draw line from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		//Bukkit.getServer().broadcastMessage(MapID.getSingle(e).toString() + "" + URL.getSingle(e).toString());
		MapView map = Bukkit.getMap(MapID.getSingle(e).shortValue());
		map.addRenderer( new LinePixelMapUtil(color.getSingle(e), x.getSingle(e), y.getSingle(e), x2.getSingle(e), y2.getSingle(e)) );
	}
}

