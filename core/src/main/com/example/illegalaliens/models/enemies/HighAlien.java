package com.example.illegalaliens.models.enemies;

public class HighAlien extends Enemy {
	private final static float SPEED=40;
	private final static float HEALTH=30;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 20;

	
	public HighAlien(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public HighAlien(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public HighAlien(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	public HighAlien(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}
	
	
}
