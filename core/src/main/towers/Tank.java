package towers;


import buildings.BoardObject;
import factories.ProjectileFactory;
import politics.parties.Party;
import politics.parties.PartyFactory;
import projectiles.Projectile;

public class Tank extends Tower {

    private static final int RADIUS = 1000;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final float COOLDOWN = 50;
    private static String DESCRIPTION = "Great damage but shoots slow";
    private static final Party PARTY = PartyFactory.Democrat(10); // Just for now
    private static final float SIZE = 50;
    private static final float DAMAGE = 500;
    private static final float SPEED = 50;

    public Tank(int x, int y, float radius, float cooldown, int cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }

    public Tank(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

   
    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createMissile(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;
    }

	@Override
	public BoardObject clone(int x, int y) {
		return new Tank(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}

}
