package enemies;

import java.awt.Point;

public abstract class Enemy {
	private Point pos;
	private float speed;
	private float health; //Maybe int in future
	
	public Enemy(Point pos, float speed, float health) {
		this.pos = pos;
		this.speed = speed;
		this.health = health;
	}
	
	public Point getPos() {
		return pos;
	}

	public void setPos(Point pos) {
		this.pos = pos;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getHealth() {
		return health;
	}

	
	
	
	

}
