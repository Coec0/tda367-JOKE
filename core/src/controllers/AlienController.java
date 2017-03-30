package controllers;

import com.badlogic.gdx.InputAdapter;

import models.AlienModel;
import views.AlienView;

public class AlienController extends InputAdapter{




	AlienView AView;
	AlienModel AModel;
	/*Array<Node> nodes = new Array<Node>();
	PathFinder finder;
	Array<Node> spriteWalk;
	SpriteAdapter adapter = new SpriteAdapter(0,0);
	int counter = 0;*/
	
	public AlienController(){
		/*nodes.add(new Node(0,0)); //just to test the pathfinder class
		nodes.add(new Node(200,400));
		nodes.add(new Node(640,50));
		nodes.add(new Node(23,30));
		finder = new PathFinder(nodes);
		spriteWalk = finder.getShortestPath();*/
		AView = new AlienView();
		AModel = new AlienModel();
		
	}
	
	
	
	//JUST FOR TESTING
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		/*adapter.setPosition(spriteWalk.get(counter).getX(),spriteWalk.get(counter).getY());
		counter++;*/
		AModel.createAlien();
		AView.addToView(AModel.peekAlien().getSpriteAdapter());
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		AModel.moveAllAliens();
		return super.mouseMoved(screenX, screenY);
	}

}
