package net.andrew.effects.consoles;

import org.bukkit.event.Event;

import ca.jarcode.consoles.api.Console;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffRemoveConsole extends Effect{
	private Expression<Console> console;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		console = (Expression<Console>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "remove [console] %console%";
	}

	@Override
	protected void execute(Event e) {
		console.getSingle(e).remove();
	}

}
