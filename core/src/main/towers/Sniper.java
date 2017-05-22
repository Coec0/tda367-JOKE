package towers;


import buildings.BoardObject;
import politics.parties.Party;
import politics.parties.PartyFactory;
import projectiles.Bullet;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Sniper extends Tower {
    //Tower specific stats, name and description
    private static final String NAME = "SNIPER";
    private static String DESCRIPTION = "Very long range";
    private static final int RADIUS = 1500;
    private static final int COST = 150;
    private static final float COOLDOWN = 75;
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
    private static final float SIZE = 50;
    private static final float DAMAGE = 50;
    private static final float SPEED = 105;

    public Sniper(int x, int y, float radius, float cooldown, float cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }
    
    public Sniper(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

   

    @Override
    public Projectile makeProjectile() {
        Projectile p = new Bullet(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED);
        super.notifyObservers(p, "spawn");
        return p;    
       }
    
	@Override
	public BoardObject clone(int x, int y) {
		return new Sniper(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}

}
