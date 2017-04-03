package models;

import com.badlogic.gdx.utils.Array;

import enemies.Alien;
import utilities.Node;
import utilities.PathFinder;
import utilities.SpriteCollector;
import utilities.UpdateObserver;

public class AlienModel implements UpdateObserver {
	private PathFinder finder = PathFinder.getInstance();

	private Array<Alien> aliens;
	private Array<Node> path;
	private Array<Node> direction;

	public AlienModel() {
		aliens = new Array<Alien>(false, 10);
		
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
			for(Alien alien : aliens){
				moveAlien(alien, deltaTime);
			}
		//}
	}
	
	public Alien peekAlien(){
		return aliens.peek();
	}
	
	public Array<Alien> getAllAliens(){
		return aliens;
	}
	
	public Alien getAlien(int index){
		if(aliens.size>index)
			return aliens.get(index);
		return null;
	}
	
	public void moveAlien(Alien alien, float deltaTime){
		int position = (int)(alien.getNodeArrayPos()+((int)alien.getSpeed()*deltaTime));
		if(position >= path.size){
			//TODO
			//Lose life method
			SpriteCollector.getInstance().removeSprite(alien.getSpriteAdapter());
		} else {	
			alien.setPos(path.get(position));
			alien.setNodeArrayPos(position);
			alien.setDirection(direction.get(position));
		}
	}
	
	//Gonna listen from a tower that attacked it
	public void damaged(Alien alien, float dmg){
		alien.hurt(dmg);
		
		if(alien.isDead()){
			aliens.removeValue(alien, false);
		}
	}

	@Override
	public void update(float deltaTime) {
		moveAllAliens(deltaTime);
	}
}
