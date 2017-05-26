package textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Emil on 2017-05-20.
 */

/**
 * Handles all portrait textures used for buttons
 */
public class PortraitTextureHandler {

    /**
     * Copyright information found in RAD
     */

    private static final Texture nuke = new Texture("portraits/truman.jpg");
    private static final Texture minutemen = new Texture("portraits/washington.jpg");
    private static final Texture wall = new Texture("portraits/trump.jpg");
    private static final Texture boost = new Texture("portraits/roosevelt.jpg");


    public static Texture getNukePortrait(){
        return nuke;
    }
    public static Texture getMinutemenPortrait(){
        return minutemen;
    }
    public static Texture getWallPortrait(){
        return wall;
    }
    public static Texture getBoostPortrait(){
        return boost;
    }

}
