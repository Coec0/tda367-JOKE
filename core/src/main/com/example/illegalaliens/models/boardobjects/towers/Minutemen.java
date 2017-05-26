package com.example.illegalaliens.models.boardobjects.towers;

import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.models.projectiles.ProjectileFactory;
import com.example.illegalaliens.models.superpowers.Superpower;

/**
 * Created by Emil on 2017-05-14.
 */
public class Minutemen extends Tower implements Superpower{
    //Tower specific stats, name and description

    private static final int RADIUS = 1000;
    private static final String NAME = "MINUTEMEN";
    private static final int COST = 50;
    private static final float COOLDOWN = 50;
    private static String DESCRIPTION = "Temporary soldiers with high range but low rate of fire";
    private static final Party PARTY = null;
    private static final float SIZE = 25;
    private static final float DAMAGE = 50;
    private static final float SPEED = 50;

    
    private int superPowerCost;
    
    public Minutemen(int x, int y, float radius, float cooldown, float cost, float damage,int superPowerCost){
    	super(x, y, radius, NAME, cost, cooldown, SIZE, damage, PARTY);
    	this.superPowerCost = superPowerCost;
    }

    public Minutemen(int x, int y, int superPowerCost){

        this(x, y, RADIUS, COOLDOWN, COST, DAMAGE, superPowerCost);
    }

    public String getDescription(){
        return DESCRIPTION;
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;
        }

	@Override
	public BoardObject clone(int x, int y) {
		return new Minutemen(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage(), superPowerCost);
	}

	@Override
	public int getSuperPowerCost() {
		return superPowerCost;
	}

	@Override
	public void setSuperPowerCost(int cost) {
		this.superPowerCost = cost;
	}

}
