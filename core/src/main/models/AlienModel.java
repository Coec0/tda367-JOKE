package models;

import com.badlogic.gdx.utils.Array;

import enemies.AlienFactory;
import enemies.Enemy;
import enemies.HighAlien;
import map.MapNode;
import observers.AlienObserver;
import observers.UpdateObserver;
import path.PathFinder;
import utilities.Node;
import waves.EnemyWavesCreator;

public class AlienModel implements UpdateObserver {
	private PathFinder finder;

	private Array<Enemy> enemies;
	private Array<Node> defaultPath;
	private Array<Node> direction;
	
	private int frames = 10; //value-time inbetween aliens
	private boolean waveON;
	private boolean waveAlive;
	private EnemyWavesCreator EWC;
	private Array<Enemy> wave;
	private int enemyCounter = 0;
	private Array<MapNode> startingPos;

	public AlienModel(PathFinder finder, Array<MapNode> startingPos) {
		this.startingPos = startingPos;
		this.finder = finder;
		EWC = new EnemyWavesCreator();
		enemies = new Array<Enemy>(false, 10);
		defaultPath = finder.getShortestPath(this.startingPos.get(0));
		//direction = finder.getDirectionList();
	}
	
	public void addEnemy(Enemy enemy){
		if(enemy instanceof HighAlien){
			enemy.setPath(defaultPath);
		}else if(EWC.hasLevelRandomSpawn()){
			enemy.setPath(finder.getShortestPath(startingPos.random()));
		}else{
			enemy.setPath(defaultPath);
		}
		enemy.setStartignPos();
		enemies.add(enemy);
		notifyObservers(enemy, false);
	}
	
	public boolean getWaveOn(){
		return waveON;
	}

	public boolean getWaveAlive(){
		return waveAlive;
	}
	
	private void moveAllEnemies(){
		//if(aliens.size>0){
			for(Enemy enemy : enemies){
				enemy.rotateEnemy();
				moveEnemy(enemy);
				if(enemy.isDead()){ //check if alien is dead
					System.out.println("dead");
					removeEnemy(enemy);
				}
			}
		//}
	}
	
	public Enemy peekEnemy(){
		return enemies.peek();
	}
	
	public Array<Enemy> getAllEnemies(){
		return enemies;
	}
	
	public Enemy getEnemy(int index){
		if(enemies.size>index)
			return enemies.get(index);
		return null;
	}
	
	public void moveEnemy(Enemy enemy){
		int position = (int)(enemy.getNodeArrayPos()+((int)enemy.getSpeed()));
		if(position >= enemy.getPath().size){
			removeEnemy(enemy);
		} else {	
			enemy.setPos(enemy.getPath().get(position));
			enemy.setNodeArrayPos(position);
			//enemy.setDirection(direction.get(position));
		}
	}
	
	public void removeEnemy(Enemy enemy){
		notifyObservers(enemy, true);
		enemies.removeValue(enemy, false);
		enemy=null; //Clear
	}
	
	//Gonna listen from a tower that attacked it
	public void damaged(Enemy enemy, float dmg){
		enemy.hurt(dmg);
		
		if(enemy.isDead()){
			removeEnemy(enemy);
		}
	}
	
	public void startNextWave(){
		wave = EWC.getNextWave();
		waveON = true;
		waveAlive = true;
	}
	
	@Override
	public void update(float deltaTime) {
		moveAllEnemies();
		frames++;
		if(frames > 10){
			if(waveON){
				spawnNextEnemy();
			}
			frames = 0;
			
		}
		if (!(waveON) && enemies.size == 0)
			waveAlive=false;
	}
	
	private void spawnNextEnemy(){ 
		if(enemyCounter < wave.size){
			addEnemy(AlienFactory.createToughAlien()); //TODO remove hardcoded alien!
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
