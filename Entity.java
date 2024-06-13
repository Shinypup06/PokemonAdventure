import java.awt.image.BufferedImage;
import java.awt.Rectangle;

public class Entity{
    
    public int worldX,worldY;
    public int speed;
    
    //Entity class applies for npcs as well (to be added in a future update)
    public BufferedImage up, down, left, right;
    public String direction;
    
    public Rectangle solidArea;
    public int solidAreaDefaultX = 16;
    public int solidAreaDefaultY = 10;
    public boolean collisionOn = false;
}