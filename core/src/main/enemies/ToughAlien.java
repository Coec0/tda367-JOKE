package enemies;

public class ToughAlien extends Enemy {
	private final static float SPEED=10;
	private final static float HEALTH=500;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 10;
	
	public ToughAlien(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public ToughAlien(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public ToughAlien(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	
	public ToughAlien(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}

}
