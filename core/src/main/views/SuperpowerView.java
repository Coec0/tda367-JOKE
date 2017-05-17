package views;

import buildings.BoardObject;
import buildings.Wall;
import com.badlogic.gdx.graphics.Texture;
import observers.BuildingObserver;
import towers.Minutemen;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerView extends View implements BuildingObserver{
    private Texture wall, minutemen;

    public SuperpowerView(){
        wall = new Texture("TrumpWall.png");
        minutemen = new Texture("ranger256.png");
    }


    @Override
    protected Texture selectTexture(Object object) {
        if (object instanceof Wall)
            return wall;
        if (object instanceof Minutemen){
            return minutemen;
        }
        return null;
    }

    @Override
    public void actOnBuildingChange(BoardObject boardObject, boolean remove, boolean clickedOn) {
        if (remove)
        {
            removeFromView(boardObject.getSpriteAdapter());
            System.out.println("removing froom view");
        }
    }
}
