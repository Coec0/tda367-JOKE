package com.example.illegalaliens.models.projectiles;

import com.example.illegalaliens.utilities.Node;


/**
 * Created by Emil on 2017-04-04.
 */
public class Bullet extends SingleProjectile {

    private static final float RADIUS = 2;
    //private static final int HEALTH = 2;

    public Bullet(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED, int HEALTH){
        super(HEALTH,DAMAGE, SPEED, RADIUS, DIRECTION, POSITION);
    }
}
