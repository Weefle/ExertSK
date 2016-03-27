package net.andrew.minigames;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;

public class MinigameOption {
	public static String[] options = new String[]{
		"hunger",
		"health",
		"day",
		"night"
	};
	public static String[] optionsyntaxes = new String[]{
		"$ [minigame] [deplete] hunger %boolean%",
		"$ [minigame] [deplete] health %boolean%",
		"$ [minigame] [deplete] [always] day %boolean%",
		"$ [minigame] [deplete] [always] night %boolean%"
	};
	public static Object[] values = new Object[options.length];
	
	public static String[] getTypes(){
		return options;
	}
	public static String[] getSyntaxes(){
		return optionsyntaxes;
	}
	public static String intToOption(int i){
		if (i <= getTypes().length){
			return getTypes()[i];
		}
		return null;
	}
	
	public static Integer optionToInt(String s){
		Integer index = 0;
		for (String s2 : getTypes()){
			if (s == s2){
				return index;
			}
			index++;
		}
		return null;
	}
	
	public static Object getOptionValue(Integer i){
		return values[i];
	}
	
	public static Object getOptionValue(String s){
		Integer i = optionToInt(s);
		return getOptionValue(i);
	}
	
	public static void setOptionValue(Integer i, Object v){
		setOptionValue(intToOption(i), v);
	}
	
	public static void setOptionValue(String s, Object v){
		values[optionToInt(s)] = v;
		switch(s){
			case("day"):
				if ((Boolean) v){
					List<World> worlds = Bukkit.getWorlds();
					for (World w : worlds){
						w.setTime(5000);
						w.setGameRuleValue("doDaylightCycle", "false");
					}
				}
				break;
			case("night"):
				if ((Boolean) v){
					List<World> worlds = Bukkit.getWorlds();
					for (World w : worlds){
						w.setTime(15000);
						w.setGameRuleValue("doDaylightCycle", "false");
					}
				}
				break;
		}
	}
}
