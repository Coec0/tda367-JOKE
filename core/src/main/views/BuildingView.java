package views;

import com.badlogic.gdx.graphics.Texture;

import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Soldier;
import utilities.BuildingObserver;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;

public class BuildingView implements BuildingObserver{
    private Texture soldier, whitehouse;
    private SpriteCollector SC = SpriteCollector.getInstance();


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
