package utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAdapter extends Sprite{
	private int ID;
	
	private static int uniqueID = 0;
	
	public SpriteAdapter(){
		super();
		super.setOriginCenter();
		ID = uniqueID++;
	}
	
	public SpriteAdapter(int x, int y){
		super();
		super.setPosition(x, y);
		super.setOriginCenter();
		ID = uniqueID++;
	}
	
	public SpriteAdapter(Texture texture){
		super(texture);
		super.setOriginCenter();
		ID = uniqueID++;
	}

	public int getId(){
		return ID;
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x-super.getOriginX(), y-super.getOriginY());
	}
	
	@Override
	public float getX() {
		return super.getX()+super.getOriginX();
	}
	
	@Override
	public float getY() {
		return super.getY()+super.getOriginY();
	}

	@Override
	public void setSize(float width, float height){
		super.translate((super.getWidth()-width)/2, (super.getHeight()-height)/2);
		super.setSize(width, height);
		super.setOriginCenter();
	}
	
	@Override
	public void setTexture(Texture texture){
		super.setSize(Math.abs(texture.getWidth()), Math.abs(texture.getHeight()));
		super.setTexture(texture);
		super.setRegion(0, 0, texture.getWidth(), texture.getHeight());
		//super.setOrigin(texture.getWidth() / 2, texture.getHeight() / 2);
		super.setOriginCenter();
		super.translate(-texture.getWidth()/2, -texture.getHeight()/2);
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
