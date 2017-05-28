package com.example.illegalaliens.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.models.AlienModel;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.views.AlienView;

public class AlienController extends ClickListener implements ActionListener, InputProcessor{

	private AlienView AView;
	private AlienModel AModel;

	public AlienController(AlienView AView, AlienModel AModel) {
		this.AView = AView;
		this.AModel = AModel;
		AModel.addEnemyObserver(AView);
	}

	 @Override
     public void clicked(InputEvent event, float x, float y){
		 if(event.getListenerActor().getName().equals("nextWave"))
			 AModel.startNextWave();
     }
	
	@Override
	public boolean keyDown (int keycode) {
		if(keycode == Keys.SPACE){
			AModel.startNextWave();
			return true;
		}
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("nextWave")){
			AModel.startNextWave();
		}
		
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
