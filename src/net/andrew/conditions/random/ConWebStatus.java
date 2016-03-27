package net.andrew.conditions.random;

import org.bukkit.event.Event;

import java.net.HttpURLConnection;
import java.net.URL;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;

public class ConWebStatus extends Condition{
	private Expression<String> EXPRurl;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		EXPRurl = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event e, boolean arg1) {
		return "%string% is (online|working|not offline|good|not bad|not 404|not down)";
	}

	@Override
	public boolean check(Event e) {
		try{
			URL u = new URL (EXPRurl.getSingle(e));
			HttpURLConnection huc =  ( HttpURLConnection )  u.openConnection (); 
			huc.setRequestMethod ("GET");  //OR  huc.setRequestMethod ("HEAD"); 
			huc.connect(); 
			return true;
		}
		catch(Exception e1){
			return false;
		}
	}

}
