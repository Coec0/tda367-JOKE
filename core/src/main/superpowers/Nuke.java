package superpowers;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;

/**
 * Created by Emil on 2017-05-04.
 */
public class Nuke implements Superpower {

    public Nuke(){

    }

    @Override
    public void usePower() {

    }

    public void perform(Array<Enemy> enemies){
        for (Enemy enemy : enemies){
            enemy.kill();
        }
    }
}
