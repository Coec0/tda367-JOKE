package buildings;

import utilities.Node;
import utilities.SpriteAdapter;

public abstract class Building {
	private String name;
	private SpriteAdapter pos;
	
	protected Building(String name, int x, int y){
		this.name = name;
		this.pos = new SpriteAdapter(x, y);
	}
	
    public Node getPos() {
        return new Node(pos.getX(),pos.getY());
    }
    
    public void setPos(Node pos) {
        this.pos.setPosition(pos.getX(), pos.getY());
    }
    
    public void setPos(int x, int y) {
        this.pos.setPosition(x, y);
    }

    public SpriteAdapter getSpriteAdapter(){
        return pos;
    }

    public String getName(){
        return this.name;
    }
}
