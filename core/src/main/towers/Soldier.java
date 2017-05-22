package towers;


import buildings.BoardObject;
import factories.ProjectileFactory;
import politics.parties.Party;
import politics.parties.PartyFactory;
import projectiles.Projectile;

public class Soldier extends Tower {
    //Tower specific stats, name and description
    private static final String NAME = "SOLDIER";
    private static String DESCRIPTION = "A regular soldier";
    private static final int RADIUS = 300;
    private static final int COST = 50;
    private static final float COOLDOWN = 15;
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
	private static final float SIZE = 25;
    private static final float DAMAGE = 30;
    private static final float SPEED = 10;

    public Soldier(int x, int y, float radius, float cooldown, float cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }
    
    public Soldier(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED);
        super.notifyObservers(p, "spawn");
        return p;
       }


	@Override
	public BoardObject clone(int x, int y) {
		return new Soldier(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}
}
