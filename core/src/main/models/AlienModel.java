package models;

import com.badlogic.gdx.utils.Array;

import enemies.AlienFactory;
import enemies.Enemy;
import utilities.AlienObserver;
import utilities.EnemyWavesCreator;
import utilities.MapNode;
import utilities.Node;
import utilities.PathFinder;
import utilities.UpdateObserver;

public class AlienModel implements UpdateObserver {
	private PathFinder finder;

	private Array<Enemy> aliens;
	private Array<Node> path;
	private Array<Node> direction;
	
	private int frames = 10; //value-time inbetween aliens
	private boolean waveON;
	private EnemyWavesCreator EWC;
	private Array<Enemy> wave;
	private int enemyCounter = 0;
	private Array<MapNode> startingPos;

	public AlienModel(PathFinder finder, Array<MapNode> startingPos) {
		this.startingPos = startingPos;
		this.finder = finder;
		EWC = new EnemyWavesCreator();
		aliens = new Array<Enemy>(false, 10);
		path = finder.getShortestPath(this.startingPos.get(0));
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
				if(alien.isDead()){ //check if alien is dead
					removeEnemy(alien);
				}
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
	
	public void startNextWave(){
		wave = EWC.getNextWave();
		waveON = true;
	}
	
	@Override
	public void update(float deltaTime) {
		moveAllAliens();
		frames++;
		if(frames > 10){
			if(waveON){
				spawnNextEnemy();
			}
			frames = 0;
			
		}
	}
	
	private void spawnNextEnemy(){ 
		if(enemyCounter < wave.size){
			addAlien(AlienFactory.createAlien()); //TODO remove hardcoded alien!
			enemyCounter++;
		}else{
			waveON = false;
		}
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
