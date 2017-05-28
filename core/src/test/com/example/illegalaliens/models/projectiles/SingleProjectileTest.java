package com.example.illegalaliens.models.projectiles;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetClosest;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Test for SingleProjectile
 * @author Johan Svennungsson
 */
public class SingleProjectileTest {

	private Radar radar;
	private Array<Enemy> enemies;
	private Enemy enemy;
	private Tower soldier;
	private Projectile projectile;

	@Before
	public void setUp() throws Exception {
		radar = new Radar();

		enemies = new Array<Enemy>();
		enemy = new Alien(200,200);
		enemies.add(enemy);

		soldier = new Soldier(230,230);
		soldier.setTargetState(new TargetClosest());
		soldier.setTarget(enemies);

		projectile = new Bullet(soldier.getTarget().getPos(), soldier.getPos(), soldier.getDamage(), 25 ,3);
	}

	@Test
	public void damage() throws Exception {
		assertEquals(enemy.getHealth(), 50, 0);
		projectile.setPosition(new Node(201,201));
		projectile.damage(radar, enemies);
		assertEquals(enemy.getHealth(), 20, 0);
	}

}