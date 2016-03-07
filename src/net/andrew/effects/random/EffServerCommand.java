package net.andrew.effects.random;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.Config;
import net.andrew.Main;
import net.andrew.utils.random.LastServerCommandOutput;
public class EffServerCommand extends Effect{
	private static Expression<String> command;
	private static Expression<String> password;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		EffServerCommand.command = (Expression<String>) expr[0];
		EffServerCommand.password = (Expression<String>) expr[1];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "run command %string% with password %string% on OS";
	}

	@Override
	protected void execute(Event e) {
    	String password = EffServerCommand.password.getSingle(e);
    	String command = EffServerCommand.command.getSingle(e);
    	FileConfiguration config = Config.getConfig();
    	if(!config.getBoolean("Enable Server Command Effect")){
    		return;
    	}
    	String confighash = config.getString("PasswordHash");
    	String passhash = Config.MD5(password);
    	if(passhash == confighash){
    		try {
				Process p = Runtime.getRuntime().exec(command);
				p.waitFor();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(p.getInputStream()));
                String output = "";
                String line;
                while ((line = reader.readLine()) != null) {
                    output += line + "\n";
                }
                LastServerCommandOutput.setLastError(output);
			} catch (IOException | InterruptedException e1) {
				Main.inst().getLogger().warning("Could not run command " + command + " Reason: " + e1.getMessage());
			}
    	}
    	else{
    		Main.inst().getLogger().info("Could not run command " + command + " Reason: " + "Incorrect Password!");
    	}
	}

}
