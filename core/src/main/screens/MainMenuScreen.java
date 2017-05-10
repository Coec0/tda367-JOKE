package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.illegalaliens.IllegalAliensMain;
import stages.StageSwitcher;

public class MainMenuScreen implements Screen {

	private IllegalAliensMain game;
	private SpriteBatch batch;

	private StageSwitcher stageSwitcher;
	
	public MainMenuScreen(IllegalAliensMain game, SpriteBatch batch){
		this.game = game;
		this.batch = batch;

		stageSwitcher = new StageSwitcher(game);
	}

	@Override
	public void show() {
		stageSwitcher.showStage(stageSwitcher.getMainMenuStage());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		stageSwitcher.drawStages();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		stageSwitcher.dispose();
	}
	

}
