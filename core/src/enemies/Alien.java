package enemies;

import java.awt.Point;

public class Alien extends Enemy{
	private final static float SPEED=5;
	private final static Point START_POS=new Point(0,0);
	
	public Alien(){
		super(START_POS, SPEED, 30);
	}
	
	public Alien(float health){
		super(START_POS, SPEED, health);
	}
	
	public Alien(Point pos, float speed, float health) {
		super(pos, speed, health);
	}

}
