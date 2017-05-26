package com.example.illegalaliens.utilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class SpriteAdapter extends Sprite{

	public SpriteAdapter(){
		super();
		super.setOriginCenter();
	}
	
	public SpriteAdapter(int x, int y){
		super();
		super.setPosition(x, y);
		super.setOriginCenter();
	}
	
	public SpriteAdapter(Texture texture){
		super(texture);
		super.setOriginCenter();
	}

	/**
	 * Rotates this sprite towards input node. Offset the starting angle
	 * @param node
	 * @param offset
	 * Degrees
	 */
	public SpriteAdapter rotateTowards(Node node, float offset) {
		float angle = this.getAngleTo(node);

		angle -= offset;
		if (angle >= 0) {
			angle += 360;
		}
		this.setRotation(angle);

		return this;
	}
	
	public SpriteAdapter rotateTowards(Node node) {
		return rotateTowards(node, 0);
	}

	/**
	 * Returns the angle between SpriteAdapter and Node. Calculated with atan2.
	 * @param node
	 * @return angle in degrees
	 */
	public float getAngleTo(Node node) {
		return (float) Math.toDegrees(Math.atan2(node.getY() - this.getY(), node.getX() - this.getX()));
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
}
