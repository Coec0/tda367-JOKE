package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.IllegalAliensMain;

import controllers.AlienController;
import controllers.EnemyWavesController;
import controllers.TowerController;
import models.AlienModel;
import models.TowerModel;
import utilities.EnemyWavesCreator;
import utilities.Node;
import utilities.PathFinder;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;
import views.AlienView;
import views.TowerView;

public class GameScreen implements Screen{
	SpriteBatch batch;
	private SpriteCollector SC = SpriteCollector.getInstance();
	private PathFinder finder = PathFinder.getInstance();
	
	private Array<Node> nodes = new Array<Node>();
	IllegalAliensMain IAMain;
	EnemyWavesCreator ewc;
	EnemyWavesController waveCont;
	
	public GameScreen(IllegalAliensMain illegalAliensMain, SpriteBatch batch) {
		this.IAMain = illegalAliensMain;
		this.batch = batch;
	}

	@Override
	public void show() {
		addNodes();
		finder.calculateShortest(nodes);
		
		AlienView AW= new AlienView();
		AlienModel AM = new AlienModel();
		TowerModel TM = new TowerModel(AM);
		TowerView TW = new TowerView();
		
		
		//Maybe move these later
		AlienController AController = new AlienController(AW, AM);
		@SuppressWarnings("unused")
		TowerController TController = new TowerController(TM, AM, TW);	
		

		ewc = new EnemyWavesCreator(AController);
		IAMain.addObserver(ewc);
		EnemyWavesController waveCont = new EnemyWavesController(ewc);
		
		//TODO
		//Gdx.input.setInputProcessor(waveCont);
		Gdx.input.setInputProcessor(TController);

		IAMain.addObserver(AM);
		
	}

	private void addNodes() {
		//Maybe move to "Map" class (MVC?)
		nodes.add(new Node(0,360));
		nodes.add(new Node(500,360));
		nodes.add(new Node(500,200));
		nodes.add(new Node(780,200));
		nodes.add(new Node(780,360));
		nodes.add(new Node(1280,360));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		drawSprites();
		batch.end();
		
	}

	private void drawSprites() {
		if(SC.getSprites() != null){
			for(SpriteAdapter sprite : SC.getSprites()){
				sprite.draw(batch);
			}
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
		batch.dispose();
		if(SC.getSprites() != null){
			for(SpriteAdapter sprite : SC.getSprites()){
				sprite.getTexture().dispose();
			}
		}
		
	}

}
