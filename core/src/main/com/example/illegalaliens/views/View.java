package com.example.illegalaliens.views;

import com.badlogic.gdx.graphics.Texture;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.IAAdapter;

public abstract class View<T> extends SimpleView {

	protected View(DrawablesCollector DC) {
		super(DC);
	}

	protected abstract Texture selectTexture(T object);

	public void addToView(IAAdapter sprite, T object, float size) {
		if (sprite.getTexture() == null) {
			sprite.setTexture(selectTexture(object));
			sprite.setSize(sprite.getWidth() * getScale(sprite,size), sprite.getHeight() * getScale(sprite,size));
		}
		addToView(sprite);
	}

	public void addToView(IAAdapter sprite, T object, int x, int y,float size) {
		sprite.setPosition(x, y);
		addToView(sprite, object,size);
	}
	
	public float getScale(IAAdapter sprite, float radius){
		float diameter = radius*2;
		if(sprite.getWidth() < sprite.getHeight()){
			return (diameter/sprite.getWidth())*1.1f;
		}else{
			return (diameter/sprite.getHeight()*1.1f);
		}
	}

}
