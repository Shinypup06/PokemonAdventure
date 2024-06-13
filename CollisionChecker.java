public class CollisionChecker{
    GamePanel gp;
    KeyHandler keyH;
    
    //pass in gp and keyH to keep things uniform
    public CollisionChecker(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
    }
    
    public void checkTile(Entity entity){
        //declare the global x and y coords of the player character's bounds 
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        //find the column and row of the tile the player is hitting
        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;
        
        int tileNum1, tileNum2;
        
        //If up is pressed, check the top left and top right of the character
        if(keyH.upPressed){
            entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
            tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            
            //if the tile is solid
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                entity.collisionOn = true;
            }
        }
        
        //if down is pressed, check the bottom left and bottom right of the character
        if(keyH.downPressed){
            entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
            tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            
            //if the tile is solid
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                entity.collisionOn = true;
            }
            //If player hits pokemon center, open the pokemon center window
            if(tileNum1 == 3 || tileNum2 == 3){
                gp.gameState = gp.pokemonCenterState;
            }
        }
        
        //if left is pressed, check the top and bottom left of character
        if(keyH.leftPressed){
            entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
            tileNum1= gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            
            //if the tile is solid
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                entity.collisionOn = true;
            }
        }
        
        //if right is pressed, check the top and bottom right of the character
        if(keyH.rightPressed){
            entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
            tileNum1= gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            
            //if the tile is solid
            if(gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision){
                entity.collisionOn = true;
            }
        }
        
    }
    
    //checks for collisions inside the tall grass
    public boolean checkTallGrass(Entity entity){
        boolean result = false;
        for(int i = 0; i < gp.tallGrass.length; i++){
            if(gp.tallGrass[i] != null){
                //get entity's solid area position
                entity.solidArea.x += entity.worldX;
                entity.solidArea.y += entity.worldY;
                
                
                //get the object's solid area position
                gp.tallGrass[i].solidArea.x = gp.tallGrass[i].worldX + gp.tallGrass[i].solidArea.x;
                gp.tallGrass[i].solidArea.y = gp.tallGrass[i].worldY + gp.tallGrass[i].solidArea.y;
            
                //only updates when keys are pressed; does not update when player is idling in tall grass
                //uses .intersects method from the rectangle class because tallGrass is not a solid tile
                if(keyH.upPressed){
                    entity.solidArea.y -= entity.speed;
                    if(entity.solidArea.intersects(gp.tallGrass[i].solidArea)){
                        result = true;
                    }
                    entity.solidArea.y += entity.speed;
                }
                if(keyH.downPressed){
                    entity.solidArea.y += entity.speed;
                    if(entity.solidArea.intersects(gp.tallGrass[i].solidArea)){
                        result = true;
                    }
                    entity.solidArea.y -= entity.speed;
                }
                if(keyH.leftPressed){
                    entity.solidArea.x -= entity.speed;
                    if(entity.solidArea.intersects(gp.tallGrass[i].solidArea)){
                        result = true;
                    }
                    entity.solidArea.x += entity.speed;
                }
                if(keyH.rightPressed){
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gp.tallGrass[i].solidArea)){
                        result = true;
                    }
                    entity.solidArea.x -= entity.speed;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                
                gp.tallGrass[i].solidArea.x = gp.tallGrass[i].solidAreaDefaultX;
                gp.tallGrass[i].solidArea.y = gp.tallGrass[i].solidAreaDefaultY;

            }
        }
        return result;
    }
}