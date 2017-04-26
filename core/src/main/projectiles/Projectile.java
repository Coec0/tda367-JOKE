package projectiles;

import com.badlogic.gdx.graphics.Texture;
import enemies.Enemy;
import models.ProjectileModel;
import utilities.Node;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;
import utilities.UpdateObserver;
import views.ProjectileView;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public abstract class Projectile {

    private float damage;
    private float speed;
    private boolean areaOfEffect;
    private Node direction;
    private Node position;
    private SpriteAdapter sprite;


    public Projectile(float damage, float speed, boolean areaOfEffect, Node direction, Node position){
        this.damage = damage;
        this.speed = speed;
        this.areaOfEffect = areaOfEffect;
        this.direction = createVector(direction);
        //System.out.println(this.direction.getX() + " " + this.direction.getY());
        this.position = position;
        this.sprite = new SpriteAdapter((int)position.getX(), (int)position.getY());
    }

    public void setPosition(Node position){
        this.sprite.setPosition(position.getX(), position.getY());
    }

    public Node getNewPosition(){
        Node newPosition;
        float newX = position.getX()+ (direction.getX() * speed);
        float newY = position.getY() + (direction.getY() * speed);
        System.out.println(newX + "  " + newY);
        newPosition = new Node(newX, newY);
        return newPosition;
    }
    public Node getPosition(){
        return position;
    }

    public Node createVector(Node other){
        float x = other.getX();
        float y = other.getY();

        float pyt = (float) Math.sqrt(x*x + y*y);

        return new Node(x/pyt,y/pyt);
    }

    public SpriteAdapter getSpriteAdapter(){
        return sprite;
    }



}
