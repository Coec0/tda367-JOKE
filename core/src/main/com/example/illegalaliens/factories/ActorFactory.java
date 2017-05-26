package com.example.illegalaliens.factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import static com.badlogic.gdx.utils.Align.center;

public class ActorFactory {

	private static final Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

	public static Actor createTextButton(String text) {
		return new TextButton(text, skin, "default");
	}

	public static Actor createTextButton(String text, float x, float y) {
		TextButton textButton = new TextButton(text, skin, "default");
		textButton.setPosition(x, y);
		return textButton;
	}

	public static Actor createTextButton(String text, float x, float y, int posAlignment) {
		TextButton textButton = new TextButton(text, skin, "default");
		textButton.setPosition(x, y, posAlignment);
		return textButton;
	}

	public static Actor createTextButton(String name, String text, ClickListener clickListener) {
		TextButton textButton = new TextButton(text, skin, "default");
		textButton.setName(name);
		textButton.addListener(clickListener);
		return textButton;
	}

	public static Actor createTextButton(String name, String text, float x, float y, int posAlignment, ClickListener clickListener) {
		TextButton textButton = new TextButton(text, skin, "default");
		textButton.setName(name);
		textButton.setPosition(x, y, posAlignment);
		textButton.addListener(clickListener);
		return textButton;
	}

	public static Label createLabel(String text) {
		return new Label(text, skin, "default");
	}

	public static Label createLabel(String text, float x, float y) {
		Label label = new Label(text, skin, "default");
		label.setPosition(x, y);
		return label;
	}

	public static Actor createLabel(String text, float x, float y, int posAlignment) {
		Label label = new Label(text, skin, "default");
		label.setPosition(x, y, posAlignment);
		return label;
	}

	public static TextArea createTextArea(String text) {
		return new TextArea(text, skin, "default");
	}

	public static Actor createTextArea(String text, float x, float y, float width, float height) {
		TextArea textArea = new TextArea("", skin, "default");

		textArea.setText(text);
		textArea.setWidth(width);
		textArea.setHeight(height);
		textArea.setPosition(x, y);

		return textArea;
	}

	public static Actor createTextArea(String text, float x, float y, float width, float height, int posAlignment) {
		TextArea textArea = new TextArea("", skin, "default");

		textArea.setText(text);
		textArea.setWidth(width);
		textArea.setHeight(height);
		textArea.setPosition(x, y, posAlignment);

		return textArea;
	}

	public static Actor createTextField(String text, float x, float y, float width, float height,
										boolean disabled) {
		TextField textField = new TextField("", skin, "default");

		textField.setText(text);
		textField.setWidth(width);
		textField.setHeight(height);
		textField.setDisabled(disabled);
		textField.setPosition(x, y);
		textField.setAlignment(center); //currently hardcoded

		return textField;
	}

	public static Actor createTextField(String text, float x, float y, float width, float height,
										boolean disabled, int posAlignment) {
		TextField textField = new TextField("", skin, "default");

		textField.setText(text);
		textField.setWidth(width);
		textField.setHeight(height);
		textField.setDisabled(disabled);
		textField.setPosition(x, y, posAlignment);
		textField.setAlignment(center); //currently hardcoded

		return textField;
	}

	public static Actor createImageButton(Texture texture) {
		return new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
	}

	public static Actor createImage(float x, float y, float rotateDegrees, Texture texture) {
		Image image = new Image(texture);

		image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
		image.rotateBy(rotateDegrees);
		image.setPosition(x, y);

		return image;
	}

	public static Actor createImage(float x, float y, float rotateDegrees, int posAlignment, Texture texture) {
		Image image = new Image(texture);
		
		image.setOrigin(image.getWidth() / 2, image.getHeight() / 2);
		image.rotateBy(rotateDegrees);
		image.setPosition(x, y, posAlignment);

		return image;
	}
	
	public static Actor createImage(float x, float y, float rotateDegrees, float scale, int posAlignment, Texture texture) {
		Image image = (Image) createImage(x, y, rotateDegrees, posAlignment, texture);
		image.setScale(scale);
		return image;
	}
		
}
