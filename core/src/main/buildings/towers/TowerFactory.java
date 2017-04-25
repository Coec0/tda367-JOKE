package buildings.towers;

import com.badlogic.gdx.Gdx;

public class TowerFactory {

	public static Tower createSoldier(int x, int y) {
		return new Soldier(x, Gdx.graphics.getHeight() - y);

	}

	public static Tower createTank(int x, int y) {
		return new Tank(x, Gdx.graphics.getHeight() - y);
	}

}
