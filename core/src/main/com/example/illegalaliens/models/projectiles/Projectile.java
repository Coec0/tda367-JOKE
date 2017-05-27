package com.example.illegalaliens.models.projectiles;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.SpriteAdapter;

/**
 * Created by Emil on 2017-04-04.
 */
public abstract class Projectile implements IProjectile {
    private Node direction;
    private Node position;
    private SpriteAdapter sprite;
    private float radius;
    private float speed;
    private float damage;
    private int health;

    public Projectile(int health, float damage, float speed,float radius, Node direction, Node position){
        this.radius = radius;
        this.speed = speed;
        this.direction = direction;
        //System.out.println(this.direction.getX() + " " + this.direction.getY());
        this.position = position;
        this.sprite = new SpriteAdapter((int)position.getX(), (int)position.getY());
        this.direction = createDirectionVector();
        this.damage = damage;
        this.health = health;
    }

    public Array<Enemy> scanEnemies(Radar radar, Node pos, float radius, Array<Enemy> allEnemies){
    	Array<Enemy> foundEnemies = new Array<Enemy>();
    	for(Enemy enemy : allEnemies){
    		if(radar.isNodeWithinRadius(pos, radius, enemy.getPos(), enemy.getRadius())){
    			foundEnemies.add(enemy);
    		}
    	}
    	return foundEnemies;
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

    public int getHealth(){
        return health;
    }

    public void reduceHealth(){
        health--;
    }
    public Node getNewPosition(){
        float newX = position.getX() + (direction.getX() * speed);
        float newY = position.getY() + (direction.getY() * speed);
        Node newPosition = new Node(newX, newY);
        return newPosition;
    }
    public Node getPosition(){
        return position;
    }

    public float getDamage(){
        return damage;
    }
    public float getSpeed(){
        return speed;
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
