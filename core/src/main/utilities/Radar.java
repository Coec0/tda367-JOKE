package utilities;

import com.badlogic.gdx.utils.Array;

import enemies.Alien;
import models.AlienModel;

public class Radar {
	
	private AlienModel aModel;
	
	
	public Radar(AlienModel aModel){
		this.aModel = aModel;
	}

	
	public Array<Alien> scan(Node center, float radius){
		Array<Alien> knownAliens = new Array<Alien>();
		for(Alien alien : aModel.getAllAliens()){
			float deltaX = alien.getPos().getX() - center.getX();
	    	float deltaY = alien.getPos().getY() - center.getY();
	    	
	    	if((deltaX*deltaX) + (deltaY*deltaY) < radius*radius){
	    		knownAliens.add(alien);
	    	}
		}
		
		return knownAliens;
    }
}
