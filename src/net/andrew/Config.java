package net.andrew;

import java.nio.charset.Charset;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
	private static FileConfiguration config;
	public static void start(FileConfiguration config){
		Config.config = config;
		if (config.getBoolean("HashNextRestart") &&
			config.getString("Password") != "" &&
			config.getBoolean("Enable Server Command Effect")){
			String md5 = MD5(config.getString("Password"));
			config.set("PasswordHash", md5);
			config.set("Password", "");
			config.set("HashNextRestart", false);
			Main.inst().saveConfig();
			Bukkit.getLogger().info("Hashed Password!");
		}
	}
	public static FileConfiguration getConfig(){
		return Config.config;
	}
	public static String MD5(String md5) {
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes(Charset.forName("UTF-8")));
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}
}
