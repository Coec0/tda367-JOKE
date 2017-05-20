package towers;

import buildings.BoardObject;
import factories.ProjectileFactory;
import politics.parties.Party;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-14.
 */
public class Minutemen extends Tower {

    private static final int RADIUS = 1000;
    private static final String NAME = "MINUTEMEN";
    private static final int COST = 50;
    private static final float COOLDOWN = 50;
    private static String DESCRIPTION = "Temporary soldiers with high range but low rate of fire";
    private static final Party PARTY = null;
    private static final float SIZE = 50;
    private static final float DAMAGE = 50;
    private static final float SPEED = 50;

    public Minutemen(int x, int y, float radius, float cooldown, float cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }

    public Minutemen(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }

    public String getDescription(){
        return DESCRIPTION;
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;
        }

	@Override
	public BoardObject clone(int x, int y) {
		return new Minutemen(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}
}
