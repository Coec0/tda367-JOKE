package utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAdapter {
	private Sprite sprite;
	private int ID;
	
	private static int uniqueID = 0;
	
	public SpriteAdapter(Sprite sprite){
		this.sprite = new Sprite(sprite);
		ID = uniqueID++;
	}
	
	public SpriteAdapter(Texture texture){
		this.sprite = new Sprite(texture);
		ID = uniqueID++;
	}
	
	public int getId(){
		return ID;
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
