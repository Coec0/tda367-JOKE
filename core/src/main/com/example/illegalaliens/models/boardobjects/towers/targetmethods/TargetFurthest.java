package com.example.illegalaliens.models.boardobjects.towers.targetmethods;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */

/**
 * Targets the furthest enemy
 */
public class TargetFurthest implements ITargetState {

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {

		Enemy furthest = enemies.first();
		float furthestDist = getDistance(pos, furthest.getPos());
		float dist;
		for (Enemy enemy : enemies) {
			dist = getDistance(pos, enemy.getPos());
			if (dist > furthestDist) {
				furthestDist = dist;
				furthest = enemy;
			}
		}
		return furthest;
	}
	
	private float getDistance(Node from, Node to){
		float deltaX = to.getX() - from.getX();
    	float deltaY = to.getY() - from.getY();
    	
    	return (float)Math.sqrt((deltaX*deltaX) + (deltaY*deltaY));
	}

	@Override
	public String toString() {
		return "Furthest";
	}
}
