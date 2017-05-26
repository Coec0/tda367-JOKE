package com.example.illegalaliens.screens;

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
import com.example.illegalaliens.controllers.AlienController;
import com.example.illegalaliens.controllers.BoardObjectController;
import com.example.illegalaliens.controllers.ExecutiveOrdersController;
import com.example.illegalaliens.controllers.ProjectileController;
import com.example.illegalaliens.controllers.SuperpowerController;
import com.example.illegalaliens.models.AlienModel;
import com.example.illegalaliens.models.BoardObjectModel;
import com.example.illegalaliens.models.ExecutiveOrdersModel;
import com.example.illegalaliens.models.ProjectileModel;
import com.example.illegalaliens.models.SuperpowerModel;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.SpriteAdapter;
import com.example.illegalaliens.utilities.cooldown.CooldownHandler;
import com.example.illegalaliens.utilities.cooldown.WavesCDHandler;
import com.example.illegalaliens.utilities.path.PathFinder;
import com.example.illegalaliens.utilities.path.map.Map;
import com.example.illegalaliens.utilities.path.map.MapNode;
import com.example.illegalaliens.utilities.waves.EnemyWavesCreator;
import com.example.illegalaliens.views.AlienView;
import com.example.illegalaliens.views.BoardObjectView;
import com.example.illegalaliens.views.GameUIView;
import com.example.illegalaliens.views.ProjectileView;
import com.example.illegalaliens.views.stages.EndGamePopupStage;
import com.example.illegalaliens.views.stages.NextWaveStage;
import com.example.illegalaliens.views.stages.PoliticalMeterStage;
import com.example.illegalaliens.views.stages.RightGameUIStage;
import com.example.illegalaliens.views.stages.SelectedBoardObjectStage;
import com.example.illegalaliens.views.stages.SuperpowerStage;
import com.example.illegalaliens.views.stages.TopLeftGameUIStage;

public class GameScreen implements Screen{
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private DrawablesCollector DC;
	private PathFinder finder;
	private final int width = 1280;
	private final int height = 720;
	
	private Array<Node> nodes = new Array<Node>();
	private Array<MapNode> Mapnodes;
	private IllegalAliensMain IAMain;
	private EnemyWavesCreator ewc;
	
	private Camera camera;
	private Viewport WP;
	private GameUIView HV;
	private RightGameUIStage HS;
	private SuperpowerStage SS;

	private Map map;

	public GameScreen(IllegalAliensMain illegalAliensMain, Map map, SpriteBatch batch) {
		this.IAMain = illegalAliensMain;
		this.map = map;
		this.batch = batch;
	}

	@Override
	public void show() {
		DC = new DrawablesCollector();
		Radar radar = new Radar();
		BOPrototypes prot = new BOPrototypes();
//		map = new Map("AlphaMap", new Texture("AlphaMap.png"));
		addNodes(map);
		MapNode lastMapNode = Mapnodes.peek();
		finder = new PathFinder(Mapnodes, lastMapNode, map.getStartingNodes(),radar);
		
		CooldownHandler cdh = new CooldownHandler();
		WavesCDHandler wcd = new WavesCDHandler();
		WhiteHouse WH = new WhiteHouse("WhiteHouse", (int) lastMapNode.getPos().getX(), (int) lastMapNode.getPos().getY(),50, 100000);
		
		
		AlienView AW= new AlienView(DC);
		AlienModel AM = new AlienModel(finder, map.getStartingNodes(), wcd);
		AlienController AController = new AlienController(AW, AM);
		BoardObjectView BOView = new BoardObjectView(DC);
		BoardObjectModel BOModel = new BoardObjectModel(AM.getAllEnemies(), cdh,radar, finder);
		ProjectileModel PM = new ProjectileModel(AM,radar);
		ProjectileView PW = new ProjectileView(DC);
		
		
		
		
		
		camera = new OrthographicCamera();
		WP = new FitViewport(width, height, camera);
		ProjectileController PC = new ProjectileController(PM, PW, BOModel);
		//Maybe move these later
		
		
		//InputAdapter EWC = new EnemyWavesCreator(AController);
        
		
		
		//camera.position.set(1280/2, 720/2, 0);
		
		
		BoardObjectController BOController = new BoardObjectController(BOModel, AM, BOView, WP,finder, prot);
		
		
		IAMain.addObserver(BOModel);
		IAMain.addObserver(AM);
		IAMain.addObserver(PM);
		
		IAMain.addObserver(cdh);
		InputMultiplexer imp = new InputMultiplexer();
		imp.addProcessor(AController);
		imp.addProcessor(BOController);

		Gdx.input.setInputProcessor(imp);
		
		AM.addObserver(WH);
		BOModel.addWhiteHouse(WH);
		
		SuperpowerModel SM = new SuperpowerModel(finder,BOModel, AM, cdh);
		SuperpowerController SC = new SuperpowerController(SM, WP, AM,finder, BOModel, prot);
		IAMain.addObserver(SM);
		
		ExecutiveOrdersModel EOM = new ExecutiveOrdersModel(BOModel,AM, wcd,prot);
		ExecutiveOrdersController EOC= new ExecutiveOrdersController(EOM);
		
		SelectedBoardObjectStage SBOS = new SelectedBoardObjectStage(imp, BOController, DC);
		PoliticalMeterStage PMS = new PoliticalMeterStage();
		TopLeftGameUIStage TL = new TopLeftGameUIStage();
		NextWaveStage NW = new NextWaveStage(AController);
		HS = new RightGameUIStage(AController, BOController,EOC, prot);
		SS = new SuperpowerStage(SC);
		
		
		EndGamePopupStage EGP = new EndGamePopupStage(IAMain);
		HV = new GameUIView(DC,PMS, HS, TL, SBOS, NW, SS,EGP);
		
		SM.addObserver(HV);
		BOModel.addObserver(HV);
		prot.addObserver(HV);
		imp.addProcessor(HS);
		imp.addProcessor(SBOS);
		imp.addProcessor(NW);
		imp.addProcessor(SC);
		imp.addProcessor(SS);
		imp.addProcessor(EOC);
		
		BOModel.getWhiteHouses().peek().addObserver(HV);
		BOModel.getWhiteHouses().peek().setHealth(20); //Fixes display issue on HV
		
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
		DC.drawAll(batch);
		batch.end();
		DC.drawStages();
	}

	

	@Override
	public void resize(int width, int height) {
		WP.update(width-200*width/this.width, height, true);
		
		DC.refreshStagesVP();
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
		if(DC.getSprites() != null){
			for(SpriteAdapter sprite : DC.getSprites()){
				sprite.getTexture().dispose();
			}
		}
		if(DC.getStages() != null){
			for(Stage stage : DC.getStages()){
				stage.dispose();
			}
		}
		
	}

}
