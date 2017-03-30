package towers;

import enemies.Enemy;
import utilities.Node;

import java.awt.*;

public abstract class Tower {
    private Node pos;
    private int radius;
    private String name;
    private int cost;
    private int damage;


    public Tower(Node pos, int radius, String name, int cost, int damage){
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

    public int getRadius(){
        return this.radius;
    }

    public int getCost(){
        return this.cost;
    }

    public int getDamage(){
        return this.damage;
    }

    public void shoot(Enemy enemy){

    }

    public void target(Enemy enemy){

    }



}
