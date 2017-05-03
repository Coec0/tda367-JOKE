package buildings.towers;


import politics.parties.Democrat;
import projectiles.Missile;
import projectiles.Projectile;

public class Tank extends Tower implements Democrat{

    private static final int RADIUS = 1000;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final int COOLDOWN = 50;
    private static String DESCRIPTION = "";
    private static final float SIZE = 50;


    public Tank(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN,SIZE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

   
    @Override
    public Projectile makeProjectile() {
        Projectile p = new Missile(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;
    }


	@Override
	public int getVotes() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public void setVotes(int votes) {
		// TODO Auto-generated method stub
		
	}

}
