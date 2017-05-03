package buildings.towers;

import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.towers.targetmethods.ITargetState;
import buildings.towers.targetmethods.TargetLast;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.ProjectileObserver;

public abstract class Tower extends Building{
	private float radius;
	private int cost;
	private Enemy target;
	private ITargetState TState;
    
    
    private Array<ProjectileObserver> observers = new Array<ProjectileObserver>();
    private int cooldown;
    private int cooldownTimer;

	protected Tower(int x, int y, float radius, String name, int cost, int cooldown){
		super(name, x, y);
		this.cooldown = cooldown;
		cooldownTimer = cooldown; 
		this.radius = radius;
		this.cost = cost;
		this.TState = new TargetLast();
		this.cooldown=cooldown;
	}

    
    
    public boolean isInCooldown(){
    	cooldownTimer--;
    	if(cooldownTimer <= 0){
    		cooldownTimer = cooldown;
    		return false;
    	}
    	return true;
    }

   

	public void setTargetState(ITargetState state) {
		this.TState = state;
	}

	public ITargetState getTargetState() {
		return TState;
	}

	public float getRadius() {
		return this.radius;
	}

	public int getCost() {
		return this.cost;
	}

	public void shoot() {
		this.makeProjectile();
    }


    public abstract Projectile makeProjectile();

	public void setTarget(Array<Enemy> targets) {
		if(targets.size == 0){
			target = null;
		}else{
			this.target = TState.getEnemy(super.getPos(), targets);
			super.getSpriteAdapter().rotateTowards(target.getPos());
		}
		
	}
	
	public boolean hasTarget(){
		return target != null;
	}
	

	public Enemy getTarget() {
		return target;
	}

    public void removeObserver(ProjectileObserver observer) {
        observers.removeValue(observer, false);
    }

    public void notifyObservers(Projectile projectile, String change){
        for (ProjectileObserver observer : observers){
            observer.actOnProjectileChange(projectile, change);
        }
    }


    public void addObserver(ProjectileObserver observer){
        observers.add(observer);
    }


}
