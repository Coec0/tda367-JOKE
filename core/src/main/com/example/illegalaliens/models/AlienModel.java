package com.example.illegalaliens.models;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.UpdateObserver;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.AlienFactory;
import com.example.illegalaliens.models.enemies.AlienWithHelmet;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.enemies.EnemyObserver;
import com.example.illegalaliens.models.enemies.HighAlien;
import com.example.illegalaliens.models.enemies.SneakyAlien;
import com.example.illegalaliens.models.enemies.ToughAlien;
import com.example.illegalaliens.models.enemies.waves.EnemyWavesCreator;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.cooldown.WavesCDHandler;
import com.example.illegalaliens.utilities.path.PathFinder;
import com.example.illegalaliens.utilities.path.map.MapNode;

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
	
	public boolean isWaveOn(){
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
			addEnemy(wave.get(enemyCounter).clone());
			enemyCounter++;
		}else{
			enemyCounter = 0;
			waveON = false;
		}
	}
	
	
	private Array<EnemyObserver> observers = new Array<EnemyObserver>(false, 10);

	public void addObserver(EnemyObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(EnemyObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(Enemy enemy, boolean remove) {
		for (EnemyObserver observer : observers)
			observer.actOnEnemyChange(enemy, remove);
	}
}
