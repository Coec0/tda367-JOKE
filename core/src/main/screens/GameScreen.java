package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.IllegalAliensMain;

import controllers.AlienController;
import controllers.BuildingController;
import controllers.ProjectileController;
import controllers.SuperpowerController;
import map.Map;
import map.MapNode;
import models.AlienModel;
import models.BuildingModel;
import models.ProjectileModel;
import models.SuperpowerModel;
import path.PathFinder;
import stages.RightGameUIStage;
import stages.PoliticalMeterStage;
import stages.SelectedBuildingStage;
import utilities.DrawablesCollector;
import utilities.Node;
import utilities.SpriteAdapter;
import views.AlienView;
import views.BuildingView;
import views.GameUIView;
import views.ProjectileView;
import views.SuperpowerView;
import waves.EnemyWavesCreator;

public class GameScreen implements Screen{
	SpriteBatch batch;
	private Sprite backgroundSprite;
	private DrawablesCollector SC = DrawablesCollector.getInstance();
	private PathFinder finder;
	private final int width = 1280;
	private final int height = 720;
	
	private Array<Node> nodes = new Array<Node>();
	Array<MapNode> Mapnodes;
	IllegalAliensMain IAMain;
	EnemyWavesCreator ewc;
	
	private Camera camera;
	private Viewport WP;
	GameUIView HV;
	RightGameUIStage HS;
	
	public GameScreen(IllegalAliensMain illegalAliensMain, SpriteBatch batch) {
		this.IAMain = illegalAliensMain;
		this.batch = batch;
	}

	@Override
	public void show() {
		Map map = new Map("AlphaMap");
		addNodes(map);
		finder = new PathFinder(Mapnodes, Mapnodes.get(23));
		System.out.println("GameScreen: " + Mapnodes.get(23).getID());
		finder.getShortestPath(Mapnodes.get(0));
		AlienView AW= new AlienView();
		
		AlienModel AM = new AlienModel(finder, map.getStartingNodes());
		BuildingModel BM = new BuildingModel(AM);
		BuildingView TW = new BuildingView();
		ProjectileModel PM = new ProjectileModel(AM);
		ProjectileView PW = new ProjectileView();
		SuperpowerView SW = new SuperpowerView();
		SuperpowerModel SM = new SuperpowerModel();
		SuperpowerController SC = new SuperpowerController(SM, SW, WP, AM);
		ProjectileController PC = new ProjectileController(PM, PW, BM);
		//Maybe move these later
		AlienController AController = new AlienController(AW, AM);

		//InputAdapter EWC = new EnemyWavesCreator(AController);
        
		camera = new OrthographicCamera();
		WP = new FitViewport(width, height, camera);
		//camera.position.set(1280/2, 720/2, 0);
		BuildingController TController = new BuildingController(BM, AM, TW, WP,finder);
		
		IAMain.addObserver(BM);
		IAMain.addObserver(AM);
		IAMain.addObserver(PM);
		InputMultiplexer imp = new InputMultiplexer();
		imp.addProcessor(AController);
		imp.addProcessor(TController);
		
		Gdx.input.setInputProcessor(imp);
		
		SelectedBuildingStage SBS = new SelectedBuildingStage(TController);
		PoliticalMeterStage PMS = new PoliticalMeterStage();
		HS = new RightGameUIStage(AController, TController);
		
		HV = new GameUIView(PMS, HS, SBS);
		BM.addObserver(HV);
		imp.addProcessor(HS);
		imp.addProcessor(SBS);
		BM.getWhiteHouses().peek().addObserver(HV);

		
	}

	private void addNodes(Map map) {
		
		
		Mapnodes = map.getMapNodes();
		nodes.clear();
		for(MapNode tmp : Mapnodes){
			nodes.add(tmp.getPos());
		}
		backgroundSprite = new Sprite(map.getTexture());
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		WP.apply();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundSprite.draw(batch);
		SC.drawAll(batch);
		batch.end();
		SC.drawStages();
	}

	

	@Override
	public void resize(int width, int height) {
		WP.update(width-200*width/this.width, height, true);
		
		SC.refreshStagesVP();
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
