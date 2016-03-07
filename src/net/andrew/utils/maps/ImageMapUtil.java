package net.andrew.utils.maps;
import java.awt.image.BufferedImage;
 
import org.bukkit.entity.Player;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
 
public class ImageMapUtil extends MapRenderer {
    private BufferedImage image;
    private boolean rendered;
	private Integer x1;
	private Integer y1;
 
    public ImageMapUtil(BufferedImage image, Integer x1, Integer y1) {
        this.image = image;
        this.x1 = x1;
        this.y1 = y1;
    }
 
    public void render(MapView mv, MapCanvas mc, Player p) {
        if (rendered || image == null) {
            return;
        }
        mc.drawImage(x1, y1, image);
        rendered = true;
    }
 
    public void setImage(BufferedImage image) {
        this.image = image;
        setRendered(false);
    }
    public BufferedImage getImage() {
        return this.image;
    }
 
    public void setRendered(boolean rendered) {
        this.rendered = rendered;
    }
 
    public boolean hasRendered() {
        return this.rendered;
    }
}