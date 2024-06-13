public class Pokemon{
    String name;
    String type;
    
    
    int health;
    int maxHealth;
    
    public Move[] moves = new Move[4];
    
    public boolean caught;
    
    public int level;
    int xp;
    
    final String[] types = {"Fire", "Water", "Grass", "Normal"};
    
    final String[] Fire = {"charmander", "vulpix", "growlithe", "ponyta"};
    final String[] Water = {"squirtle", "psyduck", "poliwag", "krabby"};
    final String[] Grass = {"bulbasaur", "oddish", "paras", "bellsprout"};
    final String[] Normal = {"pidgey", "rattata", "spearow", "tauros", "eevee"};
    
    
    public static int totalPokemonCaught = 0;
    
    public Pokemon(String name, String type){
        this.name = name;
        this.type = type;
        this.maxHealth = (int)(Math.random() * 50) + 80;
        this.health = this.maxHealth;
        this.level = 5;
        this.xp = 0;
        
        moves[0] = new Move("Normal");
        moves[1] = new Move(type);
        moves[2] = new Move(type);
        moves[3] = new Move(type);
    }
    
    public Pokemon(){
        //randomly assign a type
        this.type = types[(int)(Math.random() * 4)];

        //Assign wild pokemon's name based on randomly generated type
        switch(this.type){
            case "Fire":
                this.name = Fire[(int)(Math.random() * 4)];
                break;
            case "Water":
                this.name = Water[(int)(Math.random() * 4)];
                break;
            case "Grass":
                this.name = Grass[(int)(Math.random() * 4)];
                break;
            default:
                this.name = Normal[(int)(Math.random() * 5)];
                break;
        }
        
        //wild pokemon level is randomly generated between 1 and 10;
        this.level = (int)(Math.random() * 11);
        this.xp = 0;
        
        //max hp is a random number between 80 and 130 and increases with leveling up
        this.maxHealth = (int)(Math.random() * 50) + 80 + this.level * 2;
        this.health = this.maxHealth;
        
        //1 normal type move and 3 type-specific moves are assigned to each pokemon
        moves[0] = new Move("Normal");
        moves[1] = new Move(type);
        moves[2] = new Move(type);
        moves[3] = new Move(type);
    }
    
    public void heal(int amount){
        if(amount + health < maxHealth){
            health += amount;
        }
        else{
            health = maxHealth;
        }
    }
    
    public void attack(int move, Pokemon other){
        double multiplier = 0.6;
        //move is 0, 1, 2 or 3 based on the moves in the moves[] array
        this.xp += 13;
        
        //determine move effectiveness
        if((moves[move].getType().equals("Fire") && other.getType().equals("Grass"))
        || (moves[move].getType().equals("Grass") && other.getType().equals("Water"))
        || (moves[move].getType().equals("Water") && other.getType().equals("Fire"))){
            multiplier = 1;
        }
        else if((moves[move].getType().equals("Grass") && other.getType().equals("Fire"))
        || (moves[move].getType().equals("Water") && other.getType().equals("Grass"))
        || (moves[move].getType().equals("Fire") && other.getType().equals("Water"))){
            multiplier = 0.3;
        }
        
        //damage equation: base damage, effectiveness multiplier, level
        other.takeDamage((int)(moves[move].damage * multiplier * (1 + level/10.0) * (Math.random() * 0.2 + 0.9)));
    }
    
    public void takeDamage(int damage){
        if(this.health - damage > 0){
            this.health -= damage;

        } else{
            this.health = 0;
        }
    }
    
    public boolean catchPokemon(){
        if((Math.random()*2) - ((double)health/maxHealth) > 0){
            Player.pokemonBag.add(this);
            this.caught = true;
        }
        return caught;
    }
    
    public void updateLevel(){
        //level up
        if(this.xp > 40){
            this.level++;
            this.xp -= 100;
            this.maxHealth += 2 * level;
        }
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getHP(){
        return this.health;
    }
    
    public int getMaxHP(){
        return this.maxHealth;
    }
    
    public String getType(){
        return this.type;
    }
    
    public int getLevel(){
        return this.level;
    }
    
}