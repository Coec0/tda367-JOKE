package models;

import com.badlogic.gdx.utils.Array;

import cooldown.WavesCDHandler;
import enemies.Alien;
import enemies.AlienFactory;
import enemies.AlienWithHelmet;
import enemies.Enemy;
import enemies.HighAlien;
import enemies.SneakyAlien;
import enemies.ToughAlien;
import map.MapNode;
import observers.AlienObserver;
import observers.UpdateObserver;
import path.PathFinder;
import utilities.Node;
import waves.EnemyWavesCreator;

public class AlienModel implements UpdateObserver {
	private PathFinder finder;

	private Array<Enemy> enemies;
	private Array<Array<Node>> defaultPaths;
	private Array<Node> direction;
	
	private int frames = 10; //value-time inbetween aliens
	private boolean waveON;
	private boolean waveAlive;
	private EnemyWavesCreator EWC;
	private Array<Enemy> wave;
	private int enemyCounter = 0;
	private Array<MapNode> startingPos;
	private WavesCDHandler wavescdhandler;
	private Array<Enemy> nettedEnemies;
	private boolean openBorders = false;

	public AlienModel(PathFinder finder, Array<MapNode> startingPos, WavesCDHandler wavescdhandler) {
		this.startingPos = startingPos;
		this.finder = finder;
		EWC = new EnemyWavesCreator();
		enemies = new Array<Enemy>(false, 10);
		nettedEnemies = new Array<Enemy>(false,10);
		defaultPaths = calcAllDefaultPaths(startingPos);
		this.wavescdhandler = wavescdhandler;
		//direction = finder.getDirectionList();
	}
	
	public  Array<Array<Node>> calcAllDefaultPaths(Array<MapNode> startingPos){
		Array<Array<Node>> defaultPaths = new Array<Array<Node>>();
		for(MapNode start : startingPos){
			defaultPaths.add(finder.getShortestPath(start));
		}
		return defaultPaths;
	}
	
	public void openBorders(){
		openBorders = true;
	}
	
	public void addEnemy(Enemy enemy){
		
		if(enemy instanceof HighAlien){
			enemy.setPath(defaultPaths.random());
		}else if(EWC.hasLevelRandomSpawn()){
			enemy.setPath(finder.getShortestPath(startingPos.random()));
		}else{
			enemy.setPath(defaultPaths.get(0));
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
				}if(enemy.isInNet()){
					if(!nettedEnemies.contains(enemy, false)){
						nettedEnemies.add(enemy);
						notifyObservers(enemy, false);
					}
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
		if(openBorders){
			wave = new Array<Enemy>();
			Array<Enemy> tmpWave = EWC.getNextWave();
			for(Enemy enemy : tmpWave){
				wave.add(enemy);
				wave.add(enemy);
			}
		openBorders = false;
		}else{
			wave = EWC.getNextWave();
		}
		wavescdhandler.registerNewWave();
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
			if (wave.get(enemyCounter) instanceof Alien)
				addEnemy(AlienFactory.createAlien());
			if (wave.get(enemyCounter) instanceof AlienWithHelmet)
				addEnemy(AlienFactory.createAlienWithHelmet());
			if (wave.get(enemyCounter) instanceof SneakyAlien)
				addEnemy(AlienFactory.createSneakyAlien());
			if (wave.get(enemyCounter) instanceof HighAlien)
				addEnemy(AlienFactory.createHighAlien());
			if (wave.get(enemyCounter) instanceof ToughAlien)
				addEnemy(AlienFactory.createToughAlien());
			enemyCounter++;
		}else{
			enemyCounter = 0;
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
