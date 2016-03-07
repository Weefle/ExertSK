package net.andrew.expressions.maps;

import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import org.bukkit.inventory.ItemStack;

public class ExprMapID extends SimpleExpression<Integer>{
	private Expression<ItemStack> item;
	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean, ParseResult paramParseResult) {
		item = (Expression<ItemStack>) expr[0];
		return true;
	}
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		if (mode == ChangeMode.SET){
			item.getSingle(e).setDurability(( ((Integer) delta[0]).shortValue()));
		}
		if (mode == ChangeMode.ADD){
			Short current = item.getSingle(e).getDurability();
			item.getSingle(e).setDurability((short) (current.shortValue() + ((Integer) delta[0]).shortValue()));
		}
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
		return "map id of %item%";
	}

	@SuppressWarnings("deprecation")
	@Override
	@Nullable
	protected Integer[] get(Event e) {
		return new Integer[]{(int) item.getSingle(e).getData().getData()};
	}

}
