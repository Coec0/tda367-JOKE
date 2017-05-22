package towers;

import buildings.BoardObject;
import buildings.Building;
import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import politics.parties.Party;
import politics.parties.PartyFactory;
import projectiles.Bullet;
import projectiles.Net;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-07.
 */
public class NetGunner extends Tower {
    private static final String NAME = "NetGunner";
    private static final String DESCRIPTION = "A tower that slows down aliens with his net";
    private static final int SIZE = 50;
    private static final int SLOWRADIUS = 500;
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
    private static final int COST = 400;
    private static final float COOLDOWN = 90;
    private static final float DAMAGE = 0;
    private static final float SPEED = 25;
    private static final float SCALE = 0.25f;
    
    public NetGunner(int x, int y, float radius, float cooldown, float cost){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, DAMAGE, PARTY);
    	//affectedEnemies = new Array<Enemy>(false, 5000);
    }
    
    public NetGunner(int x, int y){
        this(x , y, SLOWRADIUS, COOLDOWN, COST);
    }


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

	
	@Override
	public BoardObject clone(int x, int y) {
		return new NetGunner(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost());
	}
	@Override
	public void setTarget(Array<Enemy> targets) {
		Array<Enemy> notNetted = new Array<Enemy>();
		for(Enemy target : targets){
			if(!target.isInNet()){
				notNetted.add(target);
			}
		}
		super.setTarget(notNetted);
		
	}

	@Override
	public Projectile makeProjectile() {
		Projectile p = new Net(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED);
        super.notifyObservers(p, "spawn");
        return p;
	}

	@Override
	public float getScale() {
		return SCALE;
	}

}
