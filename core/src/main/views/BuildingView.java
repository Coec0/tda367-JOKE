package views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import buildings.BoardObject;
import buildings.Building;
import buildings.Wall;
import buildings.WhiteHouse;
import buildings.towers.AlienNerfer;
import buildings.towers.Bazooka;
import buildings.towers.Engineer;
import buildings.towers.Howitzer;
import buildings.towers.Marine;
import buildings.towers.Ranger;
import buildings.towers.Sniper;
import buildings.towers.Soldier;
import buildings.towers.Tank;
import buildings.towers.TowerBooster;
import observers.BuildingObserver;
import utilities.SpriteAdapter;

public class BuildingView extends View<BoardObject> implements BuildingObserver{
    private Texture soldier,tank, whitehouse, howitzer, ranger, sniper, bazooka, engineer, marine,wall, aliennerfer, towerbooster;
    private SpriteAdapter onMouse, sizeCircle, radiusCircle;


    public BuildingView(){
        soldier = new Texture("soldier.png");
        tank = new Texture("tank.png");
        whitehouse = new Texture("sexywhitehouse.png");
        howitzer = new Texture("soldier.png");
        ranger = new Texture("ranger.png");
        marine = new Texture("marine.png");
        wall = new Texture("TrumpWall.png");
        sniper = new Texture("soldier.png");
        bazooka = new Texture("soldier.png");
        engineer = new Texture("soldier.png");
        aliennerfer = new Texture("soldier.png");
        towerbooster = new Texture("soldier.png");
        //all same texture for testing
    }

    public void placeTexture(BoardObject building){
    	onMouse = new SpriteAdapter(selectTexture(building));
    	onMouse.setSize(onMouse.getWidth()/3, onMouse.getHeight()/3);
    	onMouse.setAlpha(0.5f);
    	
    	//Temp removed
    	//radiusCircle = getRadiusSpriteAdapter(building.getSpriteAdapter().getX(), building.getSpriteAdapter().getY(), building.getRadius());
    	//addToView(radiusCircle);
    	sizeCircle = getSizeSpriteAdapter(building.getSpriteAdapter().getX(), building.getSpriteAdapter().getY(), building.getSize());
    	sizeCircle.setAlpha(0.5f);
    	addToView(sizeCircle);
    	
    	addToView(onMouse);
    }
    
    public void movePlaceTexture(float x, float y){
    	onMouse.setPosition(x, y);
    	sizeCircle.setPosition(x, y);
    	//radiusCircle.setPosition(x, y);
    }
    
    public void removePlaceTexture(){
    	removeFromView(onMouse);
    }
    
    @Override
	protected Texture selectTexture(BoardObject building) {
		
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
		if(building instanceof AlienNerfer)
			return aliennerfer;
		if(building instanceof TowerBooster)
			return towerbooster;
		return null;
		
	}
	
	private SpriteAdapter getRadiusSpriteAdapter(float x, float y, float radius){
		Texture texture = new Texture("shapes/ring/ring1024.png");
		SpriteAdapter sa = new SpriteAdapter(texture);
		sa.setSize(radius*2, radius*2);
		sa.setPosition(x, y);
		sa.setColor(Color.GOLD);
		return sa;
	}
	
	private SpriteAdapter getSizeSpriteAdapter(float x, float y, float size){
		Texture texture = new Texture("shapes/circle/circle256.png");
		SpriteAdapter sa = new SpriteAdapter(texture);
		sa.setSize(size*2, size*2);
		sa.setPosition(x, y);
		sa.setColor(Color.RED);
		return sa;
	}

	@Override
	public void actOnBuildingChange(BoardObject building, boolean remove, boolean clickedOn) {
		if(!remove && !clickedOn){
			addToView(building.getSpriteAdapter(), building, 0.3f);
			removePlaceTexture();
		} else if(!clickedOn && remove){
			removeFromView(building.getSpriteAdapter());	
		} else if(clickedOn && remove){
			removePlaceTexture();
		}
	}
}
