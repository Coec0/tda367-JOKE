package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.illegalaliens.IllegalAliensMain;

public class MainMenuScreen implements Screen {

	private IllegalAliensMain game;
	BitmapFont font;
	SpriteBatch batch;
	
	public MainMenuScreen(IllegalAliensMain game, SpriteBatch batch){
		this.game = game;
		this.batch = batch;
		font = new BitmapFont();
	}
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		
		batch.begin();
		font.draw(batch, "Welcome to IllegalAliens!!! ",Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight()/2);
		font.draw(batch, "Tap anywhere to begin!", Gdx.graphics.getWidth() / 2 - 100, Gdx.graphics.getHeight()/2 - 100);
		batch.end();
		
		if (Gdx.input.isTouched()) {
			game.setScreen(new GameScreen(game, batch));
			dispose();
		}
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
		font.dispose();
		
	}
	

}
