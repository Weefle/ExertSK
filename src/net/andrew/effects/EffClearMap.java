package net.andrew.effects;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import java.util.List;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffClearMap extends Effect{
	private Expression<Number> MapID;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int paramInt, Kleenean paramKleenean, ParseResult paramParseResult) {
		MapID = (Expression<Number>) expr[0];
		return true;
	}
	@Override
	public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
		return "map clear %number%";
	}
	@SuppressWarnings("deprecation")
	@Override
	protected void execute(Event e) {
		//Bukkit.getServer().broadcastMessage(MapID.getSingle(e).toString());
		MapView map = Bukkit.getMap(MapID.getSingle(e).shortValue());
		List<MapRenderer> renders = map.getRenderers();
		for (MapRenderer data : renders){
			map.removeRenderer(data);
		}
	}
}


