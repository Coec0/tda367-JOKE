package controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import models.AlienModel;
import models.SuperpowerModel;
import views.SuperpowerView;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerController extends ClickListener implements InputProcessor {

    SuperpowerModel SModel;
    AlienModel AModel;
    SuperpowerView SView;
    Viewport VP;

    public SuperpowerController(SuperpowerModel SModel, SuperpowerView SView, Viewport VP, AlienModel AModel){
        this.SModel = SModel;
        this.SView = SView;
        this.VP = VP;
        this.AModel = AModel;
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
        if(event.getListenerActor().getName().equals("nuke")){
            SModel.useNuke(AModel.getAllAliens());
        }
        if(event.getListenerActor().getName().equals("wall")){

        }
        if(event.getListenerActor().getName().equals("minutemen")){

        }
        if(event.getListenerActor().getName().equals("towerboost")){

        }
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
