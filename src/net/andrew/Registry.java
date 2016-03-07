package net.andrew;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.plugin.Plugin;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.ClassInfo;
import ch.njol.skript.classes.Parser;
import ch.njol.skript.lang.ExpressionType;
import ch.njol.skript.lang.ParseContext;
import ch.njol.skript.lang.util.SimpleEvent;
import ch.njol.skript.registrations.Classes;
import ch.njol.skript.registrations.EventValues;
import ch.njol.skript.util.Getter;
import net.andrew.effects.maps.EffClearMap;
import net.andrew.effects.maps.EffMapRenderFile;
import net.andrew.effects.maps.EffMapRenderText;
import net.andrew.effects.maps.EffMapRenderURL;
import net.andrew.effects.maps.EffMapSetLine;
import net.andrew.effects.maps.EffMapSetPixel;
import net.andrew.effects.maps.EffMapSetRect;
import net.andrew.effects.random.EffForceRespawn;
import net.andrew.effects.random.EffServerCommand;
import net.andrew.expressions.maps.ExprMapID;
import net.andrew.expressions.mcmmo.ExprMcMMOSkill;
import net.andrew.expressions.mcmmo.ExprRawXPLevel;
import net.andrew.expressions.mcmmo.ExprXPLevel;
import net.andrew.expressions.random.ExprLastOutput;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;
@SuppressWarnings("unused")
public class Registry {
	public static void SkriptMaps(){
		//CLEAR MAP
		Skript.registerEffect(EffClearMap.class, "clear map %integer%");
		//RENDER IMAGE FROM URL
		Skript.registerEffect(EffMapRenderURL.class, "render image from url %string% on map [with id] %integer% [starting at %integer%, %integer%]", "render resized image from url %string% on map [with id] %integer% [starting at %integer%, %integer%]");
		//RENDER IMAGE FROM FILE
		Skript.registerEffect(EffMapRenderFile.class, "render image from file %string% on map [with id] %integer% [starting at %integer%, %integer%]", "render resized image from file %string% on map [with id] %integer% [starting at %integer%, %integer%]");
		//RENDER IMAGE FROM FILE
		Skript.registerEffect(EffMapRenderText.class, "render text %string% on map [with id] %integer% [starting at %integer%, %integer%]");
		//SET PIXEL ON MAP TO "COLOR"
		Skript.registerEffect(EffMapSetPixel.class, "set pixel at %integer%[,] %integer% on map [with id] %integer% to [color] %string%");
		//DRAW LINE ON MAP WITH COLOR
		Skript.registerEffect(EffMapSetLine.class, "draw line from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%");
		//DRAW RECTANGLE ON MAP WITH COLOR
		Skript.registerEffect(EffMapSetRect.class, "draw rectangle from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%");
		//DRAW RECTANGLE BORDER ON MAP WITH COLOR
		//Skript.registerEffect(EffMapSetBorderRect.class, "draw rect[angle] border from %integer%[,] %integer% to %integer%[,] %integer% on map [with id] %integer% with [color] %string%");
		//MAP ID EXPR
		Skript.registerExpression(ExprMapID.class, Integer.class, ExpressionType.PROPERTY, "map id of %itemstack%", "%itemstack%'s map id");
		//MAP INIT EVT
		Skript.registerEvent("Map Initialize", SimpleEvent.class, MapInitializeEvent.class, "map initialize", "map init");
		//EVENT-INTEGER
		EventValues.registerEventValue(MapInitializeEvent.class, Number.class, new Getter<Number, MapInitializeEvent>() {
			@Override
			@SuppressWarnings("deprecation")
			public Long get(MapInitializeEvent e) {
				return Long.valueOf(e.getMap().getId());
			}
		}, 0);
	}
	public static void mcMMO(Plugin mcMMO) {
		mcMMO.getLogger().info("Found ExterSK! Hello ExterSK!");
		//SKILL LEVEL GET
		Skript.registerExpression(ExprXPLevel.class, Integer.class, ExpressionType.PROPERTY, "[mcmmo] %mcmmoskill% level of %player%", "%player%'s [mcmmo] %mcmmoskill% level");
		//RAW XP FROM SKILL GET
		Skript.registerExpression(ExprRawXPLevel.class, Integer.class, ExpressionType.PROPERTY, "[mcmmo] %mcmmoskill% [e]xp[erience] of %player%", "%player%'s [mcmmo] %mcmmoskill% [e]xp[erience]");
		//MCMMMO SKILL TYPE
		Classes.registerClass(new ClassInfo<SkillType>(SkillType.class, "mcmmoskill")
				.name("mcmmoskill").parser(new Parser<SkillType>() {
					@Override
					@Nullable
					public SkillType parse(String s, ParseContext context) {
						try {

							return SkillType.valueOf(s.toUpperCase());
						} catch (Exception e) {

						}
						return null;
					}

					@Override
					public String toString(SkillType skill, int flags) {
						return skill.getName().toLowerCase();
					}

					@Override
					public String toVariableNameString(SkillType skill) {
						return skill.getName().toLowerCase();
					}

					@Override
					public String getVariableNamePattern() {
						return ".+";
					}

				}));
		//MCMMO SKILL EXPR
		Skript.registerExpression(ExprMcMMOSkill.class, SkillType.class, ExpressionType.PATTERN_MATCHES_EVERYTHING, "ACROBATICS", "ALCHEMY", "ARCHERY", "AXES", "EXCAVATION", "FISHING", "HERBALISM", "MINING", "REPAIR", "SALVAGE", "SMELTING", "SWORDS", "TAMING", "UNARMED", "WOODCUTTING");
		//LEVEL UP EVENT
		Skript.registerEvent("mcmmo skill level up", SimpleEvent.class, McMMOPlayerLevelUpEvent.class, "[mcmmo] [skill] level up");
		//EVENT-STRING
		EventValues.registerEventValue(McMMOPlayerLevelUpEvent.class, String.class, new Getter<String, McMMOPlayerLevelUpEvent>() {
			@Override
			public String get(McMMOPlayerLevelUpEvent e) {
				return e.getSkill().getName().toUpperCase();
			}
		}, 0);
		//EVENT-INTEGER
		EventValues.registerEventValue(McMMOPlayerLevelUpEvent.class, Number.class, new Getter<Number, McMMOPlayerLevelUpEvent>() {
			@Override
			public Long get(McMMOPlayerLevelUpEvent e) {
				return Long.valueOf(e.getSkillLevel());
			}
		}, 0);
		
		
	}
	public static void Random(){
		Skript.registerEffect(EffForceRespawn.class, "[exter] force respawn %player%");
		if (Config.getConfig().getBoolean("Enable Server Command Effect") && Config.getConfig().getString("Password Hash") != ""){
			Skript.registerEffect(EffServerCommand.class, "run command %string% with password %string% on OS");
			Skript.registerExpression(ExprLastOutput.class, String.class, ExpressionType.SIMPLE, "last [exter] [server command] output");
		}
	}
}

