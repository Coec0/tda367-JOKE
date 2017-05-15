package towers;


import factories.ProjectileFactory;
import politics.parties.Party;
import politics.parties.PartyFactory;
import politics.parties.Voter;
import projectiles.Projectile;

public class Tank extends Tower implements Voter{

    private static final int RADIUS = 1000;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final float COOLDOWN = 50;
    private static String DESCRIPTION = "Great damage but shoots slow";
    private Party party = PartyFactory.Democrat(10); // Just for now
    private static final float SIZE = 50;
    private static final float DAMAGE = 500;
    private static final float SPEED = 50;


    public Tank(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN,SIZE, DAMAGE);
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
	public Party getParty() {
		return party;
	}

}
