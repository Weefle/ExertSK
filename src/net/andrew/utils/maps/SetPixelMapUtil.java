package net.andrew.utils.maps;
 
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
 
@SuppressWarnings("unused")
public class SetPixelMapUtil extends MapRenderer {
    private boolean rendered;
	private Integer x1;
	private Integer y1;
	private String color;
 
    public SetPixelMapUtil(String color, Integer x1, Integer y1) {
        this.color = color;
        this.x1 = x1;
        this.y1 = y1;
    }
 
    public void render(MapView mv, MapCanvas mc, Player p) {

    	if (rendered || color == null) {
            return;
        }
        new Thread() {
            public void run() {
            	mc.setPixel(x1, y1, ColorMapUtil.minecraftColorFromString(color));
            }
        }.start();        
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