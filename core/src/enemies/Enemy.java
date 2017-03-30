package enemies;

import utilities.Node;
import utilities.SpriteAdapter;

public abstract class Enemy {
	private SpriteAdapter pos;
	private float speed;
	private float health; //Maybe int in future
	private int nodeArrayPos = 0;

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
