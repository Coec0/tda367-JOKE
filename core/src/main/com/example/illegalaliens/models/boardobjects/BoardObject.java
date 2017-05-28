package com.example.illegalaliens.models.boardobjects;

import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.politics.parties.Voter;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.IAAdapter;

public abstract class BoardObject implements Voter {
	private String name;
	private IAAdapter pos;
	private float size;
	private boolean active;
	private float cost;
	private float value;

	protected BoardObject(String name, int x, int y, float size, float cost) {
		this.cost = cost;
		this.size = size;
		this.name = name;
		initValue(cost);
		this.pos = new IAAdapter(x, y);
		setActive(false);
	}

	public abstract String getDescription();

	public float getValue() {
		return value;
	}

	public void setValue(float value) {this.value = value;}

	private void initValue(float value) {
		this.value = value*0.7f;
	}
	
	public float getRadius(){
		return 0;
	}
	
	@Override
	public Party getParty(){
		return null;
	}
	
	public float getCost(){
		return cost;
	}
	
	public void setCost(float cost){
		this.cost = cost;
	}
	
	public Node getPos() {
		return new Node(pos.getX(), pos.getY());
	}

	public void setPos(Node pos) {
		this.pos.setPosition(pos.getX(), pos.getY());
	}

	public void setPos(int x, int y) {
		this.pos.setPosition(x, y);
	}

	public IAAdapter getSpriteAdapter() {
		return pos;
	}

	public String getName() {
		return this.name;
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	public abstract BoardObject clone(int x, int y);

}
