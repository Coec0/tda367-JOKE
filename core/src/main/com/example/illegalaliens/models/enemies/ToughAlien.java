package com.example.illegalaliens.models.enemies;

public class ToughAlien extends Enemy {
	private final static float SPEED=15;
	private final static float HEALTH=2000;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 55;

	
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

	@Override
	public Enemy clone() {
		return new ToughAlien();
	}


}
