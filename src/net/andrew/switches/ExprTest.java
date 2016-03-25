package net.andrew.switches;

import java.util.List;

import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.TriggerSection;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprTest extends SimpleExpression<String> {
	private TriggerSection[] sections;
	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public Class<? extends String> getReturnType() {
		return String.class;
	}

	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean isDelayed, ParseResult result) {
		List<TriggerSection> tempsections = ScriptLoader.currentSections;
		sections = new TriggerSection[tempsections.size()];
		Integer i = 0;
		for (TriggerSection s : tempsections){
			sections[i] = s;
			i++;
		}
		return true;
	}
	@Override
	public String toString(@Nullable Event e, boolean debug) {
		return "tests";
	}

	@Override
	@Nullable
	protected String[] get(Event e) {
		String[] sectionss = new String[sections.length];
		Integer i = 0;
		for (TriggerSection s : sections){
			sectionss[i] = s.toString(e, true);
			i++;
		}
		return new String[]{sectionss[sectionss.length - 1]};
		
	}
}