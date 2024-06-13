import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        //creating instance of JFrame
        JFrame window= new JFrame();
        window.setTitle("Pokemon Adventure");
        window.setResizable(false);
        
        //create an instance of GamePanel, where the game itself will run
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        //make sure game variables are set before starting the game loop
        gamePanel.setupGame();
        gamePanel.startGameThread();
        
    }
}