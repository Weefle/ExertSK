package net.andrew.switches;

import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ConSwitch extends Condition{
	private Object v;
	private Integer i;
	public static String Value;
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		i = arg1;
		v = expr[0];
		return true;
	}
	public static String getValue(){
		return Value;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "Switch: " + Value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Event e) {
		switch (i){
			case 0:
				Value = ((Expression<String>) v).getSingle(e);
				break;
			case 1:
				Value = ((Expression<Number>) v).getSingle(e).toString();
				break;
		}
		return true;
	}

}
