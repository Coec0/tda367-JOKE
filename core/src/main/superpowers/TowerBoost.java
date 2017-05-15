package superpowers;

import com.badlogic.gdx.utils.Array;
import cooldown.CooldownObject;
import towers.Tower;

/**
 * Created by Emil on 2017-05-15.
 */
public class TowerBoost implements Superpower {

    private final float radiusModifier = 1.3f;
    private final float damageModifier = 1.3f;
    private final float cooldownModifier = 0.7f;
    private final float durationFrames = 500;
    private CooldownObject duration;

    public TowerBoost(){
        duration = new CooldownObject(durationFrames);
    }

    public void boostTower(Tower tower){
        float originalDamage = tower.getDamage();
        float originalRadius = tower.getRadius();
        CooldownObject originalCooldown = tower.getCooldownObject();
        tower.setDamage(tower.getDamage() * damageModifier);
        tower.setRadius(tower.getRadius() * radiusModifier);
        tower.getCooldownObject().setCooldownTime(tower.getCooldownObject().getCooldownTime() * cooldownModifier);
        //need to add duration and revert changes when time is out
    }

    

    @Override
    public void usePower() {

    }
}
