import javax.imageio.*;
import java.io.*;
import java.awt.*;

public class TileManager{
    GamePanel gp;
    Tile[] tile;
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        
        getTileImage();
        loadMap("/maps/map1.txt");
        
    }
    
    public void loadMap(String filePath){
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow){
                String line = br.readLine();
                while(col < gp.maxWorldCol){
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    
                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == gp.maxWorldCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
            
            
        }
        catch(Exception e){
            
        }
    }
    
    
    public void getTileImage(){
        try{
            //assign types of tiles to images
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/images/grass.png"));
        
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/images/grass.png"));
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/images/path.png"));
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/images/pokemonCenter.png"));
            tile[3].collision = true;
            
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/images/pokemonCenter2.png"));
            tile[4].collision = true;
            
            tile[5] = new Tile();
            //TODO: replace with map border image
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/images/pokemonCenter2.png"));
            tile[5].collision = true;
            
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2){
        int worldCol = 0;
        int worldRow = 0;

        
        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            int tileNum = mapTileNum[worldCol][worldRow];
            
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            
            //only draw the tiles that are near the player
            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX 
            && worldX - gp.tileSize < gp.player.worldX + gp.player.screenX 
            && worldY + gp.tileSize > gp.player.worldY - gp.player.screenY 
            && worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            
            worldCol++;
            
            if(worldCol == gp.maxScreenCol){
                worldCol = 0;
                worldRow++;
            }
        }
        
    }
    
    
}