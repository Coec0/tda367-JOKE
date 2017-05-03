package buildings.towers;

import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-03.
 */
public class TowerUpgrader {

    public TowerUpgrader(){

    }

    public void upgradeRadius(Tower tower){
        tower.setRadius(0);
    }

    public void upgradeDamage(Projectile projectile){
        projectile.setDamage(0);
    }

    public void enableAreaOfEffect(Projectile projectile){
        projectile.setAreaOfEffect(true);
    }

    public void reduceCooldown(Tower tower){
        tower.setCooldown(5);
    }
}
