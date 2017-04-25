package models;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.AlienObserver;
import utilities.Node;
import utilities.PathFinder;
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
	
	public void addAlien(Enemy enemy){
		enemy.setPos(new Node((int)path.get(0).getX(),(int)path.get(0).getY()));
		aliens.add(enemy);
		notifyObservers(aliens.peek(), false);
	}
	
	private void moveAllAliens(){
		//if(aliens.size>0){
			for(Enemy alien : aliens){
				moveAlien(alien);
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
	
	public void moveAlien(Enemy alien){
		int position = (int)(alien.getNodeArrayPos()+((int)alien.getSpeed()*0.01));
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
		notifyObservers(enemy, true);
		aliens.removeValue(enemy, false);
		enemy=null; //Clear
	}
	
	//Gonna listen from a tower that attacked it
	public void damaged(Enemy alien, float dmg){
		alien.hurt(dmg);
		
		if(alien.isDead()){
			removeEnemy(alien);
		}
	}

	@Override
	public void update(float deltaTime) {
		moveAllAliens();
	}
	
	private Array<AlienObserver> observers = new Array<AlienObserver>(false, 10);

	public void addObserver(AlienObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(AlienObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(Enemy enemy, boolean remove) {
		for (AlienObserver observer : observers)
			observer.actOnEnemyChange(enemy, remove);
	}
}
