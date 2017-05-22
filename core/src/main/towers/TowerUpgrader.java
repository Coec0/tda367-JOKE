package towers;

/**
 * Created by Emil on 2017-05-03.
 */
public class TowerUpgrader {
    /**
     * This class handles all permament tower upgrades
     */

    public TowerUpgrader(){

    }

    public void upgradeRadius(Tower tower, float radius){
        tower.setRadius(radius);
    }

    public void upgradeDamage(Tower tower, float damage){
        tower.setDamage(damage);
    }


    public void reduceCooldown(Tower tower, float cooldown){
        tower.getCooldownObject().decreaseCooldownTime(cooldown);
    }
}
