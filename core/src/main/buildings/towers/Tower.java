package buildings.towers;

import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.towers.targetmethods.ITargetState;
import buildings.towers.targetmethods.TargetLast;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.Node;

public abstract class Tower extends Building {
	private float radius;
	private int cost;
	private Enemy target;
	private ITargetState TState;

	protected Tower(int x, int y, float radius, String name, int cost){
		super(name, x, y);
		this.radius = radius;
		this.cost = cost;
		this.TState = new TargetLast();
	}

	public void setTargetState(ITargetState state) {
		this.TState = state;
	}

	public ITargetState getTargetState() {
		return TState;
	}

	public float getRadius() {
		return this.radius;
	}

	public int getCost() {
		return this.cost;
	}

	public void shoot(Enemy enemy) {
		makeProjectile();
        System.out.println("shoot");
    }

	public abstract Projectile makeProjectile();

	public void setTarget(Array<Enemy> targets) {
		this.target = TState.getEnemy(super.getPos(), targets);
		rotateTowards(target.getPos());
		shoot(target);
		System.out.println("settarget");
	}

	public Enemy getTarget() {
		return target;
	}

	// we probably need to move this fuction to a helper class. I just
	// copy-pasted from enemy for now (test)

	public void rotateTowards(Node newDir) {
		float oldX = super.getPos().getX();
		float oldY = super.getPos().getY();
		float newX = newDir.getX();
		float newY = newDir.getY();

		float angle = (float) Math.toDegrees(Math.atan2(newY - oldY, newX - oldX));

		if (angle >= 0) {
			angle += 360;
		}
		super.getSpriteAdapter().setRotation(angle);
	}
	

}
