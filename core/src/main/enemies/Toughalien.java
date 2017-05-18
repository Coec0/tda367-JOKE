package enemies;

public class Toughalien extends Enemy {
	private final static float SPEED=10;
	private final static float HEALTH=500;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 10;
	
	public Toughalien(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public Toughalien(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public Toughalien(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	
	public Toughalien(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}

}
