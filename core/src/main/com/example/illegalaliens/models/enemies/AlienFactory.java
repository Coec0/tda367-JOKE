package com.example.illegalaliens.models.enemies;

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
	
	public static Enemy createToughAlien() {
		return new ToughAlien();
	}
	
	public static Enemy createHighAlien() {
		return new HighAlien();
	}
}
