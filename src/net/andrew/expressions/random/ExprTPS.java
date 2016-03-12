package net.andrew.expressions.random;

import org.bukkit.event.Event;
import javax.annotation.Nullable;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import net.andrew.utils.random.Lag;

public class ExprTPS extends SimpleExpression<Number>{

	@Override
	public Class<? extends Number> getReturnType() {
		return Number.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] arg0, int arg1, Kleenean arg2, ParseResult arg3) {
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[exter] [server] tps";
	}

	@Override
	@Nullable
	protected Number[] get(Event arg0) {
		return new Number[]{Lag.getTPS()};
	}

}
