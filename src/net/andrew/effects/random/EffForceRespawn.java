package net.andrew.effects.random;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

import javax.annotation.Nullable;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.Skript;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.log.ErrorQuality;
import ch.njol.util.Kleenean;
public class EffForceRespawn extends Effect{
	private static Expression<Player> player;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		if(!ScriptLoader.isCurrentEvent(EntityDeathEvent.class)){
			Skript.error("Force Respawn effects can only be used inside death events.", ErrorQuality.SEMANTIC_ERROR);
			return false;
		}
		EffForceRespawn.player = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[exter] force respawn %player%";
	}

	@Override
	protected void execute(Event e) {
    	EffForceRespawn.player.getSingle(e).spigot().respawn();
	}

}
