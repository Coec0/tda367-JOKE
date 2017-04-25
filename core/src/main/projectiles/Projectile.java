package projectiles;

import com.badlogic.gdx.graphics.Texture;
import enemies.Enemy;
import utilities.Node;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public abstract class Projectile {

    private float damage;
    private float speed;
    private boolean areaOfEffect;
    private Texture texture;
    private Node direction;
    private Node position;


    public Projectile(float damage, float speed, boolean areaOfEffect, Texture texture, Node direction, Node position){
        this.damage = damage;
        this.speed = speed;
        this.areaOfEffect = areaOfEffect;
        this.texture=texture;
        this.direction = direction;
        this.position = position;
    }

    public void setPosition(Node position){
        this.position = position;
    }


    public void move(){
        Node newPosition;
        float newX = position.getX() + speed;
        float newY = position.getY() + speed;
        newPosition = new Node(newX, newY);
        setPosition(newPosition);
    }

    public void hitEnemy(Enemy enemy){
        enemy.hurt(damage);
    }



}
