package net.andrew.expressions.nametags;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;
import net.andrew.Main;
import net.andrew.Registry;
import xyz.flarereturns.nametags.api.API;

import javax.annotation.Nullable;

public class ExprNameTag extends SimpleExpression<String>{
	private static Expression<Player> player;
	private static Boolean prefix;
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
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		ExprNameTag.player = (Expression<Player>) expr[0];
		//0 1 2 3
		if (label >= 2){
			ExprNameTag.prefix = false;
		}
		else{
			ExprNameTag.prefix = true;
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		if (ExprNameTag.prefix){
			return "%player%'s [exter] [nametag][s] prefix";
		}
		else{
			return "%player%'s [exter] [nametag][s] suffix";
		}
	}
	@Override
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		if (mode == ChangeMode.SET){
			Player p = ExprNameTag.player.getSingle(e);
			String prefix = (String) delta[0];
			API api = Registry.nameTagsAPI();
			try {
				if (ExprNameTag.prefix){
					api.setTag(p, prefix);
				}
				else{
					api.setTag(p, prefix, true);
				}
			} catch (Exception e1) {
				if (ExprNameTag.prefix){
					Main.inst().getLogger().warning("Could not set prefix of " + p.getName() + " to " + prefix + " because of " + e1.getMessage());
				}
				else{
					Main.inst().getLogger().warning("Could not set suffix of " + p.getName() + " to " + prefix + " because of " + e1.getMessage());
				}
			}
		}
	}
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET)
			return CollectionUtils.array(String.class);
		return null;
	}
	@Override
	@Nullable
	protected String[] get(Event e) {
		API api = Registry.nameTagsAPI();
		Player p = ExprNameTag.player.getSingle(e);
		try {
			if (ExprNameTag.prefix){
				return new String[]{api.getPrefix(p)};
			}
			else {
				return new String[]{api.getSuffix(p)};
			}
		} catch (Exception e1) {
			if (ExprNameTag.prefix){
				Main.inst().getLogger().warning("Could not get prefix of " + p.getName() + " because of " + e1.getMessage());
			}
			else{
				Main.inst().getLogger().warning("Could not get suffix of " + p.getName() + " because of " + e1.getMessage());
			}
		}
		return null;
	}

}
