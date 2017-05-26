package com.example.illegalaliens.models.boardobjects.towers.targetmethods;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;

/**
 * Targets the last enemy in their node array
 */

public class TargetLast implements ITargetState {
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		int last=enemies.first().getPath().size - enemies.first().getNodeArrayPos();
		Enemy lastE = enemies.first();
		for(Enemy enemy: enemies){
			if(enemy.getPath().size - enemy.getNodeArrayPos()>last){
				last = enemy.getPath().size - enemy.getNodeArrayPos();
				lastE = enemy;
			}
		}
		return lastE;
	}

	@Override
	public String toString() {
		return "Last";
	}
}
