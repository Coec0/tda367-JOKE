package textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Handles all Textures for Towers
 * @author Johan Svennungsson
 */
public class TowerTextureHandler {

	private static final Texture soldier = new Texture("towers/soldier/soldier512.png");
	private static final Texture tank = new Texture("towers/tank/tank512.png");
	private static final Texture ranger = new Texture("towers/ranger/ranger256.png");
	private static final Texture sniper = new Texture("towers/sniper/sniper512.png");
	private static final Texture netGunner = new Texture("towers/netgunner/netgunner512.png");
	private static final Texture riotShield = new Texture("towers/riotshield/riotshield256.png");
	private static final Texture minutemen = new Texture("towers/minuteman/minuteman512.png");

	public static Texture getSoldierTexture() {
		return soldier;
	}

	public static Texture getTankTexture() {
		return tank;
	}

	public static Texture getRangerTexture() {
		return ranger;
	}

	public static Texture getSniperTexture() {
		return sniper;
	}

	public static Texture getNetGunnerTexture() {
		return netGunner;
	}

	public static Texture getRiotshieldTexture() {
		return riotShield;
	}

	public static Texture getMinutemenTexture() {
		return minutemen;
	}
}
