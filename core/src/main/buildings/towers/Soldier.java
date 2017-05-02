package buildings.towers;


import politics.parties.Republican;
import projectiles.Bullet;
import projectiles.Projectile;

public class Soldier extends Tower implements Republican{
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;
    private static final int COOLDOWN = 10;
	private int votes = 3; // Just for now


    public Soldier(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }


   

    @Override
    public Projectile makeProjectile() {
    	
        Projectile p = new Bullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }


	@Override
	public int getVotes() {
		return votes;
	}


	@Override
	public void setVotes(int votes) {
		this.votes  = votes;

	}
}
