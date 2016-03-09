package net.andrew.expressions.random;

import org.bukkit.WeatherType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

import javax.annotation.Nullable;

public class ExprPlayerWeather extends SimpleExpression<WeatherType>{
	private static Expression<Player> player;
	@Override
	public Class<? extends WeatherType> getReturnType() {
		return WeatherType.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		ExprPlayerWeather.player = (Expression<Player>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[personal] weather of player";
	}
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		if (mode == ChangeMode.SET){
			ExprPlayerWeather.player.getSingle(e).setPlayerWeather((WeatherType) delta[0]); 
		}
		else if (mode == ChangeMode.REMOVE || mode == ChangeMode.RESET){
			ExprPlayerWeather.player.getSingle(e).setPlayerWeather(null);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.REMOVE || mode == ChangeMode.RESET)
			return CollectionUtils.array(WeatherType.class);
		return null;
	}
	@Override
	@Nullable
	protected WeatherType[] get(Event e) {
		return new WeatherType[]{ExprPlayerWeather.player.getSingle(e).getPlayerWeather()};
		
	}

}
