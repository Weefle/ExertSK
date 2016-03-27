package net.andrew.expressions.random;

import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprIWItemFrame extends SimpleExpression<ItemStack>{
	private Expression<Entity> iframe;

	@Override
	public Class<? extends ItemStack> getReturnType() {
		return ItemStack.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		iframe = (Expression<Entity>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[exert] item [with]in %entity%";
	}

	@Override
	@Nullable
	protected ItemStack[] get(Event e) {
		if (iframe != null){
			if (iframe.getSingle(e) instanceof ItemFrame){
				ItemFrame frame = (ItemFrame) iframe.getSingle(e);
				return new ItemStack[]{frame.getItem()};
			}
		}
		return null;
	}


}
