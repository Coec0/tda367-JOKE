package com.example.illegalaliens.models.boardobjects.towers.targetmethods;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */

/**
 * Interface implemented in all target states, using State Pattern
 */
public interface ITargetState {
	Enemy getEnemy(Node pos, Array<Enemy> enemies); //Maybe edit to Building in future
}
