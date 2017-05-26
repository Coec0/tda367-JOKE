package com.example.illegalaliens.models.boardobjects.towers.targetmethods;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */

/**
 * Targets the strongest enemy available
 */
public class TargetStrongest implements ITargetState  {

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		float strongest = enemies.first().getHealth();
		Enemy strongestE = enemies.first();
        for (Enemy enemy : enemies){
		    if(enemy.getHealth() > strongest){
		        strongest = enemy.getHealth();
		        strongestE = enemy;
            }
        }
		return strongestE;
	}

	@Override
	public String toString() {
		return "Strongest";
	}

}
