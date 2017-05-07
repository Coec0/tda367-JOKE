package views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

import buildings.Building;
import buildings.Wall;
import buildings.WhiteHouse;
import buildings.towers.Bazooka;
import buildings.towers.Engineer;
import buildings.towers.Howitzer;
import buildings.towers.Marine;
import buildings.towers.Ranger;
import buildings.towers.Sniper;
import buildings.towers.Soldier;
import buildings.towers.Tank;
import observers.BuildingObserver;
import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

public class BuildingView implements BuildingObserver{
    private Texture soldier,tank, whitehouse, howitzer, ranger, sniper, bazooka, engineer, marine,wall;
    private DrawablesCollector SC = DrawablesCollector.getInstance();
    private SpriteAdapter onMouse, sizeCircle;


    public BuildingView(){
        soldier = new Texture("soldier.png");
        tank = new Texture("tank.png");
        whitehouse = new Texture("sexywhitehouse.png");
        //howitzer = new Texture("");
        ranger = new Texture("ranger.png");
        marine = new Texture("marine.png");
        wall = new Texture("TrumpWall.png");
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
    	
    	
    	sizeCircle = getSizeSpriteAdapter(building.getSpriteAdapter().getX(), building.getSpriteAdapter().getY(), building.getSize(), Color.RED);
    	sizeCircle.setAlpha(0.5f);
    	addToView(sizeCircle);
    	addToView(onMouse);
    }
    
    public void movePlaceTexture(float x, float y){
    	onMouse.setPosition(x, y);
    	sizeCircle.setPosition(x, y);
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
		if(building instanceof Wall)
			return wall;
		return null;
		
	}
	
	private SpriteAdapter getSizeSpriteAdapter(float x, float y, float radius, Color color){
		float width = radius*12;
		float height = width;
		int radiusI = (int)radius;
		Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
		pixmap.setColor(color);
		int xI = (int)((x+width)/2-x/2); //Seems to get it close enough
		int yI = (int)((y+height)/2-y/2);
		pixmap.fillCircle(xI, yI, radiusI);
		return new SpriteAdapter(new Texture(pixmap));
	}

	@Override
	public void actOnBuildingChange(Building building, boolean remove, boolean clickedOn) {
		if(!remove && !clickedOn){
			addToView(building);
			removePlaceTexture();
		} else if(!clickedOn && remove){
			removeFromView(building.getSpriteAdapter());	
		} else if(clickedOn && remove){
			removePlaceTexture();
		}
	}
}
