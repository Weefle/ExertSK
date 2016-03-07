package net.andrew.utils;

import org.bukkit.map.MapPalette;

public class ColorMapUtil {
	@SuppressWarnings("deprecation")
	public static byte minecraftColorFromString(String s) {
		if(s.equalsIgnoreCase("transparent"))
			return MapPalette.TRANSPARENT;
		else if(s.equalsIgnoreCase("lightGreen") || s.equalsIgnoreCase("light_green"))
			return MapPalette.LIGHT_GREEN;
		else if(s.equalsIgnoreCase("darkGreen") || s.equalsIgnoreCase("dark_green"))
			return MapPalette.DARK_GREEN;
		else if(s.equalsIgnoreCase("lightBrown") || s.equalsIgnoreCase("light_brown"))
			return MapPalette.LIGHT_BROWN;
		else if(s.equalsIgnoreCase("brown"))
			return MapPalette.BROWN;
		else if(s.equalsIgnoreCase("darkBrown") || s.equalsIgnoreCase("dark_brown"))
			return MapPalette.DARK_BROWN;
		else if(s.equalsIgnoreCase("lightGray") || s.equalsIgnoreCase("light_gray"))
			return MapPalette.LIGHT_GRAY;
		else if(s.equalsIgnoreCase("gray1") || s.equalsIgnoreCase("gray_1"))
			return MapPalette.GRAY_1;
		else if(s.equalsIgnoreCase("gray2") || s.equalsIgnoreCase("gray_2"))
			return MapPalette.GRAY_2;
		else if(s.equalsIgnoreCase("darkGray") || s.equalsIgnoreCase("dark_gray"))
			return MapPalette.DARK_GRAY;
		else if(s.equalsIgnoreCase("red"))
			return MapPalette.RED;
		else if(s.equalsIgnoreCase("paleBlue") || s.equalsIgnoreCase("pale_blue"))
			return MapPalette.PALE_BLUE;
		else if(s.equalsIgnoreCase("blue"))
			return MapPalette.BLUE;
		else if(s.equalsIgnoreCase("white"))
			return MapPalette.WHITE;
		return 0;
	}
	@SuppressWarnings("deprecation")
	public static String StringFromMinecraftColor(Byte s) {
		if (s.equals(MapPalette.TRANSPARENT))
			return "TRANSPARENT";
		else if (s.equals(MapPalette.LIGHT_GREEN))
			return "light_green";
		else if (s.equals(MapPalette.DARK_GREEN))
			return "dark_green";
		else if (s.equals(MapPalette.LIGHT_BROWN))
			return "light_brown";
		else if (s.equals(MapPalette.BROWN))
			return "brown";
		else if (s.equals(MapPalette.DARK_BROWN))
			return "dark_brown";
		else if (s.equals(MapPalette.LIGHT_GRAY))
			return "light_gray";
		else if (s.equals(MapPalette.GRAY_1))
			return "gray_1";
		else if (s.equals(MapPalette.GRAY_2))
			return "gray_2";
		else if (s.equals(MapPalette.DARK_GRAY))
			return "dark_gray";
		else if (s.equals(MapPalette.RED))
			return "red";
		else if (s.equals(MapPalette.PALE_BLUE))
			return "pale_blue";
		else if (s.equals(MapPalette.WHITE))
			return "white";
		return null;
		
	}
}
