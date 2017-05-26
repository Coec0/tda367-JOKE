package com.example.illegalaliens.models.enemies;

public class SneakyAlien extends Enemy {
	private final static float SPEED=100;
	private final static float HEALTH=15;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 17;
	
	public SneakyAlien(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public SneakyAlien(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public SneakyAlien(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	
	public SneakyAlien(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}

}
