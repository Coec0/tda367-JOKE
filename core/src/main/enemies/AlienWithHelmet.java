package enemies;

public class AlienWithHelmet extends Enemy{
	private final static float SPEED=25;
	private final static float HEALTH=100;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 25;

	
	public AlienWithHelmet(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public AlienWithHelmet(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public AlienWithHelmet(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	
	public AlienWithHelmet(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}
}
