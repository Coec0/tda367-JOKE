package boardobjects.towers;


import boardobjects.BoardObject;
import factories.ProjectileFactory;
import politics.parties.Party;
import politics.parties.PartyFactory;
import projectiles.Projectile;

public class Tank extends Tower {
    //Tower specific stats, name and description
    private static final String NAME = "TANK";
    private static String DESCRIPTION = "Great damage but shoots slow";
    private static final int RADIUS = 400;
    private static final int COST = 100;
    private static final float COOLDOWN = 50;
    private static final Party PARTY = PartyFactory.Democrat(10); // Just for now
    private static final float SIZE = 55;
    private static final float DAMAGE = 100;
    private static final float SPEED = 35;

    public Tank(int x, int y, float radius, float cooldown, float cost, float damage){
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
        Projectile p = ProjectileFactory.createMissile(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED);
        super.notifyObservers(p, "spawn");
        return p;
    }

	@Override
	public BoardObject clone(int x, int y) {
		return new Tank(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}

}
