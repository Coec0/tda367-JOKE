package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import buildings.Building;

public class SelectedBuildingStage extends Stage{
	private Skin skin;
	private Label description, name;
	private Building selectedBuilding;
	
	public SelectedBuildingStage(ClickListener buildingC) {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		Table table = new Table();
		table.setZIndex(9001); // Put on top
		//table.setDebug(true);
		table.setPosition(Gdx.graphics.getWidth() - 200, 0);
		table.setWidth(200);
		table.setHeight(720);
		
		description = new Label("", skin);
		name = new Label("", skin);
		name.setWidth(200);
		description.setWrap(true);

		table.add(name).expand().top();
		table.row();
		table.add(getTargetsTable(buildingC));
		table.row();
		table.add(description).width(180).bottom();
		
		this.addActor(table);
	}
	
	private TextButton addTargetButton(String name, String text, ClickListener CL) {
		int width = 200;
		int height = 100;
		int x = Gdx.graphics.getWidth() - width;

		return addTextButton(x, 0, width, height, name, text, CL);
	}
	
	private Table getTargetsTable(ClickListener buildingC){
		Table table = new Table();
		table.add(addTargetButton("tFirst", "Target first", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tLast", "Target last", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tStrong", "Target strongest", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tWeak", "Target weakest", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tClose", "Target closest", buildingC)).expand().bottom();
		table.row();
		table.add(addTargetButton("tFar", "Target furthest", buildingC)).expand().bottom();
		return table;
	}
	
	public void setBuilding(Building building){
		selectedBuilding = building;
		description.setText(building.getDescription());
		name.setText(building.getName());
	}
	
	private TextButton addTextButton(int x, int y, float width, float height, String name, String text,
			ClickListener CL) {
		TextButton textButton = new TextButton(text, skin, "default");

		textButton.setName(name);
		textButton.setTransform(false);
		textButton.setWidth(width);
		textButton.setHeight(height);
		textButton.setPosition(x, y);
		textButton.addListener(CL);
		return textButton;
	}
}
