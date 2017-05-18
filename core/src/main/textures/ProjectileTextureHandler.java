package textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles all Textures for Projectiles
 * @author Johan Svennungsson
 */
public class ProjectileTextureHandler {

	private static final Texture bullet = new Texture("projectiles/bullet.png");
	private static final Texture missile = new Texture("projectiles/missile.png");
	private static final Texture artilleryRound = new Texture("projectiles/bullet.png");
	private static final Texture engineerBullet = new Texture("projectiles/bullet.png");
	private static final Texture bazookaMissile = new Texture("projectiles/bullet.png");

	public static Texture getBulletTexture() {
		return bullet;
	}

	public static Texture getMissileTexture() {
		return missile;
	}

	public static Texture getArtilleryRoundTexture() {
		return artilleryRound;
	}

	public static Texture getEngineerBulletTexture() {
		return engineerBullet;
	}

	public static Texture getBazookaMissileTexture() {
		return bazookaMissile;
	}
}
