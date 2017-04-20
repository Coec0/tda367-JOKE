package buildings;

public class WhiteHouse extends Building{
	private int health;
	private float money;
	
	public WhiteHouse(String name, int x, int y){
		super(name, x, y);
	}
	
	public void removeHealth(int amount){
		health -=amount;
	}
	
	public void removeHealth(){
		health -=1;
	}
	
	public void setHealth(int amount){
		health = amount;
	}
	
	public void addHealth(int amount){
		health += amount;
	}
	
	public void addHealth(){
		health += 1;
	}
	
	public int getHealth(){
		return health;
	}
	
	
	
	public void addMoney(float amount){
		money += amount;
	}
	
	public void setMoney(float amount){
		money = amount;
	}
	
	public void removeMoney(float amount){
		money -= amount;
	}
	
	public float getMoney(){
		return money;
	}
}
