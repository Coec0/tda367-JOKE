package enemies;

public class Highalien extends Enemy {
	private final static float SPEED=100;
	private final static float HEALTH=30;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 10;
	
	public Highalien(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public Highalien(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public Highalien(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	public Highalien(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}
}
