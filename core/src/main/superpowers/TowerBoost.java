package superpowers;

import com.badlogic.gdx.utils.Array;
import towers.Tower;

/**
 * Created by Emil on 2017-05-15.
 */
public class TowerBoost implements Superpower {

    private final float radiusModifier = 1.3f;
    private final float damageModifier = 1.3f;
    private final float cooldownModifier = 0.7f;
    private final float duration = 500;

    public TowerBoost(){

    }

    public void boostTowers(Array<Tower> towers){
        for (Tower tower: towers){
            tower.setDamage(tower.getDamage() * damageModifier);
            tower.setRadius(tower.getRadius() * radiusModifier);
            //tower.setCooldown
            //need to add duration and revert changes when time is out
        }
    }

    @Override
    public void usePower() {

    }
}
