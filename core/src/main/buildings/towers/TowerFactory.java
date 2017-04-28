package buildings.towers;

import controllers.ProjectileController;

public class TowerFactory {
	public static Tower createSoldier(int x, int y) {
		return new Soldier(x, y);
	}

	public static Tower createTank(int x, int y) {
		return new Tank(x, y);
	}

    public static Tower createSniper(int x, int y) {
        return new Sniper(x, y);
    }
    public static Tower createRanger(int x, int y) {
        return new Ranger(x, y);
    }
    public static Tower createHowitzer(int x, int y) {
        return new Howitzer(x, y);
    }

}
