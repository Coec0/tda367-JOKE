package com.example.illegalaliens.views.textures;

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
	
	private static final Texture alienNet = new Texture("aliens/alien/aliennet512.png");
	private static final Texture alienWithHelmetNet = new Texture("aliens/alienwithhelmet/alienwithhelmetnet512.png");
	private static final Texture sneakyAlienNet = new Texture("aliens/sneakyalien/sneakyaliennet512.png");
	private static final Texture toughAlienNet = new Texture("aliens/toughalien/toughaliennet512.png");
	private static final Texture highAlienNet = new Texture("aliens/highalien/highaliennet512.png");

	public static Texture getAlienNetTexture() {
		return alienNet;
	}

	public static Texture getAlienWithHelmetNetTexture() {
		return alienWithHelmetNet;
	}

	public static Texture getSneakyAlienNetTexture() {
		return sneakyAlienNet;
	}

	public static Texture getToughAlienNetTexture() {
		return toughAlienNet;
	}

	public static Texture getHighAlienNetTexture() {
		return highAlienNet;
	}
	
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
