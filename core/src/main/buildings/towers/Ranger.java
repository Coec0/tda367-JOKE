package buildings.towers;


import projectiles.Projectile;
import projectiles.RangerBullet;

/**
 * Created by Emil on 2017-04-17.
 */
public class Ranger extends Tower {
    private static final int RADIUS = 750;
    private static final String NAME = "RANGER";
    private static final int COST = 300;
    private static final int COOLDOWN = 50;
    private static String DESCRIPTION = "";
    private static final float SIZE = 50;

    public Ranger(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN,SIZE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    

    @Override
    public Projectile makeProjectile() {
        Projectile p = new RangerBullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }

}
