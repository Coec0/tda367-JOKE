package buildings.towers;


import controllers.ProjectileController;
import projectiles.ArtilleryRound;
import projectiles.Missile;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Howitzer extends Tower {
    private static final int RADIUS = 10;
    private static final String NAME = "HOWITZER";
    private static final int COST = 100;
    private static final int COOLDOWN = 50;
    private static String DESCRIPTION = "Very high damage but low rate of fire";
    private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 100;

    public Howitzer(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN,SIZE, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    

    @Override
    public Projectile makeProjectile() {
        Projectile p = new ArtilleryRound(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;    }
}
