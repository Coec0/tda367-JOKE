package utilities;

import com.badlogic.gdx.graphics.Color;

public class TextArea {
	private int x;
	private int y;
	private int width;
	private int height;
	private String text;
	private Color color;
	private Color bgColor;
	
	public TextArea(int x, int y, int width, int height, String text, Color color, Color bgColor) {
		this(x,y,width,height,text);
		this.color = color;
		this.bgColor = bgColor;
	}
	public TextArea(int x, int y, int width, int height, String text){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		this.color = Color.BLACK;
		this.color = Color.WHITE;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}




}
