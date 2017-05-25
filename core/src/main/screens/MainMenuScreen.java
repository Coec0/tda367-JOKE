package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.illegalaliens.IllegalAliensMain;
import controllers.MainMenuController;
import stages.AboutStage;
import stages.MainMenuStage;
import stages.MapSelectStage;
import stages.StageSwitcher;

public class MainMenuScreen implements Screen {

	private IllegalAliensMain game;
	private SpriteBatch batch;

	private StageSwitcher stageSwitcher;
	private MainMenuController mainMenuController;

	private MainMenuStage mainMenuStage;
	private AboutStage aboutStage;
	private MapSelectStage mapSelectStage;
	
	public MainMenuScreen(IllegalAliensMain game, SpriteBatch batch){
		this.game = game;
		this.batch = batch;

		stageSwitcher = new StageSwitcher();
		mainMenuController = new MainMenuController(this);

		mainMenuStage = new MainMenuStage(game, mainMenuController);
		aboutStage = new AboutStage(mainMenuController);
		mapSelectStage = new MapSelectStage(game, mainMenuController);
	}

	@Override
	public void show() {
		stageSwitcher.showStage(mainMenuStage);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		mainMenuStage.draw();
		aboutStage.draw();
		mapSelectStage.draw();
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

	public void showMainMenuStage() {
		stageSwitcher.showStage(mainMenuStage);
	}

	public void showAboutStage() {
		stageSwitcher.showStage(aboutStage);
	}

	public void showMapSelectStage() {
		stageSwitcher.showStage(mapSelectStage);
	}
	

}
