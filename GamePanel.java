import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    
    //SCREEN SETTINGS
    final int originalTileSize = 16; //16x16 tile for uploaded character art, etc.
    final int scale = 3; //sizing up due to screen resolution
    
    public final int tileSize = originalTileSize * scale; //48x48 tiles
    public final int maxScreenCol = 18; //18 columns
    public final int maxScreenRow = 14; //14 rows
    
    //screen aspect ratio is 4:3
    final int screenWidth = tileSize * maxScreenCol; //768px
    final int screenHeight = tileSize * maxScreenRow; //576px
    
    //World settings
    public final int maxWorldCol = 18; //map size
    public final int maxWorldRow = 14;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    
    
    //FPS
    int FPS = 30;
    
    //TileManager sets tiles in their locations based on a txt map file
    TileManager tileM = new TileManager(this);
    
    //KeyHandler handles all keyboard input with a KeyListener class
    KeyHandler keyH = new KeyHandler(this);
    
    //player represents the player character, movement & related variables
    public Player player = new Player(this, keyH);
    
    //checks for collisions between solid tiles and the player
    public CollisionChecker cChecker = new CollisionChecker(this, keyH);
    
    //The blueprint for the Tall Grass
    public TallGrass[] tallGrass = new TallGrass[300]; 
    
    //AssetSetter sets all of the tall grass onto 
    public AssetSetter aSetter = new AssetSetter(this);
    
    
    //Each window has its own gameState
    public int gameState;
    public final int playState = 1;
    public final int pauseState =2;
    public final int encounterState = 3;
    public final int battleState = 4;
    public final int switchState = 5;
    public final int starterState = 6;
    public final int pokemonCenterState = 7;
    
    //UI Draws all the windows and pop-ups, text and images
    public UI ui = new UI(this);
    
    //gameThread keeps time
    Thread gameThread;
    
    //Draws the interactible screen window
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); //focuses the gameHandler to listen for key input
    }
    
    //sets up all of the tall grass and the "choose your starter" screen
    public void setupGame(){
        aSetter.setObject();
        gameState = starterState;
    }
    
    //starts the game loop using Thread class
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    //run method runs continuously during gameThread
    @Override
    public void run(){
        double drawInterval = 1000000000/FPS; //1 second/FPS
        //start frame clock
        double nextDrawTime = System.nanoTime() + drawInterval;
        
        while(gameThread != null){
            
            //1. Update info usch as character positions
            update();
            
            //2. draw the screen with the updated information
            repaint(); //calls paintComponent method
            
            //calculate the interval between each update
            //so it doesn't run infinitely fast and stays at 60FPS
            try{
                double remainingTime = nextDrawTime - System.nanoTime(); //returns how much time remaining until the next draw time
                remainingTime /= 1000000;
                
                if (remainingTime < 0){
                    remainingTime = 0;
                }
                
                Thread.sleep((long)remainingTime);
                
                nextDrawTime += drawInterval;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
    public void update(){
        //only update player location if state is playstate
        if(gameState == playState){
            player.update();
        } 
    }
    
    public void paintComponent(Graphics g){
        
        super.paintComponent(g);
        //paintComponent is a built in method for JPanel
        
        Graphics2D g2 = (Graphics2D)g; //convert to graphics2d because it has more functionality
        tileM.draw(g2); //tiles (draw tiles before player)
        
        for(int i = 0; i < tallGrass.length; i++){
            if(tallGrass[i] != null){
                tallGrass[i].draw(g2, this);
            }
        }
        
        player.draw(g2);  //player character (draw player after tiles)
        
        ui.draw(g2); //popups, etc. (draw popups after player)
        
        g2.dispose(); //so that it does not take up memory
    }
    
    
}