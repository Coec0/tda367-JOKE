package buildings.towers;

import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.towers.targetmethods.ITargetState;
import buildings.towers.targetmethods.TargetLast;
import controllers.ProjectileController;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.Node;

public abstract class Tower extends Building {
	private float radius;
	private int cost;
	private Enemy target;
	private ITargetState TState;
	private ProjectileController PController;
    private long cooldown;
    private double currentCooldown;
    private boolean isInCooldown;

	protected Tower(int x, int y, float radius, String name, int cost, long cooldown,ProjectileController PController){
		super(name, x, y);
		this.radius = radius;
		this.cost = cost;
		this.TState = new TargetLast();
		this.PController = PController;
		this.cooldown=cooldown;
	}

	private void setCooldown(boolean cooldown){
	    this.isInCooldown = cooldown;
    }

    private void startCooldown(){
	    this.isInCooldown = true;
	    new Cooldown().start();
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
    if (!isInCooldown){
        startCooldown();
        makeProjectile(PController);
    }
    }




	public abstract Projectile makeProjectile(ProjectileController PController);

	public void setTarget(Array<Enemy> targets) {
		this.target = TState.getEnemy(super.getPos(), targets);
		rotateTowards(target.getPos());
	}

	public Enemy getTarget() {
		return target;
	}

	// we probably need to move this fuction to a helper class. I just
	// copy-pasted from enemy for now (test)

	public void rotateTowards(Node newDir) {
		float oldX = super.getPos().getX();
		float oldY = super.getPos().getY();
		float newX = newDir.getX();
		float newY = newDir.getY();

		float angle = (float) Math.toDegrees(Math.atan2(newY - oldY, newX - oldX));

		if (angle >= 0) {
			angle += 360;
		}
		super.getSpriteAdapter().setRotation(angle);
	}


	class Cooldown extends Thread{
	    @Override
        public void run(){
	        try{
	            Thread.sleep(cooldown);
            }
            catch(InterruptedException ie){

            }
            finally{
                isInCooldown = false;
            }
        }
    }

}
