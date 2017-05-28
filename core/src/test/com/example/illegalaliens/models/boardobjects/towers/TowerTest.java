package com.example.illegalaliens.models.boardobjects.towers;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.ITargetState;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetClosest;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetLast;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.Enemy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for Tower
 * @author Johan Svennungsson
 */
public class TowerTest {

	private ITargetState targetClosest;
	private ITargetState targetLast;
	private Array<Enemy> enemies;
	private Enemy alien;
	private Tower soldier;

	@Before
	public void setUp() throws Exception {
		targetClosest = new TargetClosest();
		targetLast = new TargetLast();
		enemies = new Array<Enemy>();
		alien = new Alien(120,120);

		enemies.add(alien);
		soldier = new Soldier(100,100);

		soldier.setTargetState(targetClosest);
	}

	@Test
	public void getTargetState() throws Exception {
		assertEquals(soldier.getTargetState(), targetClosest);
	}

	@Test
	public void setTargetState() throws Exception {
		assertEquals(soldier.getTargetState(), targetClosest);
		soldier.setTargetState(targetLast);
		assertEquals(soldier.getTargetState(), targetLast);
	}

	@Test
	public void setTarget() throws Exception {
		assertEquals(soldier.getTarget(), null);
		soldier.setTarget(enemies);
		assertEquals(soldier.getTarget(), alien);
	}

	@Test
	public void hasTarget() throws Exception {
		assertEquals(soldier.hasTarget(), false);
		soldier.setTarget(enemies);
		assertEquals(soldier.hasTarget(), true);
	}

}