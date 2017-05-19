package buildings;

import politics.parties.Party;
import politics.parties.Voter;
import utilities.Node;
import utilities.SpriteAdapter;

public abstract class BoardObject implements Voter {
	private String name;
	private SpriteAdapter pos;
	private float size;
	private boolean active;
	private int cost;
	private int value;

	protected BoardObject(String name, int x, int y, float size, int cost) {
		this.cost = cost;
		this.size = size;
		this.name = name;
		setValue(cost);
		this.pos = new SpriteAdapter(x, y);
		setActive(false);
	}

	public abstract String getDescription();

	public int getValue() {
		return value;
	}

	private void setValue(int value) {
		this.value = (int)((float)value*0.7f);
	}
	
	public float getRadius(){
		return 0;
	}
	
	@Override
	public Party getParty(){
		return null;
	}
	
	public int getCost(){
		return cost;
	}
	
	public void setCost(int cost){
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

	public SpriteAdapter getSpriteAdapter() {
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
