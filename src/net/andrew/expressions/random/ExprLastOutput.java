package net.andrew.expressions.random;

import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.andrew.utils.random.LastServerCommandOutput;


public class ExprLastOutput extends SimpleExpression<String>{
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.ADD)
			return CollectionUtils.array(Integer.class);
		return null;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "last [exter] [server command] output";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		return new String[]{LastServerCommandOutput.getLastError()};
	}

}
