import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.models.boardobjects.towers.TowerUpgrader;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Emil on 2017-05-27.
 */
public class TowerUpgradeTest {
    /**
     * Test for upgrading towers
     * @author Emil Josefsson
     */

    private Soldier soldier;
    private float originalRadius;
    private float radiusModifier;

    private float originalDamage;
    private float damageModifier;

    private float originalCooldown;
    private float cooldownModifier;

    private TowerUpgrader upgrader;

    @Before
    public void setUp(){
        upgrader = new TowerUpgrader();

        soldier = new Soldier(0,0);

        originalRadius = soldier.getRadius();
        originalCooldown = soldier.getCooldownObject().getCooldownTime();
        originalDamage = soldier.getDamage();

        cooldownModifier = upgrader.getCooldownMultiplier();
        damageModifier = upgrader.getDamageMultiplier();
        radiusModifier = upgrader.getRadiusMultiplier();
    }

    @Test
    public void upgradeTower() throws Exception{
        upgrader.upgradeDamage(soldier);
        upgrader.upgradeRadius(soldier);
        upgrader.reduceCooldown(soldier);

        float newRadius = soldier.getRadius();
        float newDamage = soldier.getDamage();
        float newCooldown = soldier.getCooldownObject().getCooldownTime();

        assertEquals(newCooldown, originalCooldown * cooldownModifier,0);
        assertEquals(newDamage,originalDamage * damageModifier,0);
        assertEquals(newRadius, originalRadius*radiusModifier,0);
    }
}
