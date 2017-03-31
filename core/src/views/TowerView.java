package views;

import com.badlogic.gdx.graphics.Texture;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;

public class TowerView {
    private Texture texture;
    private SpriteCollector SC = SpriteCollector.getInstance();


    public TowerView(){
        texture = new Texture("soldier.png");
    }

    public void removeFromView(SpriteAdapter sprite){
        SC.removeSprite(sprite);
    }

    public void addToView(SpriteAdapter sprite, int x, int y){
        sprite.setPosition(x, y);
        addToView(sprite);
    }

    public void addToView(SpriteAdapter sprite){
        if(sprite.getTexture() == null){
            sprite.setTexture(texture);
            sprite.setSize(sprite.getWidth()/3, sprite.getHeight()/3);
        }
        SC.addSprite(sprite);
    }

}
