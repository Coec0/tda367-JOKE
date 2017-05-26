package com.example.illegalaliens.views.textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles all Textures for Buildings
 * @author Johan Svennungsson
 */
public class BuildingTextureHandler {

	private static final Texture wall = new Texture("buildings/TrumpWall.png");
	private static final Texture whitehouse = new Texture("buildings/sexywhitehouse.png");

	public static Texture getWallTexture() {
		return wall;
	}

	public static Texture getWhitehouseTexture() {
		return whitehouse;
	}
}
