package buildings.towers;

public class TowerFactory {
	public static Soldier createSoldier(int x, int y) {
		return new Soldier(x, y);
	}

	public static Tank createTank(int x, int y) {
		return new Tank(x, y);
	}

	public static Sniper createSniper(int x, int y) {
		return new Sniper(x, y);
	}

	public static Ranger createRanger(int x, int y) {
		return new Ranger(x, y);
	}

	public static Howitzer createHowitzer(int x, int y) {
		return new Howitzer(x, y);
	}

	public static Bazooka createBazooka(int x, int y) {
		return new Bazooka(x, y);
	}

	public static Marine createMarine(int x, int y) {
		return new Marine(x, y);
	}

	public static Engineer createEngineer(int x, int y) {
		return new Engineer(x, y);
	}

	public static AlienNerfer createAlienNerfer(int x, int y){return new AlienNerfer(x,y);}



}
