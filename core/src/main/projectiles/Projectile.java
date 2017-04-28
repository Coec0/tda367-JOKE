package projectiles;

import com.badlogic.gdx.graphics.Texture;
import enemies.Enemy;
import models.ProjectileModel;
import utilities.Node;
import utilities.SpriteAdapter;
import utilities.DrawablesCollector;
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
    private float radius;


    public Projectile(float damage, float speed, boolean areaOfEffect, float radius, Node direction, Node position){
        this.damage = damage;
        this.radius = radius;
        this.speed = speed;
        this.areaOfEffect = areaOfEffect;
        this.direction = direction;
        //System.out.println(this.direction.getX() + " " + this.direction.getY());
        this.position = position;
        this.sprite = new SpriteAdapter((int)position.getX(), (int)position.getY());
        this.direction = createDirectionVector();
    }

    public void setSpritePosition(Node position){
        this.sprite.setPosition(position.getX(), position.getY());
    }

    public void setPosition(Node position){
        this.position = position;
    }
    
    public float getRadius(){
    	return radius;
    }
    public Node getNewPosition(){
        Node newPosition;
        float newX = position.getX()+ (direction.getX() * speed);
        float newY = position.getY() + (direction.getY() * speed);
        newPosition = new Node(newX, newY);
        setPosition(newPosition);
        return newPosition;
    }
    public Node getPosition(){
        return position;
    }
    public float getDamage(){
        return damage;
    }

    public Node createDirectionVector(){
        float x = position.getDeltaX(direction);
        float y = position.getDeltaY(direction);
        float pyt = (float) Math.sqrt(x*x + y*y);

        return new Node(x/pyt,y/pyt);
    }

    public SpriteAdapter getSpriteAdapter(){
        return sprite;
    }


}
