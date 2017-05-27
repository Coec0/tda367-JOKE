package com.example.illegalaliens.views.stages;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.politics.Parliament;
import com.example.illegalaliens.models.politics.parties.Party;

public class PoliticalMeterStage extends Stage {

	private final int HEIGHT = 10;
	private final int WIDTH = 500;
	private Table table;
	private Array<Party> parties;
	private Array<Label> partyLabels;
	 
	public PoliticalMeterStage() {
		
		partyLabels = new Array<Label>(false, 5);
		table = new Table();

//		table.setDebug(true);
		table.setPosition((Gdx.graphics.getWidth() - 200 - WIDTH) / 2, 85);
		table.setWidth(WIDTH);
		table.setHeight(HEIGHT);
		parties = new Array<Party>(false, 5);
		
		this.addActor(table);
	}
		
	public void updatePartyMeter(Parliament parliament){
		for(Party party :parliament.getParties()){
			if(!parties.contains(party, false)){
				addParty(party);
				System.out.println("addPart");
			}
			voteParty(party);

		}
		reCalcTable();
	}

	private void addParty(Party party) {
		Table partyT = partyRow(partyColor(party.getName()), party);
		table.add(partyT);
		parties.add(party);
	}
	
	private void voteParty(Party party){
		Party temp = parties.get(parties.indexOf(party, false));
		temp.setVotes(party.getVotes());
	}
	
	private void reCalcTable(){
		for(Cell<?> cell : table.getCells()){
			Object object = cell.getActor().getUserObject();
			if(object instanceof Party){
				cell.width(calcWidth((Party)object));
			}
		}
		for(Label label : partyLabels){
			label.setText(String.valueOf(((Party)label.getParent().getUserObject()).getPoints()+ " PP"));
		}
		table.invalidate();
		
	}

	private float calcWidth(Party party){
		if(parties.contains(party, false)){
			float allVotes = getAllVotes();
			float partyVotes = party.getVotes();
			float percent = partyVotes/allVotes;
			return WIDTH*percent;
		}
		return 0;
	}
	
	private int getAllVotes() {
		int total=0;
		for(Party party : parties){
			total += party.getVotes();
		}
		return total;
	}

	private Color partyColor(String party) {
		if(party.equals("Republican"))
			return Color.RED;
		if(party.equals("Democrat"))
			return Color.BLUE;
		Random random = new Random();
		return new Color(random.nextFloat(),random.nextFloat(),random.nextFloat(), 0);
	}
	
	private Table partyRow(Color color, Party party){
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		Table table = new Table();
		table.setUserObject(party);
		Label label = new Label(String.valueOf(party.getPoints()) + "PP", skin);
		label.setName(party.getName());
		partyLabels.add(label);
		if(party.getName().equals("Democrat"))
			table.add(label).align(Align.left);
		else
			table.add(label).align(Align.right);
		table.row();
		table.add(partyLabel(color, party)).expand().fill().height(HEIGHT);
		
		return table;
	}
	
	private Label partyLabel(Color color, Party party) {
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		Label label = new Label("", skin);
		Pixmap labelColor = new Pixmap(10, 10, Pixmap.Format.RGB888);
		labelColor.setColor(color);
		labelColor.fill();
		label.setName(party.getName());
		label.setUserObject(party);
		label.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		labelColor.dispose();
		return label;
	}

}
