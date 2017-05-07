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

    public AlienNerfer(int x, int y, float size){
        super(NAME, x ,y, size);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    public void slow(Array<Enemy> enemies){
        for (Enemy enemy : enemies){
            enemy.setSpeed(enemy.getSpeed() * 0.5f);
        }
    }

    public void findEnemies(Array<Enemy> inRadius){
        if (!(inRadius == null)){

        }
    }
}
