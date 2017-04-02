package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import enemies.Alien;
import towers.Soldier;
import towers.Tank;
import towers.Tower;
import utilities.Node;

public class TowerModel {
    Array<Tower> towers;
    Array<Alien> aliens;


    public TowerModel(AlienModel AModel){
        towers = new Array<Tower>(false, 100);
        aliens = AModel.getAllAliens();
    }

    public void createSoldier(Node pos){
        towers.add(new Soldier((int)pos.getX(), Gdx.graphics.getHeight() -(int)pos.getY()));
    }
    public void createTank(Node pos){
        towers.add(new Tank((int)pos.getX(), (int)pos.getY()));
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
    	
    	if((deltaX*deltaX) + (deltaY*deltaY) <= (tower.getRadius()*tower.getRadius())){
    		return true;
    	}
    	return false;
    }

    public void findTargets(){
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
   
}
