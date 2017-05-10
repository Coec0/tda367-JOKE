package interfaces;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;

public interface IStage {

	void setVisible(boolean b);
	Array<Actor> getActors();
	void draw();
}
