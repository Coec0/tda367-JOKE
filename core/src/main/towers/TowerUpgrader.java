package towers;

import com.badlogic.gdx.utils.Array;

/**
 * Created by Emil on 2017-05-03.
 */
public class TowerUpgrader {
    /**
     * This class handles all permament tower upgrades
     */

    private final float costIncreaseMultiplier = 2;

    private float radiusMultiplier;
    private float damageMultiplier;
    private float cooldownMultiplier;

    private float radiusUpgradeCost;
    private float damageUpgradeCost;
    private float cooldownUpgradeCost;

    Array<Tower> upgradedTowers;

    public TowerUpgrader(){
        radiusMultiplier = 1.3f;
        damageMultiplier = 1.3f;
        cooldownMultiplier = 0.9f;

        radiusUpgradeCost = 200;
        damageUpgradeCost = 175;
        cooldownUpgradeCost = 190;

        upgradedTowers = new Array<Tower>();

    }

    public void upgradeRadius(Tower tower){
        tower.setRadius(tower.getRadius() * radiusMultiplier);
        upgradedTowers.add(tower);
        increaseCost(radiusUpgradeCost);
    }

    public void upgradeDamage(Tower tower){
        tower.setDamage(tower.getDamage() * damageMultiplier);
        upgradedTowers.add(tower);
    }


    public void reduceCooldown(Tower tower){
        tower.getCooldownObject().setCooldownTime(tower.getCooldownObject().getCooldownTime() * cooldownMultiplier);
        upgradedTowers.add(tower);
    }

    //getters for model

    public float getRadiusUpgradeCost() {
        return radiusUpgradeCost;
    }

    public float getDamageUpgradeCost() {
        return damageUpgradeCost;
    }

    public float getCooldownUpgradeCost() {
        return cooldownUpgradeCost;
    }

    public void increaseCost(float value){
        value = value * costIncreaseMultiplier;
    }
}
