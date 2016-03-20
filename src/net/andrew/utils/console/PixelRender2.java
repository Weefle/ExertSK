package net.andrew.utils.console;

import org.bukkit.entity.Player;

import ca.jarcode.consoles.api.CanvasComponent;
import ca.jarcode.consoles.api.CanvasGraphics;
import ca.jarcode.consoles.api.CustomComponent;
import ca.jarcode.consoles.api.CustomContainer;
import ca.jarcode.consoles.api.Position2D;

@SuppressWarnings("unused")
public class PixelRender2 extends CustomContainer{

	private Integer x;
	private Integer y;
	private Byte color;

	public PixelRender2(int width, int height, Integer x, Integer y, Byte color) {
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
	public void handleAddedComponent(CanvasComponent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void handleClick(int arg0, int arg1, Player arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Position2D mapComponentPosition(CanvasComponent arg0) {
		return new Position2D(this.x, this.y);
	}

	@Override
	public void onCreate(CanvasComponent arg0) {
		// TODO
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
