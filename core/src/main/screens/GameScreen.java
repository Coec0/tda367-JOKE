package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.IllegalAliensMain;

import controllers.AlienController;
import controllers.BuildingController;
import models.AlienModel;
import models.BuildingModel;
import ui.HUD;
import utilities.EnemyWavesCreator;
import utilities.Map;
import utilities.MapNode;
import utilities.Node;
import utilities.PathFinder;
import utilities.Radar;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;
import views.AlienView;
import views.BuildingView;

public class GameScreen implements Screen{
	SpriteBatch batch;
	private Sprite backgroundSprite;
	private SpriteCollector SC = SpriteCollector.getInstance();
	private PathFinder finder = PathFinder.getInstance();
	
	
	private Array<Node> nodes = new Array<Node>();
	IllegalAliensMain IAMain;
	EnemyWavesCreator ewc;
	
	
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
		Radar radar = new Radar(AM);
		BuildingModel TM = new BuildingModel(radar);
		BuildingView TW = new BuildingView();
		
		
		//Maybe move these later
		AlienController AController = new AlienController(AW, AM);
		
		//InputAdapter EWC = new EnemyWavesCreator(AController);
		InputAdapter TController = new BuildingController(TM, AM, TW);	
		
		IAMain.addObserver(TM);
		IAMain.addObserver(AM);
		
		InputMultiplexer imp = new InputMultiplexer();
		imp.addProcessor(AController);
		imp.addProcessor(TController);
		
		Gdx.input.setInputProcessor(imp);
		
		HUD hud = new HUD(imp);
		hud.addHUDListener(AController);

		
		
	}

	private void addNodes() {
		
		Map map = new Map("AlphaMap");
		Array<MapNode> Mapnodes = map.getMapNodes();
		System.out.println(Mapnodes.size);
		nodes.clear();
		for(MapNode tmp : Mapnodes){
			nodes.add(tmp.getPos());
			System.out.println(tmp.getPos().getX() + " " + tmp.getPos().getY());
			
		}
		backgroundSprite = new Sprite(map.getMap());
		
		
		
		
		/*
		//Maybe move to "Map" class (MVC?)
		nodes.add(new Node(0,360));
		nodes.add(new Node(500,360));
		nodes.add(new Node(500,200));
		nodes.add(new Node(780,200));
		nodes.add(new Node(780,360));
		nodes.add(new Node(1280,360));
		*/
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		backgroundSprite.draw(batch);
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
