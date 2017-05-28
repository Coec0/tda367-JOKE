package com.example.illegalaliens.views.textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles all Textures for Projectiles
 * @author Johan Svennungsson
 */
public class ProjectileTextureHandler {

	private static final Texture bullet = new Texture("projectiles/bullet.png");
	private static final Texture missile = new Texture("projectiles/missile.png");
	private static final Texture net = new Texture("projectiles/blacknet.png");

	public static Texture getBulletTexture() {
		return bullet;
	}
	
	public static Texture getNetTexture() {
		return net;
	}

	public static Texture getMissileTexture() {
		return missile;
	}


}
