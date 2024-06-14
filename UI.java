import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import javax.imageio.*;
import java.io.*;
import java.awt.image.BufferedImage;

public class UI{
    GamePanel gp;
    Font messageFont;
    Graphics2D g2;
    
    public String message1 = "";
    public String message2 = "";
    
    BufferedImage wildImage;
    BufferedImage currentImage;
    Pokemon wildPokemon;
    Pokemon currentPokemon;
    
    public int numEncounters = 0;
    public int prevNumEncounters = 0;
    
    public UI(GamePanel gp){
        this.gp = gp;
        messageFont = new Font("Courier New", Font.PLAIN, 20);
    }
    
    public void draw(Graphics2D g2){
        this.g2 = g2;
        
        if(gp.gameState == gp.playState){
            //do playState stuff later
            
        }
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }
        if(gp.gameState == gp.encounterState){
            drawEncounterScreen();
        }
        if(gp.gameState == gp.battleState){
            drawBattleScreen();
        }
        if(gp.gameState == gp.pokemonCenterState){
            drawPokemonCenterScreen();
        }
        if(gp.gameState == gp.switchState){
            drawSwitchScreen();
        }
        if(gp.gameState == gp.starterState){
            drawStarterScreen();
        }
        if(gp.gameState == gp.transferState){
            drawTransferScreen();
        }

    }
    
    public void drawPauseScreen(){
        String text = "PAUSED";
        g2.setFont(new Font("Courier New", Font.BOLD, 60));
    
        int x = getXForCenteredText(text);
        int y = gp.screenHeight/2;
       
        g2.drawString(text, x, y);
    }
    
    public void drawEncounterScreen(){
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 3;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);
        
        if(numEncounters > prevNumEncounters){
            wildPokemon = new Pokemon();
            currentPokemon = Player.pokemonBag.get(0);
            prevNumEncounters++;
        }
        
        //"A wild pokemon has appeared!"
        g2.setFont(messageFont);
        g2.drawString("A wild " + wildPokemon.getName() + " has appeared!", gp.tileSize * 3, gp.tileSize * 4);
        
        //load the image of the wild pokemon
        try{
            wildImage = ImageIO.read(getClass().getResourceAsStream("/images/" + wildPokemon.getName() + ".png"));
            currentImage = ImageIO.read(getClass().getResourceAsStream("/images/" + currentPokemon.getName() + ".png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        //draw the wild pokemon
        g2.drawImage(wildImage, gp.tileSize * 6, gp.tileSize * 2, gp.tileSize * 6, gp.tileSize * 6, null);
        
        g2.setFont(messageFont);
        g2.setColor(Color.white);
        
        //ui
        String text = "What do you do?";
         x = getXForCenteredText(text);
        y = gp.tileSize * 9;
        g2.drawString(text, x, y);
        
        text = "T) switch";
        x = gp.tileSize * 4;
        y = gp.tileSize * 10;
        g2.drawString(text, x, y);
        
        text = "F) fight/catch";
        x = (int)(gp.tileSize * 7.5);
        g2.drawString(text, x, y);
        
        text = "R) run";
        x = gp.tileSize * 12;
        g2.drawString(text, x, y);
        
        message1 = "";
        message2 = "";
    }
    
    public void drawBattleScreen(){
        //window
        int x = gp.tileSize * 2;
        int y = (int)(gp.tileSize * 2.5);
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 9;
        drawSubWindow(x, y, width, height);
        
        //"A wild pokemon has appeared!"
        g2.setFont(messageFont);
        g2.drawString("Pick a move!", gp.tileSize * 3, (int)(gp.tileSize * 3.5));
        
        //load the image of the wild pokemon
        try{
            wildImage = ImageIO.read(getClass().getResourceAsStream("/images/" + wildPokemon.getName() + ".png"));
            currentImage = ImageIO.read(getClass().getResourceAsStream("/images/" + currentPokemon.getName() + ".png"));
        } catch(IOException e){
            e.printStackTrace();
        }
        
        //draw the wild pokemon and your current pokemon
        g2.drawImage(wildImage, gp.tileSize * 9, gp.tileSize * 2, gp.tileSize * 6, gp.tileSize * 6, null);
        g2.drawImage(currentImage, gp.tileSize * 9, gp.tileSize * 2, -gp.tileSize * 6, gp.tileSize * 6, null);
        
        g2.setFont(messageFont);
        g2.setColor(Color.white);
        
        
        //ui
        String text;
        
        text = "1) " + currentPokemon.moves[0].getName();
        x = gp.tileSize * 4;
        y = (int)(gp.tileSize * 8.5);
        g2.drawString(text, x, y);
        
        text = "2) " + currentPokemon.moves[1].getName();
        x = (int)(gp.tileSize * 8);
        g2.drawString(text, x, y);
        
        text = "3) " + currentPokemon.moves[2].getName();
        x = gp.tileSize * 4;
        y = (int)(gp.tileSize * 9.5);
        g2.drawString(text, x, y);
        
        text = "4) " + currentPokemon.moves[3].getName();
        x = (int)(gp.tileSize * 8);
        g2.drawString(text, x, y);
        
        text = "C) catch";
        x = (int)(gp.tileSize * 12.5);
        y = (int)(gp.tileSize * 8.5);
        g2.drawString(text, x, y);
        
        text = "T) switch";
        x = (int)(gp.tileSize * 12.5);
        y = (int)(gp.tileSize * 9.5);
        g2.drawString(text, x, y);
        
        text = "" + currentPokemon.getHP() + "/" + currentPokemon.getMaxHP();
        x = gp.tileSize * 4;
        y = (int)(gp.tileSize * 4.5);
        g2.drawString(text, x, y);
        
        text = "" + wildPokemon.getHP() + "/" + wildPokemon.getMaxHP();
        x = gp.tileSize * 11;
        g2.drawString(text, x, y);
        
        text = "lv. " + currentPokemon.getLevel();
        x = gp.tileSize * 4;
        y = (int)(gp.tileSize * 5);
        g2.drawString(text, x, y);
        
        text = "lv. " + wildPokemon.getLevel();
        x = gp.tileSize * 11;
        g2.drawString(text, x, y);
        
        g2.setFont(new Font("Courier New", Font.BOLD, 14));
        
        text = message1;
        x = getXForCenteredText(text);
        y = (int)(gp.tileSize * 10.5);
        g2.drawString(text, x, y);
        
        text = message2;
        x = getXForCenteredText(text);
        y = (int)(gp.tileSize * 11);
        g2.drawString(text, x, y);
    }
    
    public void updateMessages(String text1, String text2){
        message1 = text1;
        message2 = text2;
    }
    
    public void drawPokemonCenterScreen(){
    
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 3;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);
        
        String text; 
        
        //"Welcome to the Pokemon center!"
        g2.setFont(messageFont);
        g2.drawString("Welcome to the Pokemon Center!", gp.tileSize * 3, gp.tileSize * 4);
        
        g2.setFont(new Font("Courier New", Font.BOLD, 12));
        
        //load the image of the first 6 pokemon in your box
        try{
            if(Player.pokemonBag.size() < 6){
                for(int i = 0; i < Player.pokemonBag.size(); i++){
                        g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/" + Player.pokemonBag.get(i).getName() + ".png")),
                        (int)(gp.tileSize * (2.4 + 2.1 * i)), (int)(gp.tileSize * 3.5), gp.tileSize * 3, gp.tileSize * 3, null);
                        
                        text = Player.pokemonBag.get(i).getName();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 6.8);
                        g2.drawString(text, x, y);
                        
                        text = "" + Player.pokemonBag.get(i).getHP() + "/" + Player.pokemonBag.get(i).getMaxHP();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.3);
                        g2.drawString(text, x, y);
                        
                        text = "lv. " + Player.pokemonBag.get(i).getLevel();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.8);
                        g2.drawString(text, x, y);
                }
            }else{
                    for(int i = 0; i < 6; i++){
                        g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/" + Player.pokemonBag.get(i).getName() + ".png")),
                        (int)(gp.tileSize * (2.4 + 2.1 * i)), (int)(gp.tileSize * 3.5), gp.tileSize * 3, gp.tileSize * 3, null);
                    
                        text = Player.pokemonBag.get(i).getName();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 6.8);
                        g2.drawString(text, x, y);
                        
                        text = "" + Player.pokemonBag.get(i).getHP() + "/" + Player.pokemonBag.get(i).getMaxHP();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.3);
                        g2.drawString(text, x, y);
                        
                        text = "lv. " + Player.pokemonBag.get(i).getLevel();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.8);
                        g2.drawString(text, x, y);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        
        
        g2.setFont(messageFont);
        g2.setColor(Color.white);
        
        
        //ui
        
        text = "H) heal pokemon";
        x = gp.tileSize * 4;
        y = gp.tileSize * 10;
        g2.drawString(text, x, y);
        
        text = "ENTER) exit";
        x = gp.tileSize * 11;
        g2.drawString(text, x, y);

        text = "T) transfer pokemon";
        x = gp.tileSize * 4;
        y = (int)(gp.tileSize * 9.3);
        g2.drawString(text, x, y);
    }
    
    public void drawTransferScreen(){
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 3;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);
        
        String text; 
        
        //"Which Pokemon would you like to transfer?"
        g2.setFont(messageFont);
        g2.drawString("Which pokemon would you like to transfer?", gp.tileSize * 3, gp.tileSize * 4);
        
        g2.setFont(new Font("Courier New", Font.BOLD, 12));
        
        //load the image of the first 6 pokemon in your box
        try{
            if(Player.pokemonBag.size() < 6){
                for(int i = 0; i < Player.pokemonBag.size(); i++){
                        g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/" + Player.pokemonBag.get(i).getName() + ".png")),
                        (int)(gp.tileSize * (2.4 + 2.1 * i)), (int)(gp.tileSize * 3.5), gp.tileSize * 3, gp.tileSize * 3, null);
                        
                        text = Player.pokemonBag.get(i).getName();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 6.8);
                        g2.drawString(text, x, y);
                        
                        text = "" + Player.pokemonBag.get(i).getHP() + "/" + Player.pokemonBag.get(i).getMaxHP();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.3);
                        g2.drawString(text, x, y);
                        
                        text = "lv. " + Player.pokemonBag.get(i).getLevel();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.8);
                        g2.drawString(text, x, y);
                }
            }else{
                    for(int i = 0; i < 6; i++){
                        g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/" + Player.pokemonBag.get(i).getName() + ".png")),
                        (int)(gp.tileSize * (2.4 + 2.1 * i)), (int)(gp.tileSize * 3.5), gp.tileSize * 3, gp.tileSize * 3, null);
                    
                        text = Player.pokemonBag.get(i).getName();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 6.8);
                        g2.drawString(text, x, y);
                        
                        text = "" + Player.pokemonBag.get(i).getHP() + "/" + Player.pokemonBag.get(i).getMaxHP();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.3);
                        g2.drawString(text, x, y);
                        
                        text = "lv. " + Player.pokemonBag.get(i).getLevel();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.8);
                        g2.drawString(text, x, y);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
        
        g2.setFont(messageFont);
        g2.setColor(Color.white);
        
        //ui
        
        text = "B) back to pokemon center";
        x = gp.tileSize * 4;
        y = gp.tileSize * 10;
        g2.drawString(text, x, y);
        
        text = "ENTER) exit";
        x = gp.tileSize * 11;
        g2.drawString(text, x, y);

    }

    public void drawSwitchScreen(){
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 3;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);
        
        String text; 
        
        //"A wild pokemon has appeared!"
        g2.setFont(messageFont);
        g2.drawString("Select a pokemon to switch to!", gp.tileSize * 3, gp.tileSize * 4);
        
        g2.setFont(new Font("Courier New", Font.BOLD, 16));
        
        //load the image of the first 6 pokemon in your box
        try{
            if(Player.pokemonBag.size() < 6){
                for(int i = 0; i < Player.pokemonBag.size(); i++){
                        g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/" + Player.pokemonBag.get(i).getName() + ".png")),
                        (int)(gp.tileSize * (2.4 + 2.1 * i)), gp.tileSize * 4, gp.tileSize * 3, gp.tileSize * 3, null);
                        
                        text = Player.pokemonBag.get(i).getName();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.3);
                        g2.drawString(text, x, y);
                        
                        text = "" + Player.pokemonBag.get(i).getHP() + "/" + Player.pokemonBag.get(i).getMaxHP();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 7.8);
                        g2.drawString(text, x, y);
                        
                        text = "lv. " + Player.pokemonBag.get(i).getLevel();
                        x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                        y = (int)(gp.tileSize * 8.3);
                        g2.drawString(text, x, y);
                }
            }
            else{
                for(int i = 0; i < 6; i++){
                    g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/" + Player.pokemonBag.get(i).getName() + ".png")),
                    (int)(gp.tileSize * (2.4 + 2.1 * i)), gp.tileSize * 4, gp.tileSize * 3, gp.tileSize * 3, null);
                    
                    text = Player.pokemonBag.get(i).getName();
                    x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                    y = (int)(gp.tileSize * 7.3);
                    g2.drawString(text, x, y);
                    
                    text = "" + Player.pokemonBag.get(i).getHP() + "/" + Player.pokemonBag.get(i).getMaxHP();
                    x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                    y = (int)(gp.tileSize * 7.8);
                    g2.drawString(text, x, y);
                    
                    text = "lv. " + Player.pokemonBag.get(i).getLevel();
                    x = (int)(gp.tileSize * (3.2 + 2.2 * i));
                    y = (int)(gp.tileSize * 8.3);
                    g2.drawString(text, x, y);
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void drawStarterScreen(){
        //window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 3;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 8;
        drawSubWindow(x, y, width, height);
       
        //"Choose your starter!"
        g2.setFont(messageFont);
        g2.drawString("Choose your starter!", gp.tileSize * 3, gp.tileSize * 4);
        
        //load the image of the wild pokemon
        try{
            g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/charmander.png")),
            (int)(gp.tileSize * (3 + 4 * 0)), (int)(gp.tileSize * 3.5), gp.tileSize * 4, gp.tileSize * 4, null);
            g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/bulbasaur.png")),
            (int)(gp.tileSize * (3 + 4 * 1)), (int)(gp.tileSize * 3.5), gp.tileSize * 4, gp.tileSize * 4, null);
            g2.drawImage(ImageIO.read(getClass().getResourceAsStream("/images/squirtle.png")),
            (int)(gp.tileSize * (3 + 4 * 2)), (int)(gp.tileSize * 3.5), gp.tileSize * 4, gp.tileSize * 4, null);
        } catch(IOException e){
            e.printStackTrace();
        }

        g2.setFont(messageFont);
        g2.setColor(Color.white);
        
        //ui
        
        String text = "1) Charmander";
        x = (int)(gp.tileSize * 3.5);
        y = (int)(gp.tileSize * 8.5);
        g2.drawString(text, x, y);
        
        text = "2) Bulbasaur";
        x = (int)(gp.tileSize * 8);
        g2.drawString(text, x, y);
        
        text = "3) Squirtle";
        x = gp.tileSize * 12;
        g2.drawString(text, x, y);
    }
    
    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(Color.black);
        g2.fillRoundRect(x, y, width, height, 35, 35);
        
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
    
    public int getXForCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth/2 - length/2;
    }
}