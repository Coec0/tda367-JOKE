package com.example.illegalaliens.models.boardobjects.towers;


import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.politics.parties.PartyFactory;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.models.projectiles.ProjectileFactory;

public class Soldier extends Tower {
    //Tower specific stats, name and description
    private static final String NAME = "SOLDIER";
    private static String DESCRIPTION = "A regular soldier";
    private static final int RADIUS = 230;
    private static final int COST = 75;
    private static final float COOLDOWN = 30;
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
	private static final float SIZE = 25;
    private static final float DAMAGE = 30;
    private static final float SPEED = 20;
    private static final int projectileHealth = 1;

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
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), super.getDamage(), SPEED,projectileHealth);
        super.notifyObservers(p, "spawn");
        return p;
       }


	@Override
	public BoardObject clone(int x, int y) {
		return new Soldier(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost(), getDamage());
	}
}
