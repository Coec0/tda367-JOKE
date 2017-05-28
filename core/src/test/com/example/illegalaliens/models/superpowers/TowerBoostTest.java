package com.example.illegalaliens.models.superpowers;

import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.utilities.cooldown.CooldownHandler;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by Emil on 2017-05-27.
 */
public class TowerBoostTest {
    /**
     * Test for the tower boosting superpower
     * @Author Emil Josefsson
     */

    private Soldier soldier;

    private float modifiedRadius;
    private float modifiedDamage;
    private float modifiedCooldown;

    private float cooldownModifer;
    private float radiusModifier;
    private float damageModifier;

    private float originalRadius;
    private float originalDamage;
    private float originalCooldown;


    private CooldownHandler cdh;

    private TowerBoost booster;

    @Before
    public void setUp(){
        cdh = new CooldownHandler();
        soldier = new Soldier(0,0);
        booster = new TowerBoost(cdh,0);

        originalCooldown = soldier.getCooldownObject().getCooldownTicks();
        originalDamage = soldier.getDamage();
        originalRadius = soldier.getRadius();

        cooldownModifer = booster.getCooldownModifier();
        radiusModifier = booster.getRadiusModifier();
        damageModifier = booster.getDamageModifier();
    }

    @Test
    public void boostTower(){
        booster.boostTower(soldier);

        modifiedCooldown = soldier.getModifiedCooldown();
        modifiedDamage = soldier.getModifiedDamage();
        modifiedRadius = soldier.getModifiedRadius();

        assertEquals(modifiedCooldown, originalCooldown * cooldownModifer,0);
        assertEquals(modifiedDamage, originalDamage * damageModifier,0);
        assertEquals(modifiedRadius,originalRadius * radiusModifier,0);

    }

}
