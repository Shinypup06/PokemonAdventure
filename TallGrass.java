import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class TallGrass{
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    
    
    public static int tallGrassSpawnChance = 30;
        //1 in 30 chance that a pokemon will spawn in tall grass
    
    
    public TallGrass(){
        name = "tallGrass";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/images/tallGrass.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2, GamePanel gp){
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //only draw the tiles that are near the player
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
            && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
            && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
    }
}