package com.example.illegalaliens.models.projectiles;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetClosest;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Radar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test for Projectile
 * @author Johan Svennungsson
 */
public class ProjectileTest {

	private Radar radar;
	private Array<Enemy> enemies;
	private Tower soldier;
	private SingleProjectile projectile;
	private Enemy enemy;

	@Before
	public void setUp() throws Exception {
		radar = new Radar();
		enemies = new Array<Enemy>();
		enemy = new Alien(130,130);
		enemies.add(enemy);

		soldier = new Soldier(100,100);
		soldier.setTargetState(new TargetClosest());
		soldier.setTarget(enemies);
		projectile = new Bullet(soldier.getTarget().getPos(), soldier.getPos(), soldier.getDamage(), 25, 3);
	}

	@Test
	public void scanEnemies() throws Exception {
		assertEquals(projectile.scanEnemies(radar, soldier.getPos(), soldier.getRadius(), enemies).first(), enemy);
	}

}