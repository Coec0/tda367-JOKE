package com.example.illegalaliens.models.boardobjects.towers.targetmethods;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */

/**
 * Targets the closest enemy available
 */
public class TargetClosest implements ITargetState {

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
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

	@Override
	public String toString() {
		return "Closest";
	}

}
