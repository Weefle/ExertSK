package net.andrew;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.WeatherType;
import org.bukkit.block.BlockFace;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.inventory.ItemStack;
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
import net.andrew.effects.consoles.EffSetPixel;
import net.andrew.effects.consoles.EffDrawLine;
import net.andrew.effects.consoles.EffDrawText;
import net.andrew.effects.consoles.EffGIFConsole;
import net.andrew.effects.consoles.EffImageConsole;
import net.andrew.effects.consoles.EffRemoveConsole;
import net.andrew.effects.consoles.EffSetBackConsole;
import net.andrew.effects.consoles.EffUpdateConsole;
import net.andrew.effects.random.EffChatImageFromFILE;
import net.andrew.effects.random.EffChatImageFromURL;
import net.andrew.effects.random.EffForceRespawn;
import net.andrew.effects.random.EffLeashFence;
import net.andrew.effects.random.EffServerCommand;
import net.andrew.effects.random.EffSetSpectateTarget;
import net.andrew.expressions.book.ExprBookAuthor;
import net.andrew.expressions.book.ExprBookPages;
import net.andrew.expressions.book.ExprBookSpecificPage;
import net.andrew.expressions.book.ExprBookTitle;
import net.andrew.expressions.consoles.ExprNewConsole;
import net.andrew.expressions.maps.ExprMapID;
import net.andrew.expressions.mcmmo.ExprMcMMOSkill;
import net.andrew.expressions.mcmmo.ExprRawXPLevel;
import net.andrew.expressions.mcmmo.ExprXPLevel;
import net.andrew.expressions.nametags.ExprNameTag;
import net.andrew.expressions.random.ExprHastebin;
import net.andrew.expressions.random.ExprIWItemFrame;
import net.andrew.expressions.random.ExprLastOutput;
import net.andrew.expressions.random.ExprTPS;
import xyz.flarereturns.nametags.api.API;
import xyz.flarereturns.nametags.api.Nametags;

import com.gmail.nossr50.datatypes.skills.SkillType;
import com.gmail.nossr50.events.experience.McMMOPlayerLevelUpEvent;

import ca.jarcode.consoles.api.Console;
@SuppressWarnings("unused")
public class Registry {
	public static void SkriptBook(){
		Skript.registerExpression(ExprBookTitle.class, String.class, ExpressionType.PROPERTY, "[book] title of %itemstack%", "%itemstack%'s [book] title");
		Skript.registerExpression(ExprBookPages.class, String.class, ExpressionType.PROPERTY, "[all] pages of %itemstack%", "%itemstack%'s [full] pages");
		Skript.registerExpression(ExprBookSpecificPage.class, String.class, ExpressionType.PROPERTY, "page %integer% of %itemstack%");
		Skript.registerExpression(ExprBookAuthor.class, String.class, ExpressionType.PROPERTY, "[book] author of %itemstack%", "%itemstack%'s [book] author");
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
	public static void nameTags(Plugin nt){
		nt.getLogger().info("I was expecting you ExertSK!");
		Skript.registerExpression(ExprNameTag.class, String.class, ExpressionType.PROPERTY, "[exter] [nametag][s] prefix of %player%", "%player%'s [exter] [nametag][s] prefix", "[exter] [nametag][s] suffix of %player%", "%player%'s [exter] [nametag][s] suffix");
	}
	public static API nameTagsAPI(){
		return Nametags.getAPI();
	}
	public static void Random(){
		Skript.registerEffect(EffForceRespawn.class, "[exter] force respawn %player%");
		if (Config.getConfig().getBoolean("Enable Server Command Effect") && Config.getConfig().getString("Password Hash") != ""){
			Skript.registerEffect(EffServerCommand.class, "run command %string% with password %string% on OS");
			Skript.registerExpression(ExprLastOutput.class, String.class, ExpressionType.SIMPLE, "last [exter] [server command] output");
			
		}
		Skript.registerEffect(EffSetSpectateTarget.class, "make %player% spectate %entity%");
		Skript.registerExpression(ExprTPS.class, Number.class, ExpressionType.SIMPLE, "[exter] [server] tps");
		Skript.registerExpression(ExprHastebin.class, String.class, ExpressionType.PROPERTY, "[new] hastebin (key|identifier) (for|of) [text|string] %string%", "[new] hastebin URL (for|of) [text|string] %string%");
		Skript.registerExpression(ExprIWItemFrame.class, ItemStack.class, ExpressionType.SIMPLE, "[exert] item [with]in %entity%");
		Skript.registerEffect(EffChatImageFromURL.class, "send %players% [chat] image from url %string% with height %integer%[,] shade %integer%", "send %players% [chat] image from url %string% with height %integer%[,] shade %integer% with extra texts %strings%");
		Skript.registerEffect(EffChatImageFromFILE.class, "send %players% [chat] image from file %string% with height %integer%[,] shade %integer%", "send %players% [chat] image from file %string% with height %integer%[,] shade %integer% with extra texts %strings%");
		Skript.registerEffect(EffLeashFence.class, "(leash|lead) %livingentities% to %block%");
	}
	public static void consoles(Plugin c){
		c.getLogger().info("Yes!!!");
		Skript.registerExpression(ExprNewConsole.class,Console.class,ExpressionType.SIMPLE,"[a] new console facing (0好orth|1圯ast|2安est|3存outh) at %location% with [width] %number%[ and|,] [height] %number%");
		Classes.registerClass(new ClassInfo<Console>(Console.class, "console")
				.name("console").parser(new Parser<Console>() {
					@Override
					@Nullable
					public Console parse(String s, ParseContext context) {
						return null;
					}
					@Override
					public String toString(Console c, int flags) {
						return null;
					}
					@Override
					public String toVariableNameString(Console c) {
						return null;
					}
					@Override
					public String getVariableNamePattern() {
						return ".+";
					}
				}));
		Skript.registerEffect(EffSetPixel.class, "set pixel at [x][ ]%number%[,] [y][ ]%number% to [color] %number% on [console] %console%");
		Skript.registerEffect(EffUpdateConsole.class, "update [console] %console%");
		Skript.registerEffect(EffRemoveConsole.class, "remove [console] %console%");
		Skript.registerEffect(EffSetBackConsole.class, "set background of %console% to [color] %number%");
		Skript.registerEffect(EffImageConsole.class, "render image from (0各RL|1刎ILE) %string% on [to] %console%", "render image from (0各RL|1刎ILE) %string% on [to] %console% starting at %number% %number%");
		Skript.registerEffect(EffGIFConsole.class, "render gif [image] from (0各RL|1刎ILE) %string% on [to] %console%", "render gif [image] from (0各RL|1刎ILE) %string% on [to] %console% starting at %number%[,] %number%");
		Skript.registerEffect(EffDrawText.class, "(render|draw) text %string% on [console] %console% with [color] %number%", "(render|draw) text %string% on [console] %console% with [color] %number% starting at %number% %number%");
		Skript.registerEffect(EffDrawLine.class, "(render|draw) line from [x][ ]%number%[,] [y][ ]%number% to [x][ ]%number%[,] [y][ ]%number% with color %number% on [console] %console%");
	}
}

