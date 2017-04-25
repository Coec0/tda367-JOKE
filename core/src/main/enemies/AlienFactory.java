package enemies;

public class AlienFactory {
	public static Enemy createAlien() {
		return new Alien();
	}

	public static Enemy createAlienWithHelmet() {
		return new AlienWithHelmet();
	}

	public static Enemy createSneakyAlien() {
		return new SneakyAlien();
	}
}
