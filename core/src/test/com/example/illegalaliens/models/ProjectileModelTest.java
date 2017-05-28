package com.example.illegalaliens.models;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.models.boardobjects.towers.targetmethods.TargetClosest;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.projectiles.Bullet;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.cooldown.WavesCDHandler;
import com.example.illegalaliens.utilities.path.RoadManager;
import com.example.illegalaliens.utilities.path.map.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for ProjectileModel
 * @author Johan Svennungsson
 */
public class ProjectileModelTest {

	private Array<Enemy> enemies;
	private Enemy enemy;
	private Projectile projectile;
	private ProjectileModel projectileModel;

	@Before
	public void setUp() throws Exception {
		Map map = new Map("AlphaMap");
		Radar radar = new Radar();
		WavesCDHandler wavesCDHandler = new WavesCDHandler();
		RoadManager roadManager = new RoadManager(map.getMapNodes(), map.getMapNodes().peek(), map.getStartingNodes(), radar);
		AlienModel alienModel = new AlienModel(roadManager, map.getStartingNodes(), wavesCDHandler);

		enemy = new Alien(130,130);
		enemies = new Array<Enemy>();
		enemies.add(enemy);
		Tower soldier = new Soldier(100, 100);
		soldier.setTargetState(new TargetClosest());
		soldier.setTarget(enemies);
		projectile = new Bullet(soldier.getTarget().getPos(), soldier.getPos(), soldier.getDamage(), 25, 3);

		projectileModel = new ProjectileModel(alienModel, radar);
	}

	@Test
	public void addProjectile() throws Exception {
		assertEquals(projectileModel.getProjectiles().contains(projectile, false), false);
		projectileModel.addProjectile(projectile);
		assertEquals(projectileModel.getProjectiles().contains(projectile, false), true);
	}

	@Test
	public void scan() throws Exception {
		projectile.setPosition(new Node(129,129)); //very close to Alien at 130;130
		assertEquals(projectileModel.scan(projectile, enemies).contains(enemy, false), true);
	}

	@Test
	public void removeProjectile() throws Exception {
		projectileModel.addProjectile(projectile);
		assertEquals(projectileModel.getProjectiles().contains(projectile, false), true);
		projectileModel.removeProjectile(projectile);
		assertEquals(projectileModel.getProjectiles().contains(projectile, false), false);
	}

}