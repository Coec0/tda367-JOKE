package towers;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import buildings.Building;

/**
 * Created by Emil on 2017-05-08.
 */
public class TowerBooster extends Building {
	private static final String NAME = "TowerBooster";
	private static final String DESCRIPTION = "Boosts all towers in its radius";
	private static final int BOOSTRADIUS = 5000;
	private static final int SIZE = 50;
	private static final int COST = 600;

	private Array<Tower> towers;

	  public TowerBooster(int x, int y, float radius, float cooldown, int cost){
	    	super(NAME, x, y, SIZE, radius, cooldown, cost);
	    }
	
	public TowerBooster(int x, int y) {
		this(x, y, BOOSTRADIUS, 0, COST);

	}

	@Override
	public String getDescription() {
		return DESCRIPTION;
	}

	public void updateTowers(Array<Tower> towers) {
		this.towers = towers;
	}

	@Override
	public void usePower() {
		if (towers != null) {
			for (Tower tower : towers) {
				tower.setDamage(tower.getDamage() * 1.3f);
				tower.setRadius(tower.getRadius() * 20f);
			}
		}
	}

	@Override
	public BoardObject clone(int x, int y) {
		return new TowerBooster(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost());
	}

}
