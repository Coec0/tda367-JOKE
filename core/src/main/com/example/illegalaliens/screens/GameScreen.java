package com.example.illegalaliens.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.controllers.AlienController;
import com.example.illegalaliens.controllers.BoardObjectController;
import com.example.illegalaliens.controllers.ExecutiveOrdersController;
import com.example.illegalaliens.controllers.MainMenuController;
import com.example.illegalaliens.controllers.ProjectileController;
import com.example.illegalaliens.controllers.SuperpowerController;
import com.example.illegalaliens.hiscore.HiscoreDB;
import com.example.illegalaliens.models.AlienModel;
import com.example.illegalaliens.models.BoardObjectModel;
import com.example.illegalaliens.models.ProjectileModel;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.models.executive_orders.ExecutiveOrdersModel;
import com.example.illegalaliens.models.superpowers.SuperpowerModel;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.IAAdapter;
import com.example.illegalaliens.utilities.cooldown.CooldownHandler;
import com.example.illegalaliens.utilities.cooldown.WavesCDHandler;
import com.example.illegalaliens.utilities.path.RoadManager;
import com.example.illegalaliens.utilities.path.map.Map;
import com.example.illegalaliens.utilities.path.map.MapNode;
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
import com.example.illegalaliens.views.stages.BottomLeftGameUIStage;

public class GameScreen implements Screen{
	private SpriteBatch batch;
	private Sprite backgroundSprite;
	private DrawablesCollector DC;
	private RoadManager manager;
	private final int width;
	private final int height;
	
	private Array<Node> nodes = new Array<Node>();
	private Array<MapNode> Mapnodes;
	private IllegalAliensMain IAMain;

	private Camera camera;
	private Viewport VP;
	private GameUIView HV;
	private RightGameUIStage HS;
	private SuperpowerStage SS;

	private Map map;
	private MainMenuController MMController;

	private HiscoreDB hiscoreDB;

	public GameScreen(IllegalAliensMain illegalAliensMain, Map map, SpriteBatch batch, MainMenuController MMController,
					  Viewport VP, Camera camera, HiscoreDB hiscoreDB) {
		this.IAMain = illegalAliensMain;
		this.map = map;
		this.batch = batch;
		this.MMController = MMController;
		this.VP = VP;
		this.camera = camera;
		this.hiscoreDB = hiscoreDB;
		this.width = Gdx.graphics.getWidth();
		this.height = Gdx.graphics.getHeight();
	}

	@Override
	public void show() {
		DC = new DrawablesCollector();
		Radar radar = new Radar();
		BOPrototypes prot = new BOPrototypes();
		addNodes(map);
		MapNode lastMapNode = Mapnodes.peek();
		manager = new RoadManager(Mapnodes, lastMapNode, map.getStartingNodes(),radar);
		
		CooldownHandler cdh = new CooldownHandler();
		WavesCDHandler wcd = new WavesCDHandler();
		WhiteHouse WH = new WhiteHouse("WhiteHouse", (int) lastMapNode.getPos().getX(), (int) lastMapNode.getPos().getY(),
				50, 100, hiscoreDB);
		
		
		AlienView AW= new AlienView(DC);
		AlienModel AM = new AlienModel(manager, map.getStartingNodes(), wcd);
		AlienController AController = new AlienController(AW, AM);
		BoardObjectView BOView = new BoardObjectView(DC);
		BoardObjectModel BOModel = new BoardObjectModel(AM.getAllEnemies(), cdh,radar, manager);
		ProjectileModel PM = new ProjectileModel(AM,radar);
		ProjectileView PW = new ProjectileView(DC);
		
		ProjectileController PC = new ProjectileController(PM, PW, BOModel);
		BoardObjectController BOController = new BoardObjectController(BOModel, BOView, VP, prot);
		
		
		IAMain.addObserver(BOModel);
		IAMain.addObserver(AM);
		IAMain.addObserver(PM);
		
		IAMain.addObserver(cdh);
		InputMultiplexer imp = new InputMultiplexer();
		imp.addProcessor(AController);
		imp.addProcessor(BOController);

		Gdx.input.setInputProcessor(imp);
		
		AM.addEnemyObserver(WH);
		BOModel.addWhiteHouse(WH);
		
		SuperpowerModel SM = new SuperpowerModel(manager,BOModel, AM, cdh);
		SuperpowerController SC = new SuperpowerController(SM, VP, AM,manager, BOModel);
		IAMain.addObserver(SM);
		
		ExecutiveOrdersModel EOM = new ExecutiveOrdersModel(BOModel,AM, wcd,prot);
		ExecutiveOrdersController EOC= new ExecutiveOrdersController(EOM);
		
		SelectedBoardObjectStage SBOS = new SelectedBoardObjectStage(imp, BOController, DC);
		PoliticalMeterStage PMS = new PoliticalMeterStage();
		BottomLeftGameUIStage TL = new BottomLeftGameUIStage();
		NextWaveStage NW = new NextWaveStage(AController);
		HS = new RightGameUIStage(BOController,EOC, prot, WH);
		SS = new SuperpowerStage(SC);
		
		
		EndGamePopupStage EGP = new EndGamePopupStage(MMController);
		HV = new GameUIView(DC,PMS, HS, TL, SBOS, NW, SS,EGP);
		
		EOM.addObserver(HV);
		AM.addWavesObserver(HV);
		SM.addObserver(HV);
		BOModel.addObserver(HV);
		prot.addObserver(HV);
		imp.addProcessor(HS);
		imp.addProcessor(SBOS);
		imp.addProcessor(NW);
		imp.addProcessor(SC);
		imp.addProcessor(SS);
		imp.addProcessor(EOC);
		imp.addProcessor(EGP);
		
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
		Gdx.gl.glClearColor(212f/255f, 212f/255f, 212f/255f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		VP.apply();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		backgroundSprite.draw(batch);
		DC.drawAll(batch);
		batch.end();
		DC.drawStages();
	}

	

	@Override
	public void resize(int width, int height) {
		VP.update(width-200*width/this.width, (height-112*height/this.height), true);
		VP.setScreenY(VP.getTopGutterHeight());
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
			for(IAAdapter sprite : DC.getSprites()){
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
