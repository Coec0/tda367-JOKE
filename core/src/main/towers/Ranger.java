package towers;


import buildings.BoardObject;
import politics.parties.Party;
import politics.parties.PartyFactory;
import projectiles.Bullet;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Ranger extends Tower {
    private static final int RADIUS = 750;
    private static final String NAME = "RANGER";
    private static final int COST = 300;
    private static final float COOLDOWN = 50;
    private static String DESCRIPTION = "Soldier with higher rate of fire";
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
    private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 100;

    public Ranger(int x, int y, float radius, float cooldown, int cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }

    public Ranger(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    

    @Override
    public Projectile makeProjectile() {
        Projectile p = new Bullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;
    }

	@Override
	public BoardObject clone(int x, int y) {
		return new Ranger(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}
}
