package towers;

import buildings.BoardObject;
import buildings.Building;
import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import politics.parties.Party;
import politics.parties.PartyFactory;

/**
 * Created by Emil on 2017-05-07.
 */
public class NetGunner extends Building {
    private static final String NAME = "NetGunner";
    private static final String DESCRIPTION = "A building theat slows down aliens with his net";
    private static final int SIZE = 50;
    private static final int SLOWRADIUS = 500;
    private static final Party PARTY = PartyFactory.Republican(3); // Just for now
    private static final int COST = 400;
    private static final float COOLDOWN = 90;
    private Array<Enemy> enemies;
    private Array<Enemy> affectedEnemies;
    
    public NetGunner(int x, int y, float radius, float cooldown, float cost){
    	super(NAME, x, y, SIZE, radius, cooldown, cost, PARTY);
    	affectedEnemies = new Array<Enemy>(false, 5000);
    }
    
    public NetGunner(int x, int y){
        this(x , y, SLOWRADIUS, COOLDOWN, COST);
    }


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    public void updateEnemies(Array<Enemy> enemies){
        this.enemies = enemies;
     }

	/*private void slowAllInRadius(){
    Array<Enemy> inRadius;
    for (AlienNerfer nerfer : nerfers){
        inRadius = radar.scan(nerfer.getPos(), nerfer.getRadius(), enemies);
        nerfer.slow(inRadius);
    }
}*/ //TO FIX

    private boolean checkIfEffected(Enemy e){
        if(affectedEnemies.size != 0){
            for (Enemy aE : affectedEnemies){
                if (aE.equals(e)){
                    return true;
                }
            }
        }
        return false;
    }

	@Override
	public void usePower() {
		if (enemies.size != 0) {
            for (Enemy enemy : enemies) {
                if (!(checkIfEffected(enemy))){
                enemy.setSpeed(enemy.getSpeed() * 0.8f);
                affectedEnemies.add(enemy);
            }
            }
        }
        super.getCooldownObject().setOnCooldown(true);
	}
	
	@Override
	public BoardObject clone(int x, int y) {
		return new NetGunner(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost());
	}

}
