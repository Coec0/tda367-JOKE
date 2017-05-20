package controllers;

import javax.swing.plaf.BorderUIResource;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import buildings.BoardObject;
import buildings.Wall;
import towers.BOPrototypes;
import towers.Tower;
import towers.TowerFactory;
import towers.targetmethods.TargetClosest;
import towers.targetmethods.TargetFirst;
import towers.targetmethods.TargetFurthest;
import towers.targetmethods.TargetLast;
import towers.targetmethods.TargetStrongest;
import towers.targetmethods.TargetWeakest;
import models.AlienModel;
import models.BuildingModel;
import path.PathFinder;
import utilities.Node;
import views.BuildingView;

public class BuildingController extends ClickListener implements InputProcessor {

	private BuildingView BView;
    private BuildingModel BModel;
    private AlienModel AModeL;
    private Viewport WP;
//    private BoardObject onMouse;
//    private BoardObject highlighted;
    private BOPrototypes prototypes;
   
    

    public BuildingController(BuildingModel BModel, AlienModel AModel, BuildingView BView, Viewport WP,PathFinder finder, BOPrototypes prototypes){
        this.BView = BView;
        this.BModel = BModel;
        this.AModeL = AModel;
        this.WP = WP;
        this.prototypes = prototypes;
        BModel.addObserver(BView);
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
    	if(event.getListenerActor().getName().equals("remove")){
    		BModel.trash();
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
			BModel.clickedBuilding(onMouse);
		}
			
    }

    private void checkIfUpgrade(InputEvent event){
    	if (!(BModel.getHighlighted() instanceof Tower)){
    		return;
		}

		Tower tower = (Tower)BModel.getHighlighted();
    	if(event.getListenerActor().getName().equals("uDamage")){
			BModel.upgradeTowerDamage(tower);
		}

		else if (event.getListenerActor().getName().equals("uCooldown")){
			BModel.upgradeTowerCooldown(tower);
		}
		else if (event.getListenerActor().getName().equals("uRadius")){
			BModel.upgradeTowerRadius(tower);
		}
	}
    
    private void checkIfNewTarget(InputEvent event) {
    	if(!(BModel.getHighlighted() instanceof Tower))
    		return; //Abort if not tower
    	
    	Tower tower = (Tower)BModel.getHighlighted();
    	
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
		if(BModel.getHighlighted() != null && (BModel.getHighlighted() instanceof Wall) || v.x >= WP.getWorldWidth()) //Makes sure you cant click on ui
			return false;
		
    	if(BModel.getHighlighted() != null && !BModel.getHighlighted().isActive() && BModel.isFreeSpace((int)v.x,(int) v.y, BModel.getHighlighted())){
    		BModel.purchaseHighlightedObject((int)v.x,(int) v.y);
    	}else {
    		BoardObject clicked = getClickedBuilding((int)v.x,(int) v.y);
    		if(clicked != null && !(clicked instanceof Wall)){
    			BModel.clickedBuilding(clicked);
    		}else{
    			BModel.deselectHighlighted();
    			//System.out.print("X: " + screenX + " Y: " + screenY);
    		}
    	}
		return false;
	}
    
    public BoardObject getClickedBuilding(int x, int y){
    
    	for(BoardObject building : BModel.getAllBoardObjects()){
    		if(isInRadius(x,y,building)){
    			return building;
    		}
    	}
    	return null;
    	
    }
    
    public boolean isInRadius(int x, int y, BoardObject building){
    	Node node = new Node(x,y);
    	return node.getDistanceTo(building.getPos()) <= building.getSize();
    }

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(BModel.getHighlighted() != null && !BModel.getHighlighted().isActive()){
			Vector3 v = new Vector3 (screenX , screenY, 0);
    		WP.unproject(v);
			BView.movePlaceTexture(v.x, v.y);
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
