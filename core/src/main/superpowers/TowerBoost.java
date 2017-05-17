package superpowers;

import com.badlogic.gdx.utils.Array;
import cooldown.CooldownHandler;
import cooldown.CooldownObject;
import towers.Tower;

/**
 * Created by Emil on 2017-05-15.
 */
public class TowerBoost implements Superpower {

    private final float radiusModifier = 20f;
    private final float damageModifier = 1.3f;
    private final float cooldownModifier = 0.1f;

    private final float durationFrames = 50;
    private CooldownObject duration;
    private CooldownHandler cdh;

    public TowerBoost(CooldownHandler cdh){
        duration = new CooldownObject(durationFrames, false);
        this. cdh = cdh;
        cdh.addCooldownObject(duration);
    }

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
        System.out.println("BOOST");
        //need to add duration and revert changes when time is out
    }

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

        System.out.println("UNBOOST");
    }

    public boolean isFinished(){
        return (!duration.isOnCooldown());
    }


    @Override
    public void usePower() {

    }
}
