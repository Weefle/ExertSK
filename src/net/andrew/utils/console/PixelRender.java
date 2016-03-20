package net.andrew.utils.console;

import org.bukkit.entity.Player;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.CanvasGraphics;
import ca.jarcode.consoles.api.CustomComponent;

public class PixelRender extends CustomComponent{

	private int x;
	private int y;
	private byte color;
	public PixelRender(int width, int height, int x, int y, Byte color) {
		super(width, height);
		this.x = x;
		this.y = y;
		this.color = color;
	}

	@Override
	public byte background() {
		return 0;
	}

	@Override
	public boolean enabled() {
		return true;
	}

	@Override
	public void handleClick(int arg0, int arg1, Player arg2) {	
	}

	@Override
	public void onCreate(CanvasComponent arg0) {
	}

	@Override
	public void paint(CanvasGraphics arg0, String arg1) {
		arg0.draw(x, y, color);
	}

	@Override
	public void setEnabled(boolean arg0) {
		// TODO Auto-generated method stub
	}

}
