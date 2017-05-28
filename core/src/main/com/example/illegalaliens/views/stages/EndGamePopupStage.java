package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class EndGamePopupStage extends Stage{
	private Skin skin = new Skin(Gdx.files.internal("ui/skin/plain-james-ui.json"));
	private float WIDTH = 300;
	private float HEIGHT = 250;
	private ClickListener MMCont;
	
	public EndGamePopupStage(ClickListener MMCont){
		this.MMCont = MMCont;
		Pixmap background = new Pixmap(10, 10, Pixmap.Format.Alpha);
		background.setColor(0, 0, 0, 0.7f);
		background.fill();
		Table mainTable = new Table();
		mainTable.setBackground(new Image(new Texture(background)).getDrawable());
		mainTable.setHeight(Gdx.graphics.getHeight());
		mainTable.setWidth(Gdx.graphics.getWidth());
		mainTable.add(getPopupTable()).center().width(WIDTH).height(HEIGHT);
		background.dispose();
		this.addActor(mainTable);
		mainTable.setVisible(false);
		
	}
	
	
	private Table getPopupTable(){
		Pixmap  box = new Pixmap(10, 10, Pixmap.Format.RGB888);
		box.setColor(Color.BLACK);

		Table table = new Table();
		table.setBackground(new Image(new Texture(box)).getDrawable());
		table.add(ActorFactory.createImage(0, 0, 0 , 0 , new Texture("ui/GameOverBlood.png"))).padBottom(60).padTop(15);
		table.row();
		TextButton tb = new TextButton("Go to MainMenu",skin);
		
		tb.setName("goToMainMenu");
		tb.addListener(MMCont);
		
	
		table.add(tb).fill();
		box.dispose();
		return table;
	}
}
