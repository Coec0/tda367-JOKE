package towers;

import enemies.Enemy;
import utilities.Node;

import java.awt.*;

public abstract class Tower {
    private Node pos;
    private float radius;
    private String name;
    private int cost;
    private float damage;


    public Tower(Node pos, float radius, String name, int cost, float damage){
        this.pos = pos;
        this.radius = radius;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
    }

    public void setPos(Node pos){
        this.pos = pos;
    }

    public Node getPos(){
        return this.pos;
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
