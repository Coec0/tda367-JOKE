package buildings;

import utilities.Node;

public class Wall extends BoardObject {
	
	private static final float SCALE = 0.3f;
	
	public Wall(String name ,int x, int y, float size) {
		super(name, x, y, size, 0);
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
		return new Wall(getName(), x, y, getSize());
	}


	@Override
	public float getScale() {
		return SCALE;
	}
}
