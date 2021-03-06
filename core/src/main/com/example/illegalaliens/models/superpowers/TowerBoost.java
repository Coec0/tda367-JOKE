package com.example.illegalaliens.models.superpowers;

import com.example.illegalaliens.models.boardobjects.Superpower;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.utilities.cooldown.CooldownHandler;
import com.example.illegalaliens.utilities.cooldown.CooldownObject;

/**
 * Created by Emil on 2017-05-15.
 */
public class TowerBoost implements Superpower {
	private int superPowerCost;
	
    //Modifier stats to be used
    private final float radiusModifier = 1.15f;
    private final float damageModifier = 1.3f;
    private final float cooldownModifier = 0.1f;

    //Duration for the boost
    private final float durationFrames = 200f;
    private CooldownObject duration;

    public TowerBoost(CooldownHandler cdh, int superPowerCost){
        duration = new CooldownObject(durationFrames, false);
        cdh.addCooldownObject(duration);
        this.superPowerCost = superPowerCost;
    }

    /**
     * Boosts the tower stats using the modifiers
     * @param tower to be boosted
     */
    public void boostTower(Tower tower){

        float damage  = tower.getDamage() * damageModifier;
        float radius = tower.getRadius() * radiusModifier;
        float cooldown = tower.getCooldownObject().getCooldownTime() * cooldownModifier;

        tower.setModifiedCooldown(cooldown);
        tower.setModifiedDamage(damage);
        tower.setModifiedRadius(radius);

        tower.setDamage(tower.getDamage() + damage);
        tower.setRadius(tower.getRadius() + radius);
        tower.getCooldownObject().setCooldownTime(tower.getCooldownObject().getCooldownTime() + cooldown);
        duration.setOnCooldown(true);
        //All above is just logic for easily reverting the boost later on
    }

    /**
     * Unboosts the towers previously boosted
     * @param tower to be unboosted
     */

    public void unBoost(Tower tower){
        float damage = tower.getDamage() - tower.getModifiedDamage();
        float radius = tower.getRadius() - tower.getModifiedRadius();
        float cooldown = tower.getCooldownObject().getCooldownTime() - tower.getModifiedCooldown();
        if (damage >0 )
            tower.setDamage(damage);
        else
            tower.setDamage(0);

        if (radius > 0)
            tower.setRadius(radius);
        else
            tower.setRadius(0);

        if (cooldown >0)
            tower.setCooldown(cooldown);
        else
            tower.setCooldown(0);

    }

    public boolean isFinished(){
        return (!duration.isOnCooldown());
    }

    public float getRadiusModifier() {
        return radiusModifier;
    }

    public float getDamageModifier() {
        return damageModifier;
    }

    public float getCooldownModifier() {
        return cooldownModifier;
    }

    @Override
	public int getSuperPowerCost() {
		return superPowerCost;
	}

	@Override
	public void setSuperPowerCost(int cost) {
		this.superPowerCost = cost;
	}

	@Override
	public String getName() {
		return "TOWERBOOSTER";
	}
}
