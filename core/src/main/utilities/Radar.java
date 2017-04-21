package utilities;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import models.AlienModel;

public class Radar {
	
	private AlienModel aModel;
	
	
	public Radar(AlienModel aModel){
		this.aModel = aModel;
	}

	
	public Array<Enemy> scan(Node center, float radius){
		Array<Enemy> knownAliens = new Array<Enemy>();
		
		for(Enemy alien : aModel.getAllAliens()){
			float distance = (float) center.getDistanceTo(alien.getPos());
			if(distance <= radius+alien.getRadius()){
				knownAliens.add(alien);
			}
		}
		
		
		
		
		
		
		
		
		
		/*for(Alien alien : aModel.getAllAliens()){
			float deltaX = alien.getPos().getX() - center.getX();
	    	float deltaY = alien.getPos().getY() - center.getY();
	    	
	    	if((deltaX*deltaX) + (deltaY*deltaY) < radius*radius){
	    		knownAliens.add(alien);
	    	}
		}*/
		
		return knownAliens;
    }

}
