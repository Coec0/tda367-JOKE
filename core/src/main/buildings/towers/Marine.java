package buildings.towers;

import projectiles.Bullet;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-02.
 */
public class Marine extends Tower {
    private static final int RADIUS = 10;
    private static final String NAME = "MARINE";
    private static final int COST = 100;
    private static int COOLDOWN = 50;
    private static String DESCRIPTION = "Soldier with more damage";
    private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 100;


    public Marine(int x, int y){
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
