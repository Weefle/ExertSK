package net.andrew.utils.random;

public class LastServerCommandOutput {
	private static String output;
	
	public static String getLastError(){
		return LastServerCommandOutput.output;
	}
	public static void setLastError(String output){
		LastServerCommandOutput.output = output;
	}
}
