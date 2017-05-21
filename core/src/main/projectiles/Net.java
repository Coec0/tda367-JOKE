package projectiles;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;
import utilities.Radar;

public class Net extends Projectile{
	
	private static final float RADIUS = 30;
    private static final int HEALTH = 1;
	
	public Net(int health, float damage, float speed, float radius, Node direction, Node position) {
        super(health, damage, speed, radius, direction, position);
    }

	@Override
	public void damage(Radar radar, Array<Enemy> enemies) {
		Enemy enemy = radar.scan(this.getPosition(), this.getRadius(), enemies).first();
		//enemy.stick???
		this.reduceHealth();
		
	}
}
