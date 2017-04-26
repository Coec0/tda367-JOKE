package buildings.towers;

import controllers.ProjectileController;

public class TowerFactory {
	public static Tower createSoldier(int x, int y, ProjectileController PController) {
		return new Soldier(x, y, PController);

	}

	public static Tower createTank(int x, int y, ProjectileController PController) {
		return new Tank(x, y, PController);
	}

}
