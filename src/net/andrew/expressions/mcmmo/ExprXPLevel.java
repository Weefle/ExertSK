package net.andrew.expressions.mcmmo;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import com.gmail.nossr50.api.ExperienceAPI;
import com.gmail.nossr50.datatypes.skills.SkillType;

import javax.annotation.Nullable;

import ch.njol.skript.classes.Changer;
import ch.njol.skript.classes.Changer.ChangeMode;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;
import ch.njol.util.coll.CollectionUtils;

public class ExprXPLevel extends SimpleExpression<Integer>{
	private static Expression<SkillType> skill ;
	private static Expression<Player> player;

	//[mcmmo] %mcmmoskill% level of %player% | %player%'s [mcmmo] %mcmmoskill% level
	@Override
	public Class<? extends Integer> getReturnType() {
		return Integer.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		if (label == 0){
			ExprXPLevel.skill = (Expression<SkillType>) expr[0];
			ExprXPLevel.player = (Expression<Player>) expr[1];
		}
		else{
			ExprXPLevel.skill = (Expression<SkillType>) expr[1];
			ExprXPLevel.player = (Expression<Player>) expr[0];
		}
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[mcmmo] %mcmmoskill% level of %player%";
	}
	public void change(Event e, Object[] delta, Changer.ChangeMode mode){
		if(mode == ChangeMode.SET){
			Long i = (Long) delta[0];
			Integer i2 = i.intValue();
			ExperienceAPI.setLevel(player.getSingle(e), skill.getSingle(e).getName(), i2);
		}
		else if (mode == ChangeMode.ADD){
			Long i = (Long) delta[0];
			Integer i2 = i.intValue();
			ExperienceAPI.addLevel(player.getSingle(e), skill.getSingle(e).getName(), i2);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public Class<?>[] acceptChange(final Changer.ChangeMode mode) {
		if (mode == ChangeMode.SET || mode == ChangeMode.ADD)
			return CollectionUtils.array(Integer.class);
		return null;
	}
	@Override
	protected Integer[] get(final Event e) {
		SkillType skill = ExprXPLevel.skill.getSingle(e);
		Player player = ExprXPLevel.player.getSingle(e);
		return new Integer[] { new Integer(ExperienceAPI.getLevel(player,skill.getName())) };

	}

}
