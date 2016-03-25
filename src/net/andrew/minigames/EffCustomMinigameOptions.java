package net.andrew.minigames;

import org.bukkit.event.Event;
import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class EffCustomMinigameOptions extends Effect{
	//HUNGER
	private String option = null;
	private Expression<?> value;
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult pr) {
		option = MinigameOption.intToOption(label);
		value = expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "ExertSK | Script Minigame | OPTION: ";
	}

	@Override
	protected void execute(Event e) {
		MinigameOption.setOptionValue(option, value.getSingle(e));
	}

}
