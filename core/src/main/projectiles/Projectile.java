package projectiles;

import com.badlogic.gdx.graphics.Texture;
import enemies.Enemy;
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
    private ProjectileView PW;

    public Projectile(float damage, float speed, boolean areaOfEffect, Node direction, Node position){
        this.damage = damage;
        this.speed = speed;
        this.areaOfEffect = areaOfEffect;
        this.direction = direction.getAsNormalizedNode(position);
        this.position = position;
        this.sprite = new SpriteAdapter((int)position.getX(), (int)position.getY());
        PW = new ProjectileView();
        PW.addToView(this);
    }

    public void setPosition(Node position){
        this.sprite.setPosition(position.getX(), position.getY());
    }

    public Node getNewPosition(){
        Node newPosition;
        float newX = (position.getX()+ direction.getX()) * speed;
        float newY = (position.getY() + direction.getY()) * speed;
        newPosition = new Node(newX, newY);
        return newPosition;
    }
    public Node getPosition(){
        return position;
    }

    public SpriteAdapter getSpriteAdapter(){
        return sprite;
    }


}
