package buildings.towers;

import enemies.Enemy;
import utilities.Node;
import utilities.SpriteAdapter;

public abstract class Tower {
    private float radius;
    private String name;
    private int cost;
    private float damage;
    private Enemy target;


    private SpriteAdapter pos;


    public Tower(int x, int y, float radius, String name, int cost, float damage){
        this.pos = new SpriteAdapter(x, y);
        this.radius = radius;
        this.name = name;
        this.cost = cost;
        this.damage = damage;
        
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

    public float getRadius(){
        return this.radius;
    }

    public int getCost(){
        return this.cost;
    }

    public float getDamage(){
        return this.damage;
    }

    public void shoot(Enemy enemy){
    	
    }

    public void setTarget(Enemy target){
    	//System.out.println(target.getPos().getX() + "   "  + target.getPos().getY());
    	testFunction(target.getPos());
    	this.target = target;
    }
    
    public Enemy getTarget(){
    	return target;
    }
    
    
    //we probably need to move this fuction to a helper class. I just copy-pasted from enemy for now (test)
    
    public void testFunction (Node newDir){
    	float oldX = pos.getX();
		float oldY = pos.getY();
		float newX = newDir.getX();
		float newY = newDir.getY();
		
		
		float angle = (float) Math.toDegrees(Math.atan2(newY - oldY, newX - oldX));
		
		if(angle >= 0){
			angle += 360;
		}
		pos.setRotation(angle);
    }
    
    
    

    
	
    
   



}
