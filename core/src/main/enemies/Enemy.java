package enemies;

import com.badlogic.gdx.utils.Array;

import utilities.Node;
import utilities.SpriteAdapter;

public abstract class Enemy {
	private SpriteAdapter pos;
	private float speed;
	private float health; //Maybe int in future
	private int nodeArrayPos = 0;
	private Node direction = new Node(0,1); //x and y values for the start direction. Faces towards east now
	private float radius;
	private Array<Node> path;
	private float money=20;
	
	public Enemy(int x, int y, float speed, float health,float radius) {
		this.pos = new SpriteAdapter(x, y);
		
		this.radius = radius;
		this.speed = speed;
		this.health = health;
	}
	
	public float getMoney(){ //Override in subclasses or make abstract for specific
		return money;        //values in future
	}
	public boolean isDead(){
		return health<=0;
	}
	
	public void rotateEnemy() {
		if(nodeArrayPos + 1 < path.size){
			this.getSpriteAdapter().rotateTowards(path.get(nodeArrayPos + 1), 90);
		}
	}
	
	public void setStartignPos(){
		setPos(path.get(0));
	}
	
	public void hurt(float dmg){
		health -= dmg;
	}
	
	public void kill(){
		health=0;
	}
	
	public void setPath(Array<Node> path){
		this.path = path;
	}
	
	public Array<Node> getPath(){
		return path;
	}
	
	public void setDirection(Node newDir){
		if (isAngleNegative(newDir)) {
			pos.rotate(-getAngle(newDir));
		} else {
			pos.rotate(getAngle(newDir));
		}
		direction = newDir; //update direction
	}
	
	public float getRadius(){
		return radius;
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

		return (float) (Math.acos(
				(oldX * newX + newY * oldY)
				/ (direction.getLength() * newDir.getLength()))
				* (180/Math.PI)
		);
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

	public void setPos(float x, float y) {
		this.pos.setPosition(x, y);
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
