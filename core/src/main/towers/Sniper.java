package towers;


import projectiles.Bullet;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Sniper extends Tower {


    private static final int RADIUS = 1000;
    private static final String NAME = "SNIPER";
    private static final int COST = 150;
    private static final float COOLDOWN = 75;
    private static String DESCRIPTION = "Very long range";
    private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 100;

    public Sniper(int x, int y, int radius, float cooldown, int cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage);
    }
    
    public Sniper(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
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
