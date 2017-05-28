package com.example.illegalaliens.models.boardobjects;

import com.example.illegalaliens.utilities.Node;

public class Wall extends BoardObject implements Superpower {
	private int superPowerCost;
	
	public Wall(String name ,int x, int y, float size, int superPowerCost) {
		super(name, x, y, size, 0);
		this.superPowerCost = superPowerCost;
	}
	

	@Override
	public String getDescription() {
		return "Trump's mighty Wall";
	}
	
	public void  rotateTowards(Node node){
		super.getSpriteAdapter(). rotateTowards(node,90);
	}

	@Override
	public BoardObject clone(int x, int y) {
		return new Wall(getName(), x, y, getSize(), superPowerCost);
	}


	@Override
	public int getSuperPowerCost() {
		return superPowerCost;
	}


	@Override
	public void setSuperPowerCost(int cost) {
		this.superPowerCost = cost;
	}

}
