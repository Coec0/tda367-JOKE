package buildings;

public abstract class Building extends BoardObject {

	private float radius;
	
	protected Building(String name, int x, int y, float size, float radius){
		super(name, x, y, size);
		this.radius = radius;
	}

	public float getRadius(){
		return radius;
	}
	
	public void setRadius(float radius){
		this.radius = radius;
	}
	
	public abstract void usePower();
	
}
