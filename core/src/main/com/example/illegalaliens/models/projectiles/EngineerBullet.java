package com.example.illegalaliens.models.projectiles;

import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-05-02.
 */
public class EngineerBullet extends SingleProjectile {

    private static final float RADIUS = 20;
    private static final int HEALTH = 1;

    public EngineerBullet(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(HEALTH,DAMAGE, SPEED, RADIUS, DIRECTION, POSITION);
    }
}
