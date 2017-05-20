package textures;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by Emil on 2017-05-20.
 */
public class PortraitTextureHandler {

    private static final Texture nuke = new Texture("portraits/truman.jpg");
    private static final Texture minutemen = new Texture("portraits/washington.jpeg");
    private static final Texture wall = new Texture("portraits/trump.jpg");
    private static final Texture boost = new Texture("portraits/roosevelt.jpg");
    //might have to be changed due to copyright

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
