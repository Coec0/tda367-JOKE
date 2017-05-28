package com.example.illegalaliens.models.projectiles;

import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-04-04.
 */
public class Missile extends AOEProjectile {

    private static final float AREAOFEFFECTRADIUS = 350;
    private static final float RADIUS = 20;
    //private static final int HEALTH = 1;

    public Missile(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED,int HEALTH){
        super(HEALTH, DAMAGE, SPEED,RADIUS, AREAOFEFFECTRADIUS, DIRECTION, POSITION);
    }
}
