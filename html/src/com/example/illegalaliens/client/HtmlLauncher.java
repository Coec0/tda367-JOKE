package com.example.illegalaliens.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.example.illegalaliens.hiscore.DesktopDatabaseResolver;
import com.example.illegalaliens.screens.IllegalAliensMain;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(480, 320);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new IllegalAliensMain(new DesktopDatabaseResolver());
        }
}