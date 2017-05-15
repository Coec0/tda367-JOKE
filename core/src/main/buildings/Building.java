package buildings;

import cooldown.CooldownObject;

public abstract class Building extends BoardObject {

	private float radius;
	private CooldownObject cooldown;
	
	


	protected Building(String name, int x, int y, float size, float radius, float cooldown, int cost){
		super(name, x, y, size, cost);
		this.radius = radius;
		this.cooldown = new CooldownObject(cooldown);
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
