package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.IllegalAliensMain;

import controllers.AlienController;
import controllers.BuildingController;
import controllers.ProjectileController;
import models.AlienModel;
import models.BuildingModel;
import models.ProjectileModel;
import stages.HUDStage;
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
import views.HUDView;
import views.ProjectileView;

public class GameScreen implements Screen{
	SpriteBatch batch;
	private Sprite backgroundSprite;
	private SpriteCollector SC = SpriteCollector.getInstance();
	private PathFinder finder = PathFinder.getInstance();
	
	
	private Array<Node> nodes = new Array<Node>();
	Array<MapNode> Mapnodes;
	IllegalAliensMain IAMain;
	EnemyWavesCreator ewc;
	
	private Camera camera;
	private Viewport WP;
	HUDView HV;
	HUDStage HS;
	
	public GameScreen(IllegalAliensMain illegalAliensMain, SpriteBatch batch) {
		this.IAMain = illegalAliensMain;
		this.batch = batch;
	}

	@Override
	public void show() {
		addNodes();
		finder.calculateShortest(Mapnodes, Mapnodes.get(0),Mapnodes.peek());
		
		AlienView AW= new AlienView();
		AlienModel AM = new AlienModel();
		Radar radar = new Radar(AM);
		BuildingModel TM = new BuildingModel(radar);
		BuildingView TW = new BuildingView();
		ProjectileModel PM = new ProjectileModel();
		ProjectileView PW = new ProjectileView();
		
		//Maybe move these later
		AlienController AController = new AlienController(AW, AM);
        ProjectileController PC = new ProjectileController(PM, PW);
		
		//InputAdapter EWC = new EnemyWavesCreator(AController);
        
		camera = new OrthographicCamera();
		WP = new StretchViewport(1280, 720, camera);
		WP.apply();
		//camera.position.set(1280/2, 720/2, 0);
		InputAdapter TController = new BuildingController(TM, AM, TW, PC, WP);
		
		IAMain.addObserver(TM);
		IAMain.addObserver(AM);
		IAMain.addObserver(PM);
		InputMultiplexer imp = new InputMultiplexer();
		imp.addProcessor(AController);
		imp.addProcessor(TController);
		
		Gdx.input.setInputProcessor(imp);
		
		HV = new HUDView();
		HS = new HUDStage(AController); 
		imp.addProcessor(HS);
		HV.addToView(HS);

		
	}

	private void addNodes() {
		
		Map map = new Map("AlphaMap");
		Mapnodes = map.getMapNodes();
		System.out.println(Mapnodes.size);
		nodes.clear();
		for(MapNode tmp : Mapnodes){
			nodes.add(tmp.getPos());
			System.out.println(tmp.getPos().getX() + " " + tmp.getPos().getY());
			
		}
		backgroundSprite = new Sprite(map.getMap());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundSprite.draw(batch);
		SC.drawAll(batch);
		batch.end();
		SC.drawStages();
	}

	

	@Override
	public void resize(int width, int height) {
		WP.update(width, height, true);
		HS.getViewport().update(width, height);
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
		if(SC.getStages() != null){
			for(Stage stage : SC.getStages()){
				stage.dispose();
			}
		}
		
	}

}
