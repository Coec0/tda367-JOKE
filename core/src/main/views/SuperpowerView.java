package views;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerView {
    private Texture wall;

    private DrawablesCollector DC = DrawablesCollector.getInstance();

    public SuperpowerView(){

    }

    public void removeFromView(SpriteAdapter sprite){
        DC.removeSprite(sprite);
    }

    public void addToView(SpriteAdapter sprite, int x, int y){
        sprite.setPosition(x, y);
        addToView(sprite);
    }

    public void addToView(SpriteAdapter sprite){
        DC.addSprite(sprite);
    }

}
