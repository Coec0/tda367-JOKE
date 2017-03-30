package models;

import com.badlogic.gdx.utils.Array;

import enemies.Alien;
import utilities.Node;
import utilities.PathFinder;

public class AlienModel {
	Array<Alien> aliens;
	PathFinder finder;
	Array<Node> nodes = new Array<Node>();
	Array<Node> path;
	public AlienModel(){
		aliens = new Array<Alien>(false, 10);
		
		nodes.add(new Node(0,360)); //just to test the pathfinder class
		nodes.add(new Node(500,360));
		nodes.add(new Node(500,200));
		nodes.add(new Node(780,200));
		nodes.add(new Node(780,360));
		nodes.add(new Node(1280,360));
		
		finder = new PathFinder(nodes);
		path = finder.getShortestPath();
	}
	
	public void createAlien(){
		
		aliens.add(new Alien((int)path.get(0).getX(),(int)path.get(0).getY()));
	}
	
	public void createAlien(int amount){
		for(int i=0; i<amount; i++)
			aliens.add(new Alien());
	}
	
	public void moveAllAliens(){
		for(Alien alien : aliens){
			moveAlien(alien);
		}
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
	
	public void moveAlien(Alien alien){
		int position = alien.getNodeArrayPos()+(int)alien.getSpeed();
		if(position >= path.size){
			//TODO
			//Lose life method
		} else {	
			alien.setPos(path.get(position));
			alien.increaseNodeArrayPos((int)alien.getSpeed());
		}
	}
	
	//Gonna listen from a tower that attacked it
	public void damaged(Alien alien, float dmg){
		alien.hurt(dmg);
		
		if(alien.isDead()){
			aliens.removeValue(alien, false);
		}
	}
}
