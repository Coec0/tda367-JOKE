package com.example.illegalaliens.views;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.BoardObjectObserver;
import com.example.illegalaliens.models.boardobjects.Wall;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.buildings.RiotShield;
import com.example.illegalaliens.models.boardobjects.towers.*;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.IAAdapter;
import com.example.illegalaliens.views.textures.BuildingTextureHandler;
import com.example.illegalaliens.views.textures.TowerTextureHandler;

public class BoardObjectView extends View<BoardObject> implements BoardObjectObserver{
    public BoardObjectView(DrawablesCollector DC) {
		super(DC);
	}

	private IAAdapter onMouse, sizeCircle, radiusCircle;

    private void placeTexture(BoardObject boardObject){
    	onMouse = new IAAdapter(selectTexture(boardObject));
    	onMouse.setSize(onMouse.getWidth() * super.getScale(onMouse, boardObject.getSize()), onMouse.getHeight()*super.getScale(onMouse, boardObject.getSize()));
    	onMouse.setAlpha(0.5f);
    	
    	radiusCircle = getRoundSpriteAdapter(boardObject.getSpriteAdapter().getX(), boardObject.getSpriteAdapter().getY(), boardObject.getRadius(), false, Color.GOLD);
    	
    	sizeCircle = getRoundSpriteAdapter(boardObject.getSpriteAdapter().getX(), boardObject.getSpriteAdapter().getY(), boardObject.getSize(), true, Color.RED);
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
    
    private void removePlaceTexture(){
    	removeFromView(onMouse);
    }
    
    @Override
	protected Texture selectTexture(BoardObject boardobject) {
		
		if(boardobject instanceof WhiteHouse)
			return BuildingTextureHandler.getWhitehouseTexture();
		if(boardobject instanceof Soldier)
			return TowerTextureHandler.getSoldierTexture();
		if(boardobject instanceof Tank)
			return TowerTextureHandler.getTankTexture();
		if(boardobject instanceof Ranger)
		    return TowerTextureHandler.getRangerTexture();
		if(boardobject instanceof Sniper)
		    return TowerTextureHandler.getSniperTexture();
		if(boardobject instanceof Wall)
			return BuildingTextureHandler.getWallTexture();
		if(boardobject instanceof NetGunner)
			return TowerTextureHandler.getNetGunnerTexture();
		if(boardobject instanceof RiotShield)
			return TowerTextureHandler.getRiotshieldTexture();
		if (boardobject instanceof Minutemen){
			return TowerTextureHandler.getMinutemenTexture();
		}
		return null;
		
	}
	
	private IAAdapter getRoundSpriteAdapter(float x, float y, float rads, boolean filled, Color color){
		Texture texture;
		if(!filled)
			texture = new Texture("shapes/ring/ring1024.png");
		else
			texture = new Texture("shapes/circle/circle256.png");
		IAAdapter sa = new IAAdapter(texture);
		sa.setSize(rads*2, rads*2);
		sa.setPosition(x, y);
		sa.setColor(color);
		return sa;
	}
	
	@Override
	public void actOnBoardObjectChange(BoardObject boardObject, boolean remove, boolean clickedOn) {
		if( !clickedOn && !remove){ //When placed on ground
			addToView(boardObject.getSpriteAdapter(), boardObject, boardObject.getSize());
			removePlaceTexture();
			showBoardObjectOverlay(false);
		} else if(!clickedOn && remove){ //When removed
			removeFromView(boardObject.getSpriteAdapter());
			showBoardObjectOverlay(false);
		} else if(clickedOn && remove){ //When deselected
			showBoardObjectOverlay(false);
		} else if(clickedOn && !remove){ //When clicked on
			if(!boardObject.isActive()){
				placeTexture(boardObject);
			}
			updateBoardObjectOverlay(boardObject);
			showBoardObjectOverlay(true);
		}
	}
}
