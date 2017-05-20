package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import interfaces.IStage;

public abstract class AbstractStage extends Stage implements IStage {

	static final float width = Gdx.graphics.getWidth();
	static final float centerWidth = Gdx.graphics.getWidth() / 2;
	static final float centerHeight = Gdx.graphics.getHeight() / 2;
	static final int center = Align.center;

	public void setVisible(boolean b) {
		for(Actor actor : this.getActors()){
			actor.setVisible(b);
		}
	}

	public Array<Actor> getActors() {
		return super.getActors();
	}
}
