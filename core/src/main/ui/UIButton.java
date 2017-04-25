package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

public class UIButton extends InputAdapter{

	private int x;
	private int y;
	
	private int width;
	private int height;
	
	private String name;
	
	
	public UIButton(int x, int y, String name) {
		this.name=name;
		this.setX(x);
		this.setY(y);
	}
	
	public UIButton(int x, int y, int width, int height, String name){
		this(x,y, name);
		this.setHeight(height);
		this.setWidth(width);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(x<screenX && y<screenY && y+height>screenY && x+width>screenX){
			System.out.println("HIT");
			notifyListeners(new ActionEvent(this,0,name)); //Fix when i know how this will be useful
		}
		return super.touchDown(screenX, screenY, pointer, button);
	}
	
	private Array<ActionListener> aListeners = new Array<ActionListener>(false, 10);
	
	public void addActionListener(ActionListener l) {
		aListeners.add(l);
	}

	public void removeActionListener(ActionListener l) {
		aListeners.removeValue(l, false);
	}

	private void notifyListeners(ActionEvent e) {
		for (ActionListener l : aListeners)
			l.actionPerformed(e);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
