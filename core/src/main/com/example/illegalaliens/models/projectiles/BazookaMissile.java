package com.example.illegalaliens.models.projectiles;

import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-05-02.
 */
public class BazookaMissile extends AOEProjectile {
    private static final float AREAOFEFFECTRADIUS = 30;
    private static final float RADIUS = 20;
    private static final int HEALTH = 1;

    public BazookaMissile(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(HEALTH, DAMAGE, SPEED, RADIUS, AREAOFEFFECTRADIUS, DIRECTION, POSITION);
    }
}
