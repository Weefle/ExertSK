package net.andrew.utils.maps;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import net.andrew.utils.maps.ColorMapUtil;
 
public class BorderRectanglePixelMapUtil extends MapRenderer {
    private boolean rendered;
	private Integer x;
	private Integer y;
	private Integer x2;
	private Integer y2;
	private String color;
 
    public BorderRectanglePixelMapUtil(String color, Integer x1, Integer y1, Integer x2, Integer y2) {
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
        //(x1,y1),(x1,y2)(x2,y1)and(x2,y2)
        
        //THIS ONE: x1,y1 > x2,y1
        List<Integer> line1x = linex(x, y, x2, y);
        List<Integer> line1y = liney(x, y, x2, y);
        for (int i = 0; i < line1x.size(); i++) {
        	mc.setPixel(line1x.indexOf(i), line1y.indexOf(i), ColorMapUtil.minecraftColorFromString(color));
        }
        //x1,y1 > x1,y2
        List<Integer> line2x = linex(x, y, x, y2);
        List<Integer> line2y = liney(x, y, x, y2);
        for (int i = 0; i < line2x.size(); i++) {
        	mc.setPixel(line2x.indexOf(i), line2y.indexOf(i), ColorMapUtil.minecraftColorFromString(color));
        }
        //x1,y2 > x2,y2
        List<Integer> line3x = linex(x, y2, x2, y2);
        List<Integer> line3y = liney(x, y2, x2, y2);
        for (int i = 0; i < line3x.size(); i++) {
        	mc.setPixel(line3x.indexOf(i), line3y.indexOf(i), ColorMapUtil.minecraftColorFromString(color));
        }
      //x2,y1 > x2,y2
        List<Integer> line4x = linex(x2, y, x2, y2);
        List<Integer> line4y = liney(x2, y, x2, y2);
        for (int i = 0; i < line4x.size(); i++) {
        	mc.setPixel(line4x.indexOf(i), line4y.indexOf(i), ColorMapUtil.minecraftColorFromString(color));
        }
        rendered = true;
    }
    public List<Integer> linex(Integer x, Integer y, Integer x22, Integer y22){
    	//RETURNS array xpoints ypoints
    	List<Integer> xpoints = new ArrayList<Integer>();
    	int w = x22 - x ;
        int h = y22 - y ;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        int longest = Math.abs(w) ;
        int shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;            
        }
        int numerator = longest >> 1 ;
        for (int i=0;i<=longest;i++) {
        	//X Y :
            xpoints.add(x);
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }
        return xpoints;
    }
    public List<Integer> liney(Integer x, Integer y, Integer x22, Integer y22){
    	//RETURNS array xpoints ypoints
    	List<Integer> ypoints = new ArrayList<Integer>();
    	int w = x22 - x ;
        int h = y22 - y ;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0 ;
        if (w<0) dx1 = -1 ; else if (w>0) dx1 = 1 ;
        if (h<0) dy1 = -1 ; else if (h>0) dy1 = 1 ;
        if (w<0) dx2 = -1 ; else if (w>0) dx2 = 1 ;
        int longest = Math.abs(w) ;
        int shortest = Math.abs(h) ;
        if (!(longest>shortest)) {
            longest = Math.abs(h) ;
            shortest = Math.abs(w) ;
            if (h<0) dy2 = -1 ; else if (h>0) dy2 = 1 ;
            dx2 = 0 ;            
        }
        int numerator = longest >> 1 ;
        for (int i=0;i<=longest;i++) {
        	//X Y :
            ypoints.add(x);
            numerator += shortest ;
            if (!(numerator<longest)) {
                numerator -= longest ;
                x += dx1 ;
                y += dy1 ;
            } else {
                x += dx2 ;
                y += dy2 ;
            }
        }
        return ypoints;
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