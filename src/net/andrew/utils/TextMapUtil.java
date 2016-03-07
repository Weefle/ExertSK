package net.andrew.utils;
 
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.map.MinecraftFont;
 
public class TextMapUtil extends MapRenderer {
    private String text;
    private boolean rendered;
	private Integer x1;
	private Integer y1;
 
    public TextMapUtil(String text, Integer x1, Integer y1) {
        this.text = text;
        this.x1 = x1;
        this.y1 = y1;
    }
 
    public void render(MapView mv, MapCanvas mc, Player p) {
        if (rendered || text == null) {
            return;
        }
        mc.drawText(x1, y1, MinecraftFont.Font, text);
        rendered = true;
    }
 
    public void setText(String text) {
        this.text = text;
        setRendered(false);
    }
    public String getText() {
        return this.text;
    }
 
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
 
    public boolean hasRendered() {
        return this.rendered;
    }
}