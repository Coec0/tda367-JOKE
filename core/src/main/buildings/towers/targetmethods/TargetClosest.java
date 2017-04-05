package buildings.towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */
public class TargetClosest implements ITargetState {

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		/*if(Aliens.size <=0)
			return null;*/
		Enemy closest = enemies.first();
		float closestDist = getDistance(pos, closest.getPos());
		float dist;
		for (Enemy enemy : enemies) {
			dist = getDistance(pos, enemy.getPos());
			if (dist < closestDist) {
				closestDist = dist;
				closest = enemy;
			}
		}
		return closest;
	}
	
	private float getDistance(Node from, Node to){
		float deltaX = to.getX() - from.getX();
    	float deltaY = to.getY() - from.getY();
    	
    	return (float)Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
	}

}
