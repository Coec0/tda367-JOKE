package textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles all Textures for Aliens
 * @author Johan Svennungsson
 */
public class AlienTextureHandler {

	private static final Texture alien = new Texture("aliens/alien/alien512.png");
	private static final Texture alienWithHelmet = new Texture("aliens/alienwithhelmet/alienwithhelmet512.png");
	private static final Texture sneakyAlien = new Texture("aliens/sneakyalien/sneakyalien512.png");
	private static final Texture toughAlien = new Texture("aliens/toughalien/toughalien512.png");
	private static final Texture highAlien = new Texture("aliens/highalien/highalien512.png");

	public static Texture getAlienTexture() {
		return alien;
	}

	public static Texture getAlienWithHelmetTexture() {
		return alienWithHelmet;
	}

	public static Texture getSneakyAlienTexture() {
		return sneakyAlien;
	}

	public static Texture getToughAlienTexture() {
		return toughAlien;
	}

	public static Texture getHighAlienTexture() {
		return highAlien;
	}
}
