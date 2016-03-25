package net.andrew.switches;

import java.util.List;
import javax.annotation.Nullable;

import org.bukkit.event.Event;

import ch.njol.skript.ScriptLoader;
import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.TriggerSection;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
public class ConSwitchCompare extends Condition{
	private TriggerSection[] sections;
	private String switchcond;
	private Object exp;
	private String compstring;
	private Integer pr;
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		List<TriggerSection> tempsections = ScriptLoader.currentSections;
		sections = new TriggerSection[tempsections.size()];
		Integer i = 0;
		pr = arg1;
		for (TriggerSection s : tempsections){
			sections[i] = s;
			i++;
		}
		exp = expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "case %string%";
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean check(Event e) {
		String[] sectionss = new String[sections.length];
		Integer i = 0;
		for (TriggerSection s : sections){
			sectionss[i] = s.toString(e, true);
			i++;
		}
		switchcond = sectionss[sectionss.length - 1];
		String[] split = switchcond.split("\\s+");
		//split[1] = String to compare
		switch(pr){
			case 0:
				compstring = ((Expression<String>) exp).getSingle(e);
				break;
			case 1:
				compstring = ((Expression<Number>) exp).getSingle(e).toString();
				break;
		}
		@SuppressWarnings("unused")
		TriggerSection switchc = sections[sections.length - 1];
		if (compstring.equals(split[1])){
			return true;
		}
		return false;
	}

}
