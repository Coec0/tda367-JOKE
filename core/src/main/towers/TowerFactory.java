package towers;

public class TowerFactory {
	public static Soldier createSoldier(BOPrototypes prot, int x, int y) {
		return prot.getSoldier(x, y);
	}

	public static Tank createTank(BOPrototypes prot, int x, int y) {
		return prot.getTank(x, y);
	}

	public static Sniper createSniper(BOPrototypes prot, int x, int y) {
		return prot.getSniper(x, y);
	}

	public static Ranger createRanger(BOPrototypes prot, int x, int y) {
		return prot.getRanger(x, y);
	}

	public static NetGunner createNetGunner(BOPrototypes prot, int x, int y){
		return prot.getNetGunner(x,y);
	}

	public static RiotShield createRiotShield(BOPrototypes prot, int x, int y){
		return prot.getRiotShield(x,y);
	}

	public static Minutemen createMinutemen(int x, int y){
		return new Minutemen(x,y);
	}
}
