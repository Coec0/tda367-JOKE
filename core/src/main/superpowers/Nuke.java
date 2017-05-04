package superpowers;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;

/**
 * Created by Emil on 2017-05-04.
 */
public class Nuke {

    public Nuke(){

    }

    public void useNuke(Array <Enemy> enemies){
        for(Enemy enemy: enemies){
            enemy.kill();
        }
    }
}
