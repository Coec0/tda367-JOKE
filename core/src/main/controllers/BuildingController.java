package controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import buildings.BoardObject;
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

	BuildingView BView;
    BuildingModel BModel;
    AlienModel AModeL;
    Viewport WP;
    BoardObject onMouse;
    BoardObject highlighted;
    BOPrototypes prototypes;
   
    

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
    		if(onMouse!=null){
    			BModel.deselect(onMouse);
    			onMouse = null;	
    		} else {
    			BModel.sellBoardObject(highlighted);
    		}
    	}
    	
    	checkIfNewTarget(event);
    	checkIfUpgrade(event);

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
		if(event.getListenerActor().getName().equals("aliennerfer")){
			onMouse = TowerFactory.createNetGunner(prototypes, (int)x, (int)y); // x and y never used
		}

		if(event.getListenerActor().getName().equals("riotshield")){
			onMouse = TowerFactory.createRiotShield(prototypes, (int)x, (int)y); // x and y never used
		}

		if(onMouse !=null){
			BView.placeTexture(onMouse);
			BModel.clickedBuilding(onMouse);
		}
			
    }

    private void checkIfUpgrade(InputEvent event){
    	if (!(highlighted instanceof Tower)){
    		return;
		}

		Tower tower = (Tower)highlighted;
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
    	if(!(highlighted instanceof Tower))
    		return; //Abort if not tower
    	
    	Tower tower = (Tower)highlighted;
    	
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
		if(v.x >= WP.getWorldWidth()) //Makes sure you cant click on ui
			return false;
		
    	if(onMouse != null && BModel.isFreeSpace((int)v.x,(int) v.y, onMouse)){
    		if(onMouse.getCost()<= BModel.getWhiteHouses().first().getMoney()){
    			BModel.addBoardObject(onMouse, (int)v.x,(int) v.y);
    			onMouse = null;
    		}
    	}else if(onMouse == null){
    		BoardObject clicked = getClickedBuilding((int)v.x,(int) v.y);
    		if(clicked != null){
    			buildingClicked(clicked);
    		}else{
    			groundClicked();
    			System.out.print("X: " + screenX + " Y: " + screenY);
    		}
    	}
		return false;
	}
    

	
	
    private void buildingClicked(BoardObject building){
    	System.out.println("BuildingCont: Tower clicked"); 
		highlighted = building;
		BModel.clickedBuilding(building);
		
    }
    
    private void groundClicked(){
    	if(highlighted != null)
    		BModel.deselect(highlighted);
    	highlighted = null;
		System.out.println("BuildingCont: Ground Clicked");
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
		if(onMouse != null){
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
