package controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import buildings.Building;
import buildings.towers.Tower;
import buildings.towers.TowerFactory;
import models.AlienModel;
import models.BuildingModel;
import utilities.Node;
import views.BuildingView;

public class BuildingController extends ClickListener implements InputProcessor {

	BuildingView BView;
    BuildingModel BModel;
    AlienModel AModeL;
    Viewport WP;
    Tower onMouse;
    Building highlighted;
    

    public BuildingController(BuildingModel BModel, AlienModel AModel, BuildingView BView, Viewport WP){
        this.BView = BView;
        this.BModel = BModel;
        this.AModeL = AModel;
        this.WP = WP;
        
        BModel.addObserver(BView);
        
        BModel.createWhiteHouse(1280,360);
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
		 if(event.getListenerActor().getName().equals("soldier")){
			onMouse = TowerFactory.createSoldier((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		 }
		 if(event.getListenerActor().getName().equals("tank")){
				onMouse = TowerFactory.createTank((int)x, (int)y); // x and y never used
				BView.placeTexture(onMouse);
			 }
		if(event.getListenerActor().getName().equals("sniper")){
			onMouse = TowerFactory.createSniper((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		}
		if(event.getListenerActor().getName().equals("ranger")){
			onMouse = TowerFactory.createRanger((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		}
		if(event.getListenerActor().getName().equals("marine")){
			onMouse = TowerFactory.createMarine((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		}
		if(event.getListenerActor().getName().equals("howitzer")){
			onMouse = TowerFactory.createHowitzer((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		}
		if(event.getListenerActor().getName().equals("engineer")){
			onMouse = TowerFactory.createEngineer((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		}
		if(event.getListenerActor().getName().equals("bazooka")){
			onMouse = TowerFactory.createBazooka((int)x, (int)y); // x and y never used
			BView.placeTexture(onMouse);
		}
    }
    
    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector3 v = new Vector3 (screenX , screenY, 0);
		WP.unproject(v);
    	if(onMouse != null){
    		BModel.addTower(onMouse, (int)v.x,(int) v.y);
    		onMouse = null;
    		BView.removePlaceTexture();
    	}else{
    		Building clicked = getClickedBuilding((int)v.x,(int) v.y);
    		if(clicked != null){
    			buildingClicked(clicked);
    		}else{
    			groundClicked();
    		}
    	}
		return false;
	}
    
    private void buildingClicked(Building building){
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
    
    
    public Building getClickedBuilding(int x, int y){
    
    	for(Building building : BModel.getAllBuildings()){
    		if(isInRadius(x,y,building)){
    			return building;
    		}
    	}
    	return null;
    	
    }
    
    public boolean isInRadius(int x, int y, Building building){
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
