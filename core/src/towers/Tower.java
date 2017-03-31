package towers;

import enemies.Enemy;
import utilities.Node;
import utilities.SpriteAdapter;

import java.awt.*;

public abstract class Tower {
    private float radius;
    private String name;
    private int cost;
    private float damage;

    private SpriteAdapter pos;


    public Tower(SpriteAdapter pos, float radius, String name, int cost, float damage){
        this.pos = pos;
        this.radius = radius;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
    }
    public Node getPos() {
        return new Node(pos.getX(),pos.getY());
    }

    public void setPos(Node pos) {
        this.pos.setPosition(pos.getX(), pos.getY());
    }

    public SpriteAdapter getSpriteAdapter(){
        return pos;
    }

    public String getName(){
        return this.name;
    }

    public float getRadius(){
        return this.radius;
    }

    public int getCost(){
        return this.cost;
    }

    public float getDamage(){
        return this.damage;
    }

    public void shoot(Enemy enemy){

    }

    public void target(Enemy enemy){

    }



}
