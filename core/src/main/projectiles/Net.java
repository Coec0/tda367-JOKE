package projectiles;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;
import utilities.Radar;

public class Net extends Projectile{
	
	private static final float RADIUS = 20;
    private static final int HEALTH = 1;
    private static final int netSpeed = 10;
	
	public Net(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED) {
        super(HEALTH, DAMAGE, SPEED, RADIUS, DIRECTION, POSITION);
    }

	@Override
	public void damage(Radar radar, Array<Enemy> enemies) {
		Enemy enemy = radar.scan(this.getPosition(), this.getRadius(), enemies).first();
		enemy.placeInNet(netSpeed);
		this.reduceHealth();
		
	}
}
