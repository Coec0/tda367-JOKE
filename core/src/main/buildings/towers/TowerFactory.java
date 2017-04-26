package buildings.towers;

import com.badlogic.gdx.Gdx;
import controllers.ProjectileController;

public class TowerFactory {
	public static Tower createSoldier(int x, int y, ProjectileController PController) {
		return new Soldier(x, Gdx.graphics.getHeight() - y, PController);

	}

	public static Tower createTank(int x, int y, ProjectileController PController) {
		return new Tank(x, Gdx.graphics.getHeight() - y, PController);
	}

}
