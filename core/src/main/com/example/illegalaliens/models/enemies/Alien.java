package com.example.illegalaliens.models.enemies;

public class Alien extends Enemy{
	private final static float SPEED=40;
	private final static float HEALTH=30;
	private final static int X = 0;
	private final static int Y = 0;
	private final static float RADIUS = 20;
	
	public Alien(){
		super(X, Y, SPEED, HEALTH,RADIUS);
	}
	
	public Alien(float health){
		super(X, Y, SPEED, health,RADIUS);
	}
	
	public Alien(int x, int y){
		super(x, y, SPEED, HEALTH,RADIUS);
	}
	
	
	public Alien(int x, int y, float speed, float health) {
		super(x, y, speed, health,RADIUS);
	}

	@Override
	public Enemy clone() {
		return new Alien();
	}


}
