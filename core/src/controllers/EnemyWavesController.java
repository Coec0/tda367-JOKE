package controllers;

import com.badlogic.gdx.InputAdapter;

import utilities.EnemyWavesCreator;

public class EnemyWavesController extends InputAdapter{
	EnemyWavesCreator ewc;
	
	public EnemyWavesController(EnemyWavesCreator ewc){
		this.ewc = ewc;
	}
	
	
	
	
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		ewc.nextWave();
		return super.touchDown(screenX, screenY, pointer, button);
	}
}
