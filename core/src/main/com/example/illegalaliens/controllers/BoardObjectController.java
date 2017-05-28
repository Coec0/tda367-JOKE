package com.example.illegalaliens.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.models.BoardObjectModel;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.Wall;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.models.boardobjects.towers.TowerFactory;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetClosest;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetFirst;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetFurthest;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetLast;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetStrongest;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetWeakest;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.views.BoardObjectView;

public class BoardObjectController extends ClickListener implements InputProcessor {

	private BoardObjectView BOView;
    private BoardObjectModel BOModel;
    private Viewport WP;
//    private BoardObject onMouse;
//    private BoardObject highlighted;
    private BOPrototypes prototypes;
   
    

    public BoardObjectController(BoardObjectModel BOModel, BoardObjectView BOView, Viewport WP, BOPrototypes prototypes){
        this.BOView = BOView;
        this.BOModel = BOModel;
        this.WP = WP;
        this.prototypes = prototypes;
        BOModel.addObserver(BOView);
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
    	if(event.getListenerActor().getName().equals("remove")){
    		BOModel.trash();
    	}
    	
    	checkIfNewTarget(event);
    	checkIfUpgrade(event);

    	BoardObject onMouse = null;
		if(event.getListenerActor().getName().equals("soldier")){
			onMouse = TowerFactory.createSoldier(prototypes, (int)x, (int)y); // x and y never used
		}
		if(event.getListenerActor().getName().equals("tank")){
			onMouse = TowerFactory.createTank(prototypes, (int)x, (int)y); // x and y never used
		}
		if(event.getListenerActor().getName().equals("sniper")){
			onMouse = TowerFactory.createSniper(prototypes, (int)x, (int)y); // x and y never used
		}
		if(event.getListenerActor().getName().equals("ranger")){
			onMouse = TowerFactory.createRanger(prototypes, (int)x, (int)y); // x and y never used
		}
		if(event.getListenerActor().getName().equals("netgunner")){
			onMouse = TowerFactory.createNetGunner(prototypes, (int)x, (int)y); // x and y never used
		}
		if(event.getListenerActor().getName().equals("riotshield")){
			onMouse = TowerFactory.createRiotShield(prototypes, (int)x, (int)y); // x and y never used
		}

		if(onMouse !=null){
			//BView.placeTexture(onMouse);
			BOModel.clickedBuilding(onMouse);
		}
			
    }

    private void checkIfUpgrade(InputEvent event){
    	if (!(BOModel.getHighlighted() instanceof Tower)){
    		return;
		}

		Tower tower = (Tower)BOModel.getHighlighted();
    	if(event.getListenerActor().getName().equals("uDamage")){
			BOModel.upgradeTowerDamage(tower);
		}

		else if (event.getListenerActor().getName().equals("uCooldown")){
			BOModel.upgradeTowerCooldown(tower);
		}
		else if (event.getListenerActor().getName().equals("uRadius")){
			BOModel.upgradeTowerRadius(tower);
		}
	}
    
    private void checkIfNewTarget(InputEvent event) {
    	if(!(BOModel.getHighlighted() instanceof Tower))
    		return; //Abort if not tower
    	
    	Tower tower = (Tower)BOModel.getHighlighted();
    	
    	if(event.getListenerActor().getName().equals("tFirst")){
    		tower.setTargetState(new TargetFirst());
    	} else if(event.getListenerActor().getName().equals("tLast")){
    		tower.setTargetState(new TargetLast());
    	} else if(event.getListenerActor().getName().equals("tStrong")){
    		tower.setTargetState(new TargetStrongest());
    	} else if(event.getListenerActor().getName().equals("tWeak")){
    		tower.setTargetState(new TargetWeakest());
    	} else if(event.getListenerActor().getName().equals("tClose")){
    		tower.setTargetState(new TargetClosest());
    	} else if(event.getListenerActor().getName().equals("tFar")){
    		tower.setTargetState(new TargetFurthest());
    	}
		
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector3 v = new Vector3 (screenX , screenY, 0);
		WP.unproject(v);
		if(BOModel.getHighlighted() != null && (BOModel.getHighlighted() instanceof Wall) || v.x >= WP.getWorldWidth()) //Makes sure you cant click on ui
			return false;
		
    	if(BOModel.getHighlighted() != null && !BOModel.getHighlighted().isActive() && BOModel.isFreeSpace((int)v.x,(int) v.y, BOModel.getHighlighted())){
    		BOModel.purchaseHighlightedObject((int)v.x,(int) v.y);
    	}else if(BOModel.getHighlighted() == null || BOModel.getHighlighted().isActive()){
    		BoardObject clicked = getClickedBoardObject((int)v.x,(int) v.y);
    		if(clicked != null && !(clicked instanceof Wall)){
    			BOModel.clickedBuilding(clicked);
    		}else{
    			BOModel.deselectHighlighted();
    			//System.out.print("X: " + screenX + " Y: " + screenY);
    		}
    	}
		return false;
	}
    
    private BoardObject getClickedBoardObject(int x, int y){
    
    	for(BoardObject boardObject : BOModel.getAllBoardObjects()){
    		if(isInRadius(x,y,boardObject)){
    			return boardObject;
    		}
    	}
    	return null;
    	
    }
    
    private boolean isInRadius(int x, int y, BoardObject boardObject){
    	Node node = new Node(x,y);
    	return node.getDistanceTo(boardObject.getPos()) <= boardObject.getSize();
    }

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(BOModel.getHighlighted() != null && !BOModel.getHighlighted().isActive()){
			Vector3 v = new Vector3 (screenX , screenY, 0);
    		WP.unproject(v);
    		BOView.movePlaceTexture(v.x, v.y);
		}
		return false;
	}
    
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
