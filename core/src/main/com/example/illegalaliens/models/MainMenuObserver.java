package com.example.illegalaliens.models;

import com.badlogic.gdx.utils.IntArray;

public interface MainMenuObserver {
	void actOnMainMenuChange(String id, IntArray scores);
}
