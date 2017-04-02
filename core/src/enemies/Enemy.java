package enemies;

import utilities.Node;
import utilities.SpriteAdapter;

public abstract class Enemy {
	private SpriteAdapter pos;
	private float speed;
	private float health; //Maybe int in future
	private int nodeArrayPos = 0;
	private Node direction = new Node(0,1); //x and y values for the start direction. Faces towards east now

	public Enemy(int x, int y, float speed, float health) {
		this.pos = new SpriteAdapter(x, y);
		this.speed = speed;
		this.health = health;
	}
	public boolean isDead(){
		return health<=0;
	}
	
	public void hurt(float dmg){
		health -= dmg;
	}
	
	public void setDirection(Node newDir){
		if (isAngleNegative(newDir)) {
			pos.rotate(-getAngle(newDir));
		} else {
			pos.rotate(getAngle(newDir));
		}
		direction = newDir; //update direction
	}

	/**
	 * Calculate angle using dot product with old direction and new direction
	 * @param newDir
	 * @return angle
	 */
	private float getAngle(Node newDir) {
		float oldX = direction.getX();
		float oldY = direction.getY();
		float newX = newDir.getX();
		float newY = newDir.getY();

		float oldLength = (float) Math.sqrt(oldX*oldX + oldY*oldY);
		float newLength = (float) Math.sqrt(newX*newX + newY*newY);

		return (float) (Math.acos((oldX*newX + newY*oldY) / (oldLength*newLength)) * (180/Math.PI));
	}

	/**
	 * Check if angle is positive using determinant
	 * @param newDir
	 * @return true if angle is positive
	 */
	private boolean isAngleNegative(Node newDir) {
		float determinant = direction.getX() * newDir.getY() - direction.getY() * newDir.getX();

		return determinant < 0;
	}
	
	public Node getDirection(){
		return direction;
	}

	public Node getPos() {
		return new Node(pos.getX(),pos.getY());
	}

	public void setPos(Node pos) {
		this.pos.setPosition(pos.getX(), pos.getY());
	}

	public SpriteAdapter getSpriteAdapter(){
		return pos;
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public int getNodeArrayPos(){
		return nodeArrayPos;
	}
	
	public void setNodeArrayPos(int index){
		this.nodeArrayPos = index;
	}
	
	public void increaseNodeArrayPos(int index){
		this.nodeArrayPos += index;
	}
	
	public float getHealth() {
		return health;
	}
}
