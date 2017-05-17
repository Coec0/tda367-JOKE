package towers;


import buildings.BoardObject;
import factories.ProjectileFactory;
import politics.parties.Party;
import politics.parties.PartyFactory;
import politics.parties.Voter;
import projectiles.Projectile;

public class Soldier extends Tower implements Voter{
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;
    private static final float COOLDOWN = 10;
    private Party party = PartyFactory.Republican(3); // Just for now
	private static String DESCRIPTION = "A regular soldier";
	private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 10;

    public Soldier(int x, int y, float radius, float cooldown, int cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage);
    }
    
    public Soldier(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;    }


	@Override
	public Party getParty() {
		return party;
	}
	
	@Override
	public BoardObject clone(int x, int y) {
		return new Soldier(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}
}
