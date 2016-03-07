package net.andrew.expressions.mcmmo;

import org.bukkit.event.Event;

import com.gmail.nossr50.datatypes.skills.SkillType;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprMcMMOSkill extends SimpleExpression<SkillType>{
	private String skill;

	//"ACROBATICS", "ALCHEMY", "ARCHERY", "AXES", "EXCAVATION", "FISHING", "HERBALISM", "MINING", "REPAIR", "SALVAGE", "SMELTING", "SWORDS", "TAMING", "UNARMED", "WOODCUTTING"
	@Override
	public Class<? extends SkillType> getReturnType() {
		return SkillType.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		switch(label){
			case 0: this.skill = "ACROBATICS";
				break;
			case 1: this.skill = "ALCHEMY";
				break;
			case 2: this.skill = "ARCHERY";
				break;
			case 3: this.skill = "AXES";
				break;
			case 4: this.skill = "EXCAVATION";
				break;
			case 5: this.skill = "FISHING";
				break;
			case 6: this.skill = "HERBALISM";
				break;
			case 7: this.skill = "MINING";
				break;
			case 8: this.skill = "REPAIR";
				break;
			case 9: this.skill = "SALVAGE";
				break;
			case 10: this.skill = "SMELTING";
				break;
			case 11: this.skill = "SWORDS";
				break;
			case 12: this.skill = "TAMING";
				break;
			case 13: this.skill = "UNARMED";
				break;
			case 14: this.skill = "WOODCUTTING";
				break;
		}
		return true;
	}
	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "ExprMcMMoSkill: " + this.skill;
	}

	@Override
	@Nullable
	protected SkillType[] get(Event e) {
		return new SkillType[] { SkillType.getSkill(this.skill) };
	}

}
