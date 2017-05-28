package com.example.illegalaliens.models.superpowers;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.Superpower;
import com.example.illegalaliens.models.enemies.Enemy;

/**
 * Created by Emil on 2017-05-04.
 */

/**
 * This class does only kill all enemies alive on the map
 */
public class Nuke implements Superpower {
	private int superPowerCost;
    public Nuke(int superPowerCost){
    	this.superPowerCost = superPowerCost;
    }

    public void perform(Array<Enemy> enemies){
        for (Enemy enemy : enemies){
            enemy.kill();
        }
    }

	@Override
	public int getSuperPowerCost() {
		return superPowerCost;
	}

	@Override
	public void setSuperPowerCost(int cost) {
		this.superPowerCost = cost;
	}

	@Override
	public String getName() {
		return "NUKE";
	}
}
