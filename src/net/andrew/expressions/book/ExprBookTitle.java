package net.andrew.expressions.book;

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
import org.bukkit.inventory.meta.BookMeta;

public class ExprBookTitle extends SimpleExpression<String>{
	private Expression<ItemStack> item;
	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
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
			BookMeta book = (BookMeta) item.getSingle(e).getItemMeta();
			book.setTitle((String) delta[0]);
			item.getSingle(e).setItemMeta(book);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(String.class);
		return null;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[book] title of %itemstack%";
	}
	
	@Override
	@Nullable
	protected String[] get(Event e) {
		BookMeta book = (BookMeta) item.getSingle(e).getItemMeta();
		return new String[]{book.getTitle()};
	}

}
