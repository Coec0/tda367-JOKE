package com.example.illegalaliens.models.boardobjects.towers;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.politics.parties.PartyFactory;
import com.example.illegalaliens.models.projectiles.Net;
import com.example.illegalaliens.models.projectiles.Projectile;

/**
 * Created by Emil on 2017-05-07.
 */
public class NetGunner extends Tower {
	//Tower specific stats, name and description

	private static final String NAME = "NetGunner";
    private static final String DESCRIPTION = "A tower that slows down aliens with his net";
    private static final int SIZE = 25;
    private static final int RADIUS = 250;
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
    private static final int COST = 150;
    private static final float COOLDOWN = 90;
    private static final float DAMAGE = 10;
    private static final float SPEED = 10;
    
    public NetGunner(int x, int y, float radius, float cooldown, float cost){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, DAMAGE, PARTY);
    	//affectedEnemies = new Array<Enemy>(false, 5000);
    }
    
    public NetGunner(int x, int y){
        this(x , y, RADIUS, COOLDOWN, COST);
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

}
