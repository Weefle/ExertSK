package net.andrew.expressions.consoles;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Event;

import ca.jarcode.consoles.api.Console;

import javax.annotation.Nullable;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

public class ExprNewConsole extends SimpleExpression<Console>{
	private int bf;
	private Expression<Location> loc;
	private Expression<Number> width;
	private Expression<Number> height;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int label, Kleenean arg2, ParseResult arg3) {
		bf = arg3.mark;
		loc = (Expression<Location>) expr[0];
		width = (Expression<Number>) expr[1];
		height = (Expression<Number>) expr[2];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "[a] new console facing (0¦north|1¦east|2¦west|3¦south) at %location% with [width] %number%[ and|,] [height] %number%";
	}

	@Override
	public Class<? extends Console> getReturnType() {
		return Console.class;
	}

	@Override
	public boolean isSingle() {
		return true;
	}

	@Override
	@Nullable
	protected Console[] get(Event e) {
		BlockFace bf1;
		switch (bf){
		case 0: bf1 = BlockFace.NORTH;
			break;
		case 1: bf1 = BlockFace.EAST;
			break;
		case 2: bf1 = BlockFace.WEST;
			break;
		case 3: bf1 = BlockFace.SOUTH;
			break;
		default:	
			bf1 = BlockFace.NORTH;
			break;
		}
		Number width1 = width.getSingle(e);
		Number height1 = height.getSingle(e);
		width1 = Math.round(width1.doubleValue());
		height1 = Math.round(height1.doubleValue());
		Integer width2 = width1.intValue(); 
		Integer height2 = height1.intValue(); 
		Console console = new Console(bf1, loc.getSingle(e), width2, height2);
		console.create();
		return new Console[]{console};
	}
}
