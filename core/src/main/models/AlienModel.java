package models;

import com.badlogic.gdx.utils.Array;

import enemies.Alien;
import enemies.Enemy;
import utilities.Node;
import utilities.PathFinder;
import utilities.SpriteCollector;
import utilities.UpdateObserver;

public class AlienModel implements UpdateObserver {
	private PathFinder finder = PathFinder.getInstance();

	private Array<Enemy> aliens;
	private Array<Node> path;
	private Array<Node> direction;

	public AlienModel() {
		aliens = new Array<Enemy>(false, 10);
		
		path = finder.getShortestPath();
		direction = finder.getDirectionList();
	}
	
	public void createAlien(){
		
		aliens.add(new Alien((int)path.get(0).getX(),(int)path.get(0).getY()));
	}
	
	public void createAlien(int amount){
		for(int i=0; i<amount; i++)
			aliens.add(new Alien());
	}
	
	private void moveAllAliens(float deltaTime){
		//if(aliens.size>0){
			for(Enemy alien : aliens){
				moveAlien(alien, deltaTime);
			}
		//}
	}
	
	public Enemy peekAlien(){
		return aliens.peek();
	}
	
	public Array<Enemy> getAllAliens(){
		return aliens;
	}
	
	public Enemy getAlien(int index){
		if(aliens.size>index)
			return aliens.get(index);
		return null;
	}
	
	public void moveAlien(Enemy alien, float deltaTime){
		int position = (int)(alien.getNodeArrayPos()+((int)alien.getSpeed()*deltaTime));
		if(position >= path.size){
			//TODO
			//Lose life method
			removeEnemy(alien);
		} else {	
			alien.setPos(path.get(position));
			alien.setNodeArrayPos(position);
			alien.setDirection(direction.get(position));
		}
	}
	
	public void removeEnemy(Enemy enemy){
		SpriteCollector.getInstance().removeSprite(enemy.getSpriteAdapter());
		aliens.removeValue(enemy, false);
		enemy=null; //Clear
	}
	
	//Gonna listen from a tower that attacked it
	public void damaged(Alien alien, float dmg){
		alien.hurt(dmg);
		
		if(alien.isDead()){
			removeEnemy(alien);
		}
	}

	@Override
	public void update(float deltaTime) {
		moveAllAliens(deltaTime);
	}
}
