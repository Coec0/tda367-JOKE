package buildings.towers;

import buildings.Building;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Emil on 2017-05-08.
 */
public class TowerBooster extends Building {
    private static final String NAME = "TowerBooster";
    private static final String DESCRIPTION = "Boosts all towers in its radius";
    private static final int BOOSTRADIUS = 50;
    private static final int SIZE = 50;

    public TowerBooster(int x, int y){
        super(NAME, x ,y, SIZE);

    }
    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

    public int getBoostRadius(){
        return BOOSTRADIUS;
    }

    public void boost(Array<Tower> towers){
        for(Tower tower: towers){
            tower.setDamage(tower.getDamage() * 1.3f);
            tower.setRadius(tower.getRadius() * 1.3f);
        }
    }
}
