package enemies;

public class Alien extends Enemy{
	private final static float SPEED=1;
	private final static int X = 0;
	private final static int Y = 0;
	
	public Alien(){
		super(X, Y, SPEED, 30);
	}
	
	public Alien(float health){
		super(X, Y, SPEED, health);
	}
	
	public Alien(int x, int y, float speed, float health) {
		super(x, y, speed, health);
	}

}
