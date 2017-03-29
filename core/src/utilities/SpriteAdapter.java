package utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAdapter {
	private Sprite sprite;
	private int ID;
	private int x=0;
	private int y=0;
	
	private static int uniqueID = 0;
	
	public SpriteAdapter(int x, int y){
		this.x = x;
		this.y = Gdx.graphics.getHeight() - y;
		this.sprite = new Sprite();
		ID = uniqueID++;
	}
	
	public SpriteAdapter(Texture texture){
		this.sprite = new Sprite(texture);
		ID = uniqueID++;
	}

	public int getId(){
		return ID;
	}
	
	public void setTexture(Texture texture){
		this.sprite = new Sprite(texture);
		this.sprite.setPosition(x, y);
		sprite.setTexture(texture);
	}
	
	public Sprite getSprite(){
		return sprite;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpriteAdapter other = (SpriteAdapter) obj;
		if (ID != other.ID)
			return false;
		return true;
	}
}
