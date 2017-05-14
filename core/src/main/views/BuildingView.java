package views;

import buildings.towers.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;

import buildings.BoardObject;
import buildings.Wall;
import buildings.WhiteHouse;
import observers.BuildingObserver;
import utilities.SpriteAdapter;

public class BuildingView extends View<BoardObject> implements BuildingObserver{
    private Texture soldier,tank, whitehouse, ranger, sniper,wall, aliennerfer, towerbooster, riotshield, minutemen;
    private SpriteAdapter onMouse, sizeCircle, radiusCircle;


    public BuildingView(){
        soldier = new Texture("solider512.png");
        tank = new Texture("tank512.png");
        whitehouse = new Texture("sexywhitehouse.png");
        ranger = new Texture("ranger256.png");
        wall = new Texture("TrumpWall.png");
        sniper = new Texture("sniper512.png");
        aliennerfer = new Texture("soldier.png");
        towerbooster = new Texture("soldier.png");
        riotshield = new Texture("riotshield256.png");
        minutemen = new Texture("solider512.png");
        //all same texture for testing
    }

    public void placeTexture(BoardObject building){
    	onMouse = new SpriteAdapter(selectTexture(building));
    	onMouse.setSize(onMouse.getWidth()/3, onMouse.getHeight()/3);
    	onMouse.setAlpha(0.5f);
    	
    	radiusCircle = getRoundSpriteAdapter(building.getSpriteAdapter().getX(), building.getSpriteAdapter().getY(), building.getRadius(), false, Color.GOLD);
    	
    	sizeCircle = getRoundSpriteAdapter(building.getSpriteAdapter().getX(), building.getSpriteAdapter().getY(), building.getSize(), true, Color.RED);
    	sizeCircle.setAlpha(0.5f);
    	addToView(onMouse);
    	showBoardObjectOverlay(true);
    }
    
    private void showBoardObjectOverlay(boolean show){
    	if(show){
    		addToView(sizeCircle);
    		addToView(radiusCircle);
    	} else {
    		removeFromView(sizeCircle);
			removeFromView(radiusCircle);
    	}
    }
    
    private void updateBoardObjectOverlay(BoardObject boardObject){
    	if(radiusCircle == null || sizeCircle == null)
    		return;
    	radiusCircle.setSize(boardObject.getRadius()*2, boardObject.getRadius()*2);
		radiusCircle.setPosition(boardObject.getPos().getX(), boardObject.getPos().getY());
		
		sizeCircle.setSize(boardObject.getSize()*2, boardObject.getSize()*2);
		sizeCircle.setPosition(boardObject.getPos().getX(), boardObject.getPos().getY());
		
    }
    
    public void movePlaceTexture(float x, float y){
    	onMouse.setPosition(x, y);
    	sizeCircle.setPosition(x, y);
    	radiusCircle.setPosition(x, y);
    }
    
    public void removePlaceTexture(){
    	removeFromView(onMouse);
    }
    
    @Override
	protected Texture selectTexture(BoardObject boardobject) {
		
		if(boardobject instanceof WhiteHouse)
			return whitehouse;
		if(boardobject instanceof Soldier)
			return soldier;
		if(boardobject instanceof Tank)
			return tank;
		if(boardobject instanceof Ranger)
		    return ranger;
		if(boardobject instanceof Sniper)
		    return sniper;
		if(boardobject instanceof Wall)
			return wall;
		if(boardobject instanceof AlienNerfer)
			return aliennerfer;
		if(boardobject instanceof TowerBooster)
			return towerbooster;
		if(boardobject instanceof RiotShield)
			return riotshield;
		if (boardobject instanceof Minutemen){
			return minutemen;
		}
		return null;
		
	}
	
	private SpriteAdapter getRoundSpriteAdapter(float x, float y, float rads, boolean filled, Color color){
		Texture texture;
		if(!filled)
			texture = new Texture("shapes/ring/ring1024.png");
		else
			texture = new Texture("shapes/circle/circle256.png");
		SpriteAdapter sa = new SpriteAdapter(texture);
		sa.setSize(rads*2, rads*2);
		sa.setPosition(x, y);
		sa.setColor(color);
		return sa;
	}
	
	@Override
	public void actOnBuildingChange(BoardObject boardObject, boolean remove, boolean clickedOn) {
		if( !clickedOn && !remove){ //When placed on ground
			addToView(boardObject.getSpriteAdapter(), boardObject, 0.3f);
			removePlaceTexture();
			showBoardObjectOverlay(false);
		} else if(!clickedOn && remove){ //When removed
			removeFromView(boardObject.getSpriteAdapter());
			showBoardObjectOverlay(false);
		} else if(clickedOn && remove){ //When deselected
			showBoardObjectOverlay(false);
		} else if(clickedOn && !remove){ //When clicked on
			updateBoardObjectOverlay(boardObject);
			showBoardObjectOverlay(true);
		}
	}
}
