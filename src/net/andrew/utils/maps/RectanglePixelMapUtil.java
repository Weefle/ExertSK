package net.andrew.utils.maps;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import net.andrew.utils.maps.ColorMapUtil;
 
public class RectanglePixelMapUtil extends MapRenderer {
    private boolean rendered;
	private Integer x;
	private Integer y;
	private Integer x2;
	private Integer y2;
	private String color;
	private Integer xsmall;
	private Integer xbig;
	private Integer ysmall;
	private Integer ybig;
 
    public RectanglePixelMapUtil(String color, Integer x1, Integer y1, Integer x2, Integer y2) {
        this.color = color;
        this.x = x1;
        this.y = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
 
    public void render(MapView mv, MapCanvas mc, Player p) {
        if (rendered || color == null) {
            return;
        }
        if (x < x2){
        	this.xsmall = x;
        	this.xbig = x2;
        }
        else{
        	this.xsmall = x2;
        	this.xbig = x;
        }
        //Y:
        if (y < y2){
        	this.ysmall = y;
        	this.ybig = y2;
        }
        else{
        	this.ysmall = y2;
        	this.ybig = y;
        }
		for (int i = xsmall; i < xbig; i++) {
        	for (int i2 = ysmall; i2 < ybig; i2++) {
        		mc.setPixel(i, i2, ColorMapUtil.minecraftColorFromString(color));
            }
        }
        rendered = true;
    }
 
    public void setColor(String color) {
        this.color = color;
        setRendered(false);
    }
    public String getImage() {
        return this.color;
    }
 
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
 
    public boolean hasRendered() {
        return this.rendered;
    }
}