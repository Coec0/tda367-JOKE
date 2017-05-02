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
        this.speed = 5;
        this.areaOfEffect = areaOfEffect;
        this.direction = direction;
        //System.out.println(this.direction.getX() + " " + this.direction.getY());
        this.position = position;
        this.sprite = new SpriteAdapter((int)position.getX(), (int)position.getY());
        this.direction = createDirectionVector();
    }

    public void setSpritePosition(Node position){
        sprite.setPosition(position.getX(), position.getY());
    }
    
    public void setNewPosition(){
    	Node newPos = getNewPosition();
    	setSpritePosition(newPos);
    	setPosition(newPos);
    }

    public void setPosition(Node position){
        this.position = position;
    }
    
    public float getRadius(){
    	return radius;
    }
    private Node getNewPosition(){
        float newX = position.getX() + (direction.getX() * 5);
        float newY = position.getY() + (direction.getY() * 5);
        Node newPosition = new Node(newX, newY);
        return newPosition;
    }
    public Node getPosition(){
        return position;
    }
    public float getDamage(){
        return damage;
    }

    private Node createDirectionVector(){
        float x = position.getDeltaX(direction);
        float y = position.getDeltaY(direction);
        float pyt = (float) Math.sqrt(x*x + y*y);
        return new Node(x/pyt,y/pyt);
    }

    public SpriteAdapter getSpriteAdapter(){
        return sprite;
    }


}
