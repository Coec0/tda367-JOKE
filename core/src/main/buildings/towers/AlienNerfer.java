package buildings.towers;

import buildings.Building;
import com.badlogic.gdx.utils.Array;
import enemies.Enemy;

/**
 * Created by Emil on 2017-05-07.
 */
public class AlienNerfer extends Building {
    private static final String NAME = "AlienNerfer";
    private static final String DESCRIPTION = "A building slowing down aliens in its radius";
    private static final int SLOWRADIUS = 50;
    private static final int SIZE = 50;
    private Array<Enemy> enemies;
    
    public AlienNerfer(int x, int y){
        super(NAME, x ,y, SIZE, SLOWRADIUS, 0);
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


	@Override
	public void usePower() {
		if (enemies != null) {
            for (Enemy enemy : enemies) {
                enemy.setSpeed(enemy.getSpeed() * 0.5f);
            }
        }
	}

}
