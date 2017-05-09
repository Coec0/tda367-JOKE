package interfaces;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import utilities.Node;
import utilities.Radar;
import utilities.SpriteAdapter;

public interface IProjectile {

    //getters
    float getSpeed();
    float getDamage();
    int getHealth();
    float getRadius();
    Node getPosition();
    SpriteAdapter getSpriteAdapter();
    Node getNewPosition();

    //setters
    void setSpritePosition(Node position);
    void setNewPosition();
    void setPosition(Node position);

    //do
    void reduceHealth();
    void damage(Radar radar, Array<Enemy> enemies);
}