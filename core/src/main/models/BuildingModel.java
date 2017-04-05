package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Soldier;
import buildings.towers.Tank;
import buildings.towers.Tower;
import enemies.Alien;
import utilities.BuildingObserver;
import utilities.Node;
import utilities.SpriteAdapter;
import utilities.UpdateObserver;

public class BuildingModel implements UpdateObserver{
    Array<Tower> towers;
    Array<Building> buildings;
    Array<Alien> aliens;


    public BuildingModel(AlienModel AModel){
        towers = new Array<Tower>(false, 100);
        aliens = AModel.getAllAliens();
    }

    public void createSoldier(Node pos){
        towers.add(new Soldier((int)pos.getX(), Gdx.graphics.getHeight() -(int)pos.getY()));
        notifyObservers(towers.peek().getSpriteAdapter());
    }
    public void createTank(Node pos){
        towers.add(new Tank((int)pos.getX(), (int)pos.getY()));
        notifyObservers(towers.peek().getSpriteAdapter());
    }
    
    public void createWhiteHouse(Node pos){
    	buildings.add(new WhiteHouse("WhiteHouse", 360, 1200));
    	notifyObservers(buildings.peek().getSpriteAdapter());
    }

    public Tower getTower(int index){
        return towers.get(index);
    }
    
    public Tower peekTower(){
        return towers.peek();
    }

    public void sellTower(int index){
        towers.removeIndex(index);
    }


    public void upgradeTower(int index){
        //TODO
    }

    public boolean checkIfInRadius(Tower tower, Node enemyNode){
    	float deltaX = enemyNode.getX() - tower.getPos().getX();
    	float deltaY = enemyNode.getY() - tower.getPos().getY();
    	
    	if((deltaX*deltaX) + (deltaY*deltaY) < (tower.getRadius()*tower.getRadius())){
    		return true;
    	}
    	
    	return false;
    }

    private void findTargets(){
        for(Tower tower : towers){
        	for(Alien alien : aliens){
        		if(checkIfInRadius(tower,alien.getPos())){
        			tower.setTarget(alien); //dosent consider if tower wants to shoot first, last, strogest etc. TODO
        			
        		}
        	}
        }
    }

    public void shoot(){

    }

    public float calcDistance(){
        return 5;
    }

    public void rotate(){

    }

	@Override
	public void update(float deltaTime) {
		findTargets();
	}
	
	private Array<BuildingObserver> observers = new Array<BuildingObserver>(false, 10);

	  public void addObserver(BuildingObserver observer) {
	    observers.add(observer);
	  }

	  public void removeObserver(BuildingObserver observer) {
		  observers.removeValue(observer, false);
	  }

	  private void notifyObservers(SpriteAdapter SA) {
	    for (BuildingObserver observer : observers)
	      observer.actOnBuildingChange(SA);
	  }
   
}
