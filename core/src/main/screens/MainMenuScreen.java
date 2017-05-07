package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.illegalaliens.IllegalAliensMain;
import stages.AboutStage;
import stages.MainMenuStage;

public class MainMenuScreen implements Screen {

	private IllegalAliensMain game;
	private SpriteBatch batch;

	private MainMenuStage mainMenuStage;
	private AboutStage aboutStage;
	
	public MainMenuScreen(IllegalAliensMain game, SpriteBatch batch){
		this.game = game;
		this.batch = batch;

		mainMenuStage = new MainMenuStage(game, this);
		aboutStage = new AboutStage(game, this);
	}

	public void showAboutStage() {
		aboutStage.setVisible(true);
		mainMenuStage.setVisible(false);

		Gdx.input.setInputProcessor(aboutStage);
	}

	public void showMainMenuStage() {
		aboutStage.setVisible(false);
		mainMenuStage.setVisible(true);

		Gdx.input.setInputProcessor(mainMenuStage);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		this.showMainMenuStage();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		mainMenuStage.draw();
		aboutStage.draw();
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

	}
	

}
