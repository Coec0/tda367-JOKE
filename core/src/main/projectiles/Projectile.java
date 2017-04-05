package projectiles;

import com.badlogic.gdx.graphics.Texture;
import enemies.Enemy;
import utilities.Node;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public abstract class Projectile {

    private float radius;
    private float damage;
    private float speed;
    private boolean areaOfEffect;
    private Texture texture;
    private Node currentPosition;

   // private Enemy enemy;

    //private final Node targetPosition = enemy.getPos();


    public Projectile(float radius, float damage, float speed, boolean areaOfEffect, Texture texture){
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.areaOfEffect = areaOfEffect;
        this.texture=texture;
    }

    public void setPosition(Node position){
        this.currentPosition = position;
    }

    /*
    public void move(){
        Node newPosition;
        float newX = targetPosition.getX() + speed;
        float newY = targetPosition.getY() + speed;
        newPosition = new Node(newX, newY);
        setPosition(newPosition);
    }
*/
    public void hitEnemy(Enemy enemy){
        enemy.hurt(damage);
    }



}
