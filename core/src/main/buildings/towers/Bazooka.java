package buildings.towers;

import projectiles.BazookaMissile;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-02.
 */
public class Bazooka extends Tower {
    private static final int RADIUS = 10;
    private static final String NAME = "BAZOOKA";
    private static final int COST = 100;
    private static int COOLDOWN = 50;
    private static String DESCRIPTION = "";
    private static final float SIZE = 50;
    
    public Bazooka(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN, SIZE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }


    @Override
    public Projectile makeProjectile() {
        Projectile p = new BazookaMissile(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }
}
