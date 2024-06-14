public class Move{
    String name;
    String type;
    public int damage;
    
    
    // damage, name
    final String[] NormalMoves = {"40Tackle", "40Scratch", "40Quick Attack", "50Cut", "45Barrage", "85Body Slam", "70Headbutt", "80Hyper Fang", "40Pound", "50Rapid Spin", "70Secret Power", "55Vise Grip", "60Take Down"};
    final String[] FireMoves = {"50Flame Charge", "65Fire Fang", "40Ember", "75Fire Punch","85Blaze Kick", "80Fire Lash", "60Flame Wheel", "75Temper Flare", "80Fiery Dance", "70Flame Burst", "60Flamethrower"};
    final String[] GrassMoves = {"40Branch Poke", "75Bullet Seed", "90Leaf Blade", "40Leafage", "60Petal Blizzard", "55Razor Leaf", "80Seed Bomb", "50Trailblaze", "45Vine Whip", "65Leaf Tornado"};
    final String[] WaterMoves = {"50Liquidation", "65Aqua Tail", "40Aqua Jet", "75Waterfall", "40Bubble", "65Bubble Beam", "90Muddy Water", "80Scald", "40Water Gun", "60Surf", "60Water Pulse"};
    
    //Constructor randomly generates a move with a set damage and name based on the given type
    public Move(String type){
        this.type = type;
        String info;
        
        if(type.equals("Fire")){
            info = FireMoves[(int)(Math.random() * FireMoves.length)];
            name = info.substring(2);
            damage = Integer.valueOf(info.substring(0,2));
        }
        else if(type.equals("Water")){
            info = WaterMoves[(int)(Math.random() * WaterMoves.length)];
            name = info.substring(2);
            damage = Integer.valueOf(info.substring(0,2));
        }
        else if(type.equals("Grass")){
            info = GrassMoves[(int)(Math.random() * GrassMoves.length)];
            name = info.substring(2);
            damage = Integer.valueOf(info.substring(0,2));
        }
        else{
            info = NormalMoves[(int)(Math.random() * NormalMoves.length)];
            name = info.substring(2);
            damage = Integer.valueOf(info.substring(0,2));
        }
    }
    
    public String getName(){
        return name;
    }
    
    public String getType(){
        return type;
    }
}