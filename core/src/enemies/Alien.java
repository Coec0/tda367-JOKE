package enemies;

public class Alien extends Enemy{
	private final static float SPEED=100;
	private final static float HEALTH=30;
	private final static int X = 0;
	private final static int Y = 0;
	
	public Alien(){
		super(X, Y, SPEED, HEALTH);
	}
	
	public Alien(float health){
		super(X, Y, SPEED, health);
	}
	
	public Alien(int x, int y){
		super(x, y, SPEED, HEALTH);
	}
	
	public Alien(int x, int y, float speed, float health) {
		super(x, y, speed, health);
	}

}
