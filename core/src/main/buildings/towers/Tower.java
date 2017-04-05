package buildings.towers;

import buildings.Building;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.Node;

public abstract class Tower extends Building {
	private float radius;
	private int cost;
	private float damage;
	private Enemy target;

	protected Tower(int x, int y, float radius, String name, int cost, float damage) {
		super(name, x, y);
		this.radius = radius;
		this.cost = cost;
		this.damage = damage;

	}

	public float getRadius() {
		return this.radius;
	}


	public int getCost() {
		return this.cost;
	}

	public float getDamage() {
		return this.damage;
	}

	public  void shoot(Enemy enemy){
		makeProjectile();
	}

	public abstract Projectile makeProjectile();


	public void setTarget(Enemy target) {
		// System.out.println(target.getPos().getX() + " " +
		// target.getPos().getY());
		testFunction(target.getPos());
		this.target = target;
	}

	public Enemy getTarget() {
		return target;
	}

	// we probably need to move this fuction to a helper class. I just
	// copy-pasted from enemy for now (test)

	public void testFunction(Node newDir) {
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
