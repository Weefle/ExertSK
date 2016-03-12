package net.andrew.effects.random;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser.ParseResult;
import ch.njol.util.Kleenean;
import net.andrew.Main;
import net.andrew.utils.imgmessage.ImageChar;
import net.andrew.utils.imgmessage.ImageMessage;

import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;

import javax.annotation.Nullable;
import javax.imageio.ImageIO;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
public class EffChatImageFromURL extends Effect{
	private Expression<String> url;
	private Expression<Player> player;
	private Expression<String> extratexts;
	private static Expression<Integer> height;
	private static Expression<Integer> shade;
	private static char character;
	private int label;
	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int arg1, Kleenean arg2, ParseResult arg3) {
		player = (Expression<Player>) expr[0];
		url = (Expression<String>) expr[1];
		height = (Expression<Integer>) expr[2];
		shade = (Expression<Integer>) expr[3];
		if (arg1 == 1){
			extratexts = (Expression<String>) expr[4];
		}
		label = arg1;
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "send %players% [chat] image from url %string% with height %integer%[,] shade %integer% [with extra texts %strings%]";
	}

	@Override
	protected void execute(Event e) {
		// TODO Auto-generated method stub
		
		Thread t1 = new Thread(new Runnable() {
            public void run() {
            	try {
            		Integer shade = EffChatImageFromURL.shade.getSingle(e);
            		if (shade == 0){
            			EffChatImageFromURL.character = ImageChar.BLOCK.getChar();
            		}
            		else if (shade == 1){
            			EffChatImageFromURL.character = ImageChar.LIGHT_SHADE.getChar();
            		}
            		else if (shade == 2){
            			EffChatImageFromURL.character = ImageChar.MEDIUM_SHADE.getChar();
            		}
            		else if (shade == 3){
            			EffChatImageFromURL.character = ImageChar.DARK_SHADE.getChar();
            		}
            		else{
            			EffChatImageFromURL.character = ImageChar.BLOCK.getChar();
            		}
        			Player[] players = player.getArray(e);
        			BufferedImage image = ImageIO.read(new URL(url.getSingle(e)));
        			if (label == 1){
        				String[] texts = extratexts.getArray(e);
        				ImageMessage im = new ImageMessage(
        						image, // the buffered image to send
        						EffChatImageFromURL.height.getSingle(e), // the image height
        						EffChatImageFromURL.character // the character that the image is made of
        						).appendText(
        						texts);
        				for (Player p : players){
        					im.sendToPlayer(p);
        				}
        			}
        			else{
        				ImageMessage im = new ImageMessage(
        						image, // the buffered image to send
        						EffChatImageFromURL.height.getSingle(e), // the image height
        						EffChatImageFromURL.character // the character that the image is made of
        						);
        				for (Player p : players){
        					im.sendToPlayer(p);
        				}
        			}
        		}
        		catch (Exception e1){
        			Main.inst().getLogger().warning("Could not retreive image from URL " + url.getSingle(e) + " because " + e1.getMessage());
        			StringWriter sw = new StringWriter();
        			PrintWriter pw = new PrintWriter(sw);
        			e1.printStackTrace(pw);
        			Main.inst().getLogger().warning(sw.toString()); // stack trace as a string
        		}
            }
       }); 
       t1.start();
	}

}
