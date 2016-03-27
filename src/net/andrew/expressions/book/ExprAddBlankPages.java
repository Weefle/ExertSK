package net.andrew.expressions.book;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ExprAddBlankPages extends Effect{
	private Expression<Integer> EXPRpages;
	private Expression<ItemStack> EXPRbook;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		EXPRpages = (Expression<Integer>) expr[0];
		EXPRbook = (Expression<ItemStack>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "add %integer% page[s] to %itemstack%";
	}

	@Override
	protected void execute(Event e) {
		if (EXPRbook.getSingle(e).getType() == Material.WRITTEN_BOOK){
			BookMeta book = (BookMeta) EXPRbook.getSingle(e).getItemMeta();
			String text = "";
			for(int i=0 ; i < EXPRpages.getSingle(e) ;i++){
				book.addPage(text);
			}
			EXPRbook.getSingle(e).setItemMeta(book);
		}
	}

}
