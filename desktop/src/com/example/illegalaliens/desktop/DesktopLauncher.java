package com.example.illegalaliens.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.example.illegalaliens.IllegalAliensMain;
import hiscore.DesktopDatabaseResolver;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 720;
		config.width = 1280;
		new LwjglApplication(new IllegalAliensMain(new DesktopDatabaseResolver()), config);
	}
}
