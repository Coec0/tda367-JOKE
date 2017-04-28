package views;

import com.badlogic.gdx.graphics.Texture;

import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Soldier;
import utilities.BuildingObserver;
import utilities.SpriteAdapter;
import utilities.DrawablesCollector;

public class BuildingView implements BuildingObserver{
    private Texture soldier, whitehouse;
    private DrawablesCollector SC = DrawablesCollector.getInstance();
    private SpriteAdapter onMouse;


    public BuildingView(){
        soldier = new Texture("soldier.png");
        whitehouse = new Texture("sexywhitehouse.png");
    }

    public void removeFromView(SpriteAdapter sprite){
        SC.removeSprite(sprite);
    }

    public void addToView(SpriteAdapter sprite, int x, int y){
        sprite.setPosition(x, y);
        addToView(sprite);
    }

    public void addToView(SpriteAdapter sprite){
        SC.addSprite(sprite);
    }
    
    public void addToView(Building building){
    	SpriteAdapter sprite = building.getSpriteAdapter();
        if(sprite.getTexture() == null){
            sprite.setTexture(selectTexture(building));
            sprite.setSize(sprite.getWidth()/3, sprite.getHeight()/3);
        }
        addToView(sprite);
    }

    public void placeTexture(Building building){
    	onMouse = new SpriteAdapter(selectTexture(building));
    	onMouse.setSize(onMouse.getWidth()/3, onMouse.getHeight()/3);
    	onMouse.setAlpha(0.5f);
    	addToView(onMouse);
    }
    
    public void movePlaceTexture(float x, float y){
    	onMouse.setPosition(x, y);
    }
    
    public void removePlaceTexture(){
    	removeFromView(onMouse);
    }
    
	private Texture selectTexture(Building building) {
		
		if(building instanceof WhiteHouse)
			return whitehouse;
		if(building instanceof Soldier)
			return soldier;
		return null;
		
	}

	@Override
	public void actOnBuildingChange(Building building, boolean remove) {
		if(!remove)
			addToView(building);
		else
			removeFromView(building.getSpriteAdapter());
		
	}
	
}
