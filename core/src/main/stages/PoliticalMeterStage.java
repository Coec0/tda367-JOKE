package stages;

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
import com.badlogic.gdx.utils.ArrayMap;

public class PoliticalMeterStage extends Stage {

	private final int HEIGHT = 200;
	private final int WIDTH = 500;
	private Table table;
	private ArrayMap<Integer, String> pVotes;
	public PoliticalMeterStage() {
		pVotes = new ArrayMap<Integer, String>(false, 7);
		table = new Table();
		// table.setDebug(true);
		table.setPosition((Gdx.graphics.getWidth() - 200 - WIDTH) / 2, 0);
		table.setWidth(WIDTH);
		table.setHeight(HEIGHT);

		
		this.addActor(table);
	}
	
	public void addParty(String party, int votes){
		updatePartyVotes(party, votes);
	}
	
	public void updateParty(ArrayMap<Integer, String> pVotes){
		for(int i=0; pVotes.size > i; i++){
			updatePartyVotes(pVotes.getValueAt(i), pVotes.getKeyAt(i));
		}
		reCalcTable();
	}

	private void updatePartyVotes(String party, int votes) {
		if(pVotes.containsValue(party, false)){
			int index = pVotes.indexOfValue(party, false);
			pVotes.setKey(index, votes);
		} else {
			pVotes.put(votes, party);
			table.add(partyLabel(partyColor(party), party));
		}
	}
	
	private void reCalcTable(){
		for(Cell<?> cell : table.getCells()){
			cell.width(calcWidth(cell.getActor().getName()));
		}
		table.invalidate();
	}

	private float calcWidth(String party){
		if(pVotes.containsValue(party, false)){
			float allVotes = getAllVotes();
			float partyVotes = pVotes.getKey(party, false);
			float percent = partyVotes/allVotes;
			return WIDTH*percent;
		}
		return 0;
	}
	
	private int getAllVotes() {
		int total=0;
		for(Integer vote : pVotes.keys()){
			total += vote;
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
	
	private Label partyLabel(Color color, String party) {
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		Label label = new Label("", skin);
		Pixmap labelColor = new Pixmap(10, 10, Pixmap.Format.RGB888);
		labelColor.setColor(color);
		labelColor.fill();
		label.setName(party);
		label.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		return label;
	}

}
