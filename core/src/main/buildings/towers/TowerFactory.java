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

	public static AlienNerfer createAlienNerfer(int x, int y){return new AlienNerfer(x,y);}

	public static TowerBooster createTowerBooster(int x, int y){return new TowerBooster(x,y);}


}
