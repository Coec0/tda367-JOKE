package com.example.illegalaliens.models.boardobjects.towers;


import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.politics.parties.PartyFactory;
import com.example.illegalaliens.models.projectiles.Bullet;
import com.example.illegalaliens.models.projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Ranger extends Tower {
    //Tower specific stats, name and description

    private static final int RADIUS = 350;
    private static final String NAME = "RANGER";
    private static final int COST = 500;
    private static final float COOLDOWN = 15;
    private static String DESCRIPTION = "Soldier with higher rate of fire";
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
    private static final float SIZE = 35;
    private static final float DAMAGE = 25;
    private static final float SPEED = 25;
    private static final int projectileHealth = 2;

    public Ranger(int x, int y, float radius, float cooldown, float cost, float damage){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    }

    public Ranger(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE);
    }
    
    public String getDescription(){
    	return DESCRIPTION;
    }

    

    @Override
    public Projectile makeProjectile() {
        Projectile p = new Bullet(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED,projectileHealth);
        super.notifyObservers(p, "spawn");
        return p;
    }

	@Override
	public BoardObject clone(int x, int y) {
		return new Ranger(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}

}
