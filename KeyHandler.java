import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    
    @Override
    public void keyTyped(KeyEvent e){
        //empty
    }
    
    @Override
    public void keyPressed(KeyEvent e){
        int code = e.getKeyCode(); //returns the keyCode associated with the key in this event
        
        //PLAY STATE
        if(gp.gameState == gp.playState){
            if(code== KeyEvent.VK_W || code == KeyEvent.VK_KP_UP){ //if W or up key is pressed
                upPressed = true;
            }
            if(code== KeyEvent.VK_S || code == KeyEvent.VK_KP_DOWN){ //if S key is pressed
                downPressed = true;
            }
            if(code== KeyEvent.VK_A || code == KeyEvent.VK_KP_LEFT){ //if A key is pressed
                leftPressed = true;
            }
            if(code== KeyEvent.VK_D || code == KeyEvent.VK_KP_RIGHT){ //if D key is pressed
                rightPressed = true;
            }
            
            if(code== KeyEvent.VK_P){ //if P key is pressed (pause)
                gp.gameState = gp.pauseState;
            }
        }
        
        //PAUSE STATE
        if(gp.gameState == gp.pauseState){
            if(code== KeyEvent.VK_P){ //if P key is pressed (pause)
                gp.gameState = gp.playState;
            }
        } 
        
        //ENCOUNTER STATE
        if(gp.gameState == gp.encounterState){
            
            rightPressed = false;
            leftPressed = false;
            upPressed = false;
            downPressed = false;
            
            if(code == KeyEvent.VK_F){
                gp.gameState = gp.battleState;
            }
            if(code == KeyEvent.VK_R){
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_T){
                gp.gameState = gp.switchState;
            }
        }
        
        //BATTLE STATE
        if(gp.gameState == gp.battleState){
            for (Pokemon pokemon : Player.pokemonBag){
                pokemon.updateLevel();
            }
            
            //If pokemon is not dead:
            if(gp.ui.currentPokemon.getHP() != 0){

                //FIRST MOVE
                if(code == KeyEvent.VK_1){
                    int randnum = (int)(Math.random() * 4);
                    gp.ui.currentPokemon.attack(0, gp.ui.wildPokemon);
                    
                    if(gp.ui.wildPokemon.getHP() != 0){
                        gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[0].getName() + "!",
                        gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                    }else{
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[0].getName() + "!",
                        "The wild " + gp.ui.wildPokemon.getName() + " has fainted!");
                    }
                        
                }

                //SECOND MOVE
                if(code == KeyEvent.VK_2){
                    int randnum = (int)(Math.random() * 4);
                    gp.ui.currentPokemon.attack(1, gp.ui.wildPokemon);
                    
                    if(gp.ui.wildPokemon.getHP() != 0){
                        gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[1].getName() + "!",
                        gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                    }else{
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[0].getName() + "!",
                        "The wild " + gp.ui.wildPokemon.getName() + " has fainted!");
                    }
                    
                }

                //THIRD MOVE
                if(code == KeyEvent.VK_3){
                    int randnum = (int)(Math.random() * 4);
                    gp.ui.currentPokemon.attack(2, gp.ui.wildPokemon);
                    
                    if(gp.ui.wildPokemon.getHP() != 0){
                        gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[2].getName() + "!",
                        gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                    }else{
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[0].getName() + "!",
                        "The wild " + gp.ui.wildPokemon.getName() + " has fainted!");
                    }
                        
                }

                //FOURTH MOVE
                if(code == KeyEvent.VK_4){
                    int randnum = (int)(Math.random() * 4);
                    gp.ui.currentPokemon.attack(3, gp.ui.wildPokemon);
                    
                    if(gp.ui.wildPokemon.getHP() != 0){
                        gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[3].getName() + "!",
                        gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                    }else{
                        gp.ui.updateMessages(gp.ui.currentPokemon.getName() + " used " + gp.ui.currentPokemon.moves[0].getName() + "!",
                        "The wild " + gp.ui.wildPokemon.getName() + " has fainted!");
                    }
                }

                //If the wild pokemon is not dead, you can try to catch it
                if(gp.ui.wildPokemon.getHP() != 0){
                    if(code == KeyEvent.VK_C){
                        if(gp.ui.wildPokemon.catchPokemon()){
                            gp.ui.updateMessages(gp.ui.wildPokemon.getName() + " was caught!", "Press R to continue.");
                        }else{
                            int randnum = (int)(Math.random() * 4);
                            gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                            gp.ui.updateMessages("The wild " + gp.ui.wildPokemon.getName() + " broke free!" ,gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                        }
                    }
                }
            }else{
                gp.ui.updateMessages("Your " + gp.ui.currentPokemon.getName() + " has fainted!", "Either run or switch.");
            }
            if(code == KeyEvent.VK_T){
                gp.gameState = gp.switchState;
            }
            if(code == KeyEvent.VK_R){
                gp.gameState = gp.playState;
            }
        }
        
        //PCENTER STATE
        if(gp.gameState == gp.pokemonCenterState){
            rightPressed = false;
            leftPressed = false;
            upPressed = false;
            downPressed = false;

            if(code == KeyEvent.VK_T){
                gp.gameState = gp.transferState;
            }

            if(code== KeyEvent.VK_ENTER){ //if enter key is pressed (pause)
                gp.gameState = gp.playState;
            }

            //If H is pressed, heal every pokemon in the bag
            if(code == KeyEvent.VK_H){
                for(int i = 0; i < Player.pokemonBag.size(); i++){
                    Player.pokemonBag.get(i).heal(10000);
                }
            }


        }
        
        //SWITCH STATE
        if(gp.gameState == gp.switchState){
            if(code == KeyEvent.VK_1){
                //if 1 is pressed, return to battle
                gp.gameState = gp.battleState;
            }

            //if any other number is pressed, switch out to that pokemon
            //when a pokemon is swapped to, the opponent takes a turn
            if(code == KeyEvent.VK_2){
                Player.pokemonBag.add(0, Player.pokemonBag.remove(1));
                gp.ui.currentPokemon = Player.pokemonBag.get(0);

                int randnum = (int)(Math.random() * 4);
                
                if(gp.ui.wildPokemon.getHP() != 0){
                    gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!",
                    gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                }else{
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!", "");
                }

                gp.gameState = gp.battleState;
            }
            if(code == KeyEvent.VK_3){
                Player.pokemonBag.add(0, Player.pokemonBag.remove(2));
                gp.ui.currentPokemon = Player.pokemonBag.get(0);

                int randnum = (int)(Math.random() * 4);
                
                if(gp.ui.wildPokemon.getHP() != 0){
                    gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!",
                    gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                }else{
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!", "");
                }

                gp.gameState = gp.battleState;
            }
            if(code == KeyEvent.VK_4){
                Player.pokemonBag.add(0, Player.pokemonBag.remove(3));
                gp.ui.currentPokemon = Player.pokemonBag.get(0);

                int randnum = (int)(Math.random() * 4);
                
                if(gp.ui.wildPokemon.getHP() != 0){
                    gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!",
                    gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                }else{
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!", "");
                }

                gp.gameState = gp.battleState;
            }
            if(code == KeyEvent.VK_5){
                Player.pokemonBag.add(0, Player.pokemonBag.remove(4));
                gp.ui.currentPokemon = Player.pokemonBag.get(0);

                int randnum = (int)(Math.random() * 4);
                
                if(gp.ui.wildPokemon.getHP() != 0){
                    gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!",
                    gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                }else{
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!", "");
                }

                gp.gameState = gp.battleState;
            }
            if(code == KeyEvent.VK_6){
                Player.pokemonBag.add(0, Player.pokemonBag.remove(5));
                gp.ui.currentPokemon = Player.pokemonBag.get(0);

                int randnum = (int)(Math.random() * 4);
                
                if(gp.ui.wildPokemon.getHP() != 0){
                    gp.ui.wildPokemon.attack(randnum, gp.ui.currentPokemon);
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!",
                    gp.ui.wildPokemon.getName() + " used " + gp.ui.wildPokemon.moves[randnum].getName() + "!");
                }else{
                    gp.ui.updateMessages("Go, " + gp.ui.currentPokemon.getName() + "!", "");
                }

                gp.gameState = gp.battleState;
            }
        }
        
        //STARTER STATE
        //At the beginning, choose your starter --> 1, 2 or 3
        if(gp.gameState == gp.starterState){
            if(code == KeyEvent.VK_1){
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_2){
                gp.player.starter = new Pokemon("bulbasaur", "Grass");
                Player.pokemonBag.set(0, gp.player.starter);
                gp.gameState = gp.playState;
            }
            if(code == KeyEvent.VK_3){
                gp.player.starter = new Pokemon("squirtle", "Water");
                Player.pokemonBag.set(0, gp.player.starter);
                gp.gameState = gp.playState;
            }
        }
        
        //TRANSFER STATE
        if(gp.gameState == gp.transferState){
            if(code == KeyEvent.VK_B){
                gp.gameState = gp.pokemonCenterState;
            }
            if(Player.pokemonBag.size() > 1){
                if(code == KeyEvent.VK_1){
                    Player.pokemonBag.remove(0);
                }
                if(code == KeyEvent.VK_2){
                    Player.pokemonBag.remove(1);
                }
                if(code == KeyEvent.VK_3 && Player.pokemonBag.size() > 2){
                    Player.pokemonBag.remove(2);
                }
                if(code == KeyEvent.VK_4 && Player.pokemonBag.size() > 3){
                    Player.pokemonBag.remove(3);
                }
                if(code == KeyEvent.VK_5 && Player.pokemonBag.size() > 4){
                    Player.pokemonBag.remove(4);
                }
                if(code == KeyEvent.VK_6 && Player.pokemonBag.size() > 5){
                    Player.pokemonBag.remove(5);
                }
            }
            if(code == KeyEvent.VK_ENTER){
                gp.gameState = gp.playState;
            }
        }

    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int code = e.getKeyCode(); //returns the keyCode associated with the key in this event
        if(gp.gameState ==gp.playState){
            if(code== KeyEvent.VK_W || code == KeyEvent.VK_KP_UP){ //if W or up key is pressed
                upPressed = false;
            }
            if(code== KeyEvent.VK_S || code == KeyEvent.VK_KP_DOWN){ //if S key is pressed
                downPressed = false;
            }
            if(code== KeyEvent.VK_A || code == KeyEvent.VK_KP_LEFT){ //if A key is pressed
                leftPressed = false;
            }
            if(code== KeyEvent.VK_D || code == KeyEvent.VK_KP_RIGHT){ //if D key is pressed
                rightPressed = false;
            }
        }
        
    }
    
}