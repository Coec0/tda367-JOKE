package com.example.illegalaliens.models.boardobjects.towers.targetmethods;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;

/**
 * Targets the first enemy (in their node array)
 */

public class TargetFirst implements ITargetState{

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		int first=enemies.first().getPath().size - enemies.first().getNodeArrayPos();
		Enemy firstE = enemies.first();
		for(Enemy enemy: enemies){
			if(enemy.getPath().size - enemy.getNodeArrayPos()<first){
				first = enemy.getPath().size - enemy.getNodeArrayPos();
				firstE = enemy;
			}
		}
		return firstE;
	}

	@Override
	public String toString() {
		return "First";
	}

}
