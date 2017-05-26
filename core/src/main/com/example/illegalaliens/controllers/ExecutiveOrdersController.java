package com.example.illegalaliens.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.models.executive_orders.ExecutiveOrdersModel;

public class ExecutiveOrdersController extends ClickListener implements InputProcessor {
	
	private ExecutiveOrdersModel EOM;
	public ExecutiveOrdersController(ExecutiveOrdersModel EOM) {
		this.EOM = EOM;
	}
	
	@Override
    public void clicked(InputEvent event, float x, float y){
    	if(event.getListenerActor().getName().equals("CWR")){
    		EOM.civilWarAgainstRepublicans();
    	} else if(event.getListenerActor().getName().equals("CWD")){
    		EOM.civilWarAgainstDemocrats();
    	}  else if(event.getListenerActor().getName().equals("OC")){
    		EOM.obamaCare();
    	} else if(event.getListenerActor().getName().equals("TC")){
    		EOM.taxCut();
    	} else if(event.getListenerActor().getName().equals("DW")){
    		EOM.declareWar();
    	} else if(event.getListenerActor().getName().equals("OB")){
    		EOM.openBorders();
    	}
    	
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
