package views;

import buildings.towers.*;
import com.badlogic.gdx.graphics.Texture;

import buildings.Building;
import buildings.WhiteHouse;
import utilities.BuildingObserver;
import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

public class BuildingView implements BuildingObserver{
    private Texture soldier,tank, whitehouse, howitzer, ranger, sniper, bazooka, engineer, marine;
    private DrawablesCollector SC = DrawablesCollector.getInstance();
    private SpriteAdapter onMouse;


    public BuildingView(){
        soldier = new Texture("soldier.png");
        tank = new Texture("tank.png");
        whitehouse = new Texture("sexywhitehouse.png");
        //howitzer = new Texture("");
        ranger = new Texture("ranger.png");
        marine = new Texture("marine.png");
        //sniper = new Texture("");
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
		if(building instanceof Tank)
			return tank;
		if(building instanceof Marine)
		    return marine;
		if(building instanceof Ranger)
		    return ranger;
		if(building instanceof Bazooka)
		    return bazooka;
		if(building instanceof Engineer)
		    return engineer;
		if(building instanceof Sniper)
		    return sniper;
		if(building instanceof Howitzer)
		    return howitzer;
		return null;
		
	}

	@Override
	public void actOnBuildingChange(Building building, boolean remove, boolean clickedOn) {
		if(!remove && !clickedOn){
			addToView(building);
			removePlaceTexture();
		} else if(!clickedOn)
			removeFromView(building.getSpriteAdapter());	
		 else if(remove && clickedOn){
			removeFromView(building.getSpriteAdapter()); //Not sure if necesary
			removePlaceTexture();
		}
	}
}
