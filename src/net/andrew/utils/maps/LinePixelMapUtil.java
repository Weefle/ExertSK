package net.andrew.utils.maps;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import net.andrew.utils.maps.ColorMapUtil;
 
public class LinePixelMapUtil extends MapRenderer {
    private boolean rendered;
	private Integer x;
	private Integer y;
	private Integer x2;
	private Integer y2;
	private String color;
 
    public LinePixelMapUtil(String color, Integer x1, Integer y1, Integer x2, Integer y2) {
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
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                //http://tech-algorithm.com/articles/drawing-line-using-bresenham-algorithm/
                int w = x2 - x ;
                int h = y2 - y ;
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
                    mc.setPixel(x, y, ColorMapUtil.minecraftColorFromString(color));
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
            }
        });  
        t1.start();
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