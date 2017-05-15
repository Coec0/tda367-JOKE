package towers;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import towers.targetmethods.ITargetState;
import cooldown.CooldownObject;
import enemies.Enemy;
import observers.ProjectileObserver;
import projectiles.Projectile;

public abstract class Tower extends BoardObject{
	private float radius;
	private Enemy target;
	private ITargetState TState;
	private float damage;

	private Array<ProjectileObserver> observers = new Array<ProjectileObserver>();
    private CooldownObject cooldown;

	protected Tower(int x, int y, float radius, String name, int cost, float cooldown,float size, float damage){
		super(name, x, y,size, cost);
		this.cooldown = new CooldownObject(cooldown);
		this.radius = radius;
		this.TState = new towers.targetmethods.TargetLast();
		this.damage = damage;
	}
	
	public CooldownObject getCooldownObject(){
		return cooldown;
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

	public float getDamage(){
    	return this.damage;
	}
	
	public void setRadius(float radius){
    	this.radius = radius;
	}

	public void setDamage(float damage){
		this.damage = damage;
	}

	public void setCooldown(float cooldown){this.cooldown = new CooldownObject(cooldown);}

	public void shoot() {
		this.makeProjectile();
    }


    public abstract Projectile makeProjectile();

	public void setTarget(Array<Enemy> targets) {
		if(targets.size == 0){
			target = null;
		}else{
			this.target = TState.getEnemy(super.getPos(), targets);
			super.getSpriteAdapter().rotateTowards(target.getPos(),-90);
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
