package net.andrew.effects.random;

import org.bukkit.GameMode;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
public class EffSetSpectateTarget extends Effect{
	private static Expression<Player> player;
	private static Expression<Entity> entity;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		EffSetSpectateTarget.player = (Expression<Player>) expr[0];
		EffSetSpectateTarget.entity = (Expression<Entity>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "make %player% spectate %entity%";
	}

	@Override
	protected void execute(Event e) {
    	Player player = EffSetSpectateTarget.player.getSingle(e);
    	if (player.getGameMode() != GameMode.SPECTATOR){
    		player.setGameMode(GameMode.SPECTATOR);
    	}
    	player.setSpectatorTarget(EffSetSpectateTarget.entity.getSingle(e));
	}

}
