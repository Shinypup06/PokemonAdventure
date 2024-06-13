import java.awt.Graphics2D;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    
    //the local X and Y coordinates (where the player is on the screen)
    public final int screenX;
    public final int screenY;
    
    //The bag that stores all pokemon caught
    public static ArrayList<Pokemon> pokemonBag = new ArrayList<Pokemon>();
    public Pokemon starter;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;
        
        solidArea = new Rectangle(16, 10, 16, 30);
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void getPlayerImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/images/player_up.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/images/player_left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/images/player_right.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/images/player_down.png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void setDefaultValues(){
        worldX = gp.tileSize * 8;
        worldY = gp.tileSize * 6;
        speed = 4;
        direction = "down";
        
        //TODO: choose starter
        starter = new Pokemon("charmander", "Fire");
        pokemonBag.add(starter);
    }
    
    public void update(){
        //updates player's location based on keypresses
        if(keyH.upPressed){
            direction = "up";
        }
        else if(keyH.downPressed){
            direction = "down";
        }
        if(keyH.leftPressed){
            direction = "left";
        }
        else if(keyH.rightPressed){
            direction = "right";
        }
        
        collisionOn = false;
        
        
        gp.cChecker.checkTile(this);
        
        if(gp.cChecker.checkTallGrass(this)){
          inTallGrass();  
        }
        
        //if collision (with wall) is false, player can move
        //if collision (with wall) is true, player cannot move
        
        if(!collisionOn){
            if(keyH.upPressed){
                worldY -= speed;
            }
            else if(keyH.downPressed){
                worldY += speed;
            }
            if(keyH.leftPressed){
                worldX -= speed;
            }
            else if(keyH.rightPressed){
                worldX += speed;
            }
        }
    }
    
    public void inTallGrass(){
        int randNum = (int)(Math.random() * (TallGrass.tallGrassSpawnChance + 1));
        if(randNum == 0){
            gp.ui.numEncounters++;
            gp.gameState = gp.encounterState;
        }
    }
    
    public void draw(Graphics2D g2){
        //draws the player
        
        BufferedImage image = null;
        switch(direction){
            case "up":
                image = up;
                break;
            case "left":
                image = left;
                break;
            case "right":
                image = right;
                break;
            default:
                image = down;
                break;
        }
        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
    
}