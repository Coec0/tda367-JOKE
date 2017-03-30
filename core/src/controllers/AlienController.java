package controllers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

import utilities.Node;
import utilities.PathFinder;
import utilities.SpriteAdapter;
import views.AlienView;

public class AlienController extends InputAdapter{
	AlienView AView;
	Array<Node> nodes = new Array<Node>();
	PathFinder finder;
	Array<Node> spriteWalk;
	SpriteAdapter adapter = new SpriteAdapter(0,0);
	int counter = 0;
	
	public AlienController(){
		nodes.add(new Node(0,0)); //just to test the pathfinder class
		nodes.add(new Node(200,400));
		nodes.add(new Node(640,50));
		nodes.add(new Node(23,30));
		finder = new PathFinder(nodes);
		spriteWalk = finder.getShortestPath();
		AView = new AlienView();
		AView.addToView(adapter);
	}
	
	
	
	//JUST FOR TESTING
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		adapter.setPosition(spriteWalk.get(counter).getX(),spriteWalk.get(counter).getY());
		counter++;
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
