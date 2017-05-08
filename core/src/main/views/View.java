package views;

import com.badlogic.gdx.graphics.Texture;

import utilities.SpriteAdapter;

public abstract class View<T> extends SimpleView {

	protected abstract Texture selectTexture(T object);

	public void addToView(SpriteAdapter sprite, T object, float scale) {
		if (sprite.getTexture() == null) {
			sprite.setTexture(selectTexture(object));
			sprite.setSize(sprite.getWidth() * scale, sprite.getHeight() * scale);
		}
		addToView(sprite);
	}

	public void addToView(SpriteAdapter sprite, T object) {
		addToView(sprite, object, 1);
	}

	public void addToView(SpriteAdapter sprite, T object, int x, int y) {
		sprite.setPosition(x, y);
		addToView(sprite, object);
	}

}
