package com.example.illegalaliens.models;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.UpdateObserver;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.enemies.EnemyObserver;
import com.example.illegalaliens.models.enemies.HighAlien;
import com.example.illegalaliens.models.enemies.waves.EnemyWavesCreator;
import com.example.illegalaliens.models.enemies.waves.WavesObserver;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.cooldown.WavesCDHandler;
import com.example.illegalaliens.utilities.path.RoadManager;
import com.example.illegalaliens.utilities.path.map.MapNode;

public class AlienModel implements UpdateObserver {
	private RoadManager manager;

	private Array<Enemy> enemies;
	private Array<Array<Node>> defaultPaths;
	private int waveCounter=0;
	private int frames = 10;
	private boolean waveON;
	private boolean waveAlive;
	private EnemyWavesCreator EWC;
	private Array<Enemy> wave;
	private int enemyCounter = 0;
	private Array<MapNode> startingPos;
	private WavesCDHandler wavescdhandler;
	private Array<Enemy> nettedEnemies;
	private boolean openBorders = false;

	public AlienModel(RoadManager manager, Array<MapNode> startingPos, WavesCDHandler wavescdhandler) {
		this.startingPos = startingPos;
		this.manager = manager;
		EWC = new EnemyWavesCreator();
		enemies = new Array<Enemy>(false, 10);
		nettedEnemies = new Array<Enemy>(false,10);
		defaultPaths = calcAllDefaultPaths(startingPos);
		this.wavescdhandler = wavescdhandler;
	}
	
	private Array<Array<Node>> calcAllDefaultPaths(Array<MapNode> startingPos){
		Array<Array<Node>> defaultPaths = new Array<Array<Node>>();
		for(MapNode start : startingPos){
			defaultPaths.add(manager.getShortestPath(start));
		}
		return defaultPaths;
	}
	
	public void openBorders(){
		openBorders = true;
	}
	
	private void addEnemy(Enemy enemy){
		
		if(enemy instanceof HighAlien){
			enemy.setPath(defaultPaths.random());
		}else if(EWC.hasLevelRandomSpawn()){
			enemy.setPath(manager.getShortestPath(startingPos.random()));
		}else{
			enemy.setPath(defaultPaths.get(0));
		}
		enemy.setStartignPos();
		enemies.add(enemy);
		notifyEnemyObservers(enemy, false);
	}
	
	public boolean isWaveOn(){
		return waveON;
	}
	

	public boolean getWaveAlive(){
		return waveAlive;
	}
	
	private void moveAllEnemies(){
		for(Enemy enemy : enemies){
			enemy.rotateEnemy();
			moveEnemy(enemy);
				if(enemy.isDead()){
					removeEnemy(enemy);
				}if(enemy.isInNet()){
					if(!nettedEnemies.contains(enemy, false)){
						nettedEnemies.add(enemy);
						notifyEnemyObservers(enemy, false);
					}
				}
			}

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
	
	private void moveEnemy(Enemy enemy){
		int position = (enemy.getNodeArrayPos()+((int)enemy.getSpeed()));
		if(position >= enemy.getPath().size){
			removeEnemy(enemy);
		} else {	
			enemy.setPos(enemy.getPath().get(position));
			enemy.setNodeArrayPos(position);
		}
	}
	
	private void removeEnemy(Enemy enemy){
		notifyEnemyObservers(enemy, true);
		enemies.removeValue(enemy, false);
		enemy=null; //Clear
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
		notifyWavesObservers(waveCounter, false);
		waveCounter++;
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
		if (!(waveON) && enemies.size == 0 && waveAlive){
			waveAlive=false;
			wavescdhandler.registerNewWave();
			notifyWavesObservers(waveCounter, true);
		}
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
	
	private Array<WavesObserver> wavesObservers = new Array<WavesObserver>(false, 10);

	public void addWavesObserver(WavesObserver observer) {
		wavesObservers.add(observer);
	}

	public void removeWavesObserver(WavesObserver observer) {
		wavesObservers.removeValue(observer, false);
	}

	private void notifyWavesObservers(int wave, boolean finished) {
		for (WavesObserver observer : wavesObservers)
			observer.actOnWavesChange(wave, finished);
	}
	
	private Array<EnemyObserver> enemyObservers = new Array<EnemyObserver>(false, 10);

	public void addEnemyObserver(EnemyObserver observer) {
		enemyObservers.add(observer);
	}

	public void removeEnemyObserver(EnemyObserver observer) {
		enemyObservers.removeValue(observer, false);
	}

	private void notifyEnemyObservers(Enemy enemy, boolean remove) {
		for (EnemyObserver observer : enemyObservers)
			observer.actOnEnemyChange(enemy, remove);
	}
}
