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

	private Array<Tower> towers;

	public TowerBooster(int x, int y) {
		super(NAME, x, y, SIZE, BOOSTRADIUS);

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
				tower.setRadius(tower.getRadius() * 1.3f);
			}
		}
	}
}
