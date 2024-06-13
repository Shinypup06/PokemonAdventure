public class AssetSetter{
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        int counter = 0;

        //set the top left corner of tall grass
        for(int i = 1; i < 8; i++){
            for (int j = 1; j < 5; j++){
                gp.tallGrass[counter] = new TallGrass();
                gp.tallGrass[counter].worldX = i * gp.tileSize;
                gp.tallGrass[counter].worldY = j * gp.tileSize;
                counter++;
            }
        }

        //set the bottom right corner of tall grass
        for(int i = 16; i > 9; i--){
            for (int j = 12; j > 8; j--){
                gp.tallGrass[counter] = new TallGrass();
                gp.tallGrass[counter].worldX = i * gp.tileSize;
                gp.tallGrass[counter].worldY = j * gp.tileSize;
                counter++;
            }
        }
    }
}