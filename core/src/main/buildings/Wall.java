package buildings;

import utilities.Node;

public class Wall extends Building {
	
	
	public Wall(String name ,int x, int y, float size) {
		super(name, x, y, size);
	}

	@Override
	public String getDescription() {
		return "Trump's mighty Wall";
	}
	
	public void  rotateTowards(Node node){
		super.getSpriteAdapter(). rotateTowards(node,90);
	}
}
