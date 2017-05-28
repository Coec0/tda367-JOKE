package com.example.illegalaliens.models.projectiles;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.IAAdapter;

public interface IProjectile {

    //getters
    float getSpeed();
    float getDamage();
    int getHealth();
    float getRadius();
    Node getPosition();
    IAAdapter getSpriteAdapter();
    Node getNewPosition();

    //setters
    void setSpritePosition(Node position);
    void setNewPosition();
    void setPosition(Node position);

    //do
    void reduceHealth();
    void damage(Radar radar, Array<Enemy> enemies);
}