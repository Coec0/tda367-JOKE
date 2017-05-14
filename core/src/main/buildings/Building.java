package buildings;

import cooldown.CooldownObject;

public abstract class Building extends BoardObject {

	private float radius;
	private CooldownObject cooldown;
	int cost;
	


	protected Building(String name, int x, int y, float size, float radius, int cooldown, int cost){
		super(name, x, y, size);
		this.radius = radius;
		this.cooldown = new CooldownObject(cooldown);
		this.cost = cost;
	}
	
	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public float getRadius(){
		return radius;
	}
	
	public void setRadius(float radius){
		this.radius = radius;
	}
	
	public CooldownObject getCooldownObject(){
		return cooldown;
	}
	
	public abstract void usePower();
	
}
