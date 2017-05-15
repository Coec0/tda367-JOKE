package towers;


import projectiles.Bullet;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Ranger extends Tower {
    private static final int RADIUS = 750;
    private static final String NAME = "RANGER";
    private static final int COST = 300;
    private static final int COOLDOWN = 50;
    private static String DESCRIPTION = "Soldier with higher rate of fire";
    private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 100;


    public Ranger(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN,SIZE, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    

    @Override
    public Projectile makeProjectile() {
        Projectile p = new Bullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;    }

}
