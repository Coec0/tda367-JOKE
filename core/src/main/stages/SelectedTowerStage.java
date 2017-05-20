package stages;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import factories.ActorFactory;
import towers.Tower;

/**
 * The Stage that is shown when selecting a Tower.
 * @author Johan Svennungsson
 */
public class SelectedTowerStage extends SelectedBoardObjectStage {

	private ClickListener clickListener;

	private TextArea description;
	private Label nameLabel;

	private Tower tower;

	public SelectedTowerStage(ClickListener clickListener) {
		this.clickListener = clickListener;

		this.addActor(addNameLabel());
		this.addActor(addDescription());
		this.addActor(addTargetTable(clickListener));
		this.addActor(addUpgradeTable(clickListener));
		this.addActor(addRemoveButton(clickListener));
	}

	private Table addTargetTable(ClickListener clickListener) {
		Table table = new Table();

		table.setPosition(width - stageWidth, 150);
		table.setWidth(stageWidth);
		table.setHeight(500);

		table.add(ActorFactory.createLabel("Target enemy:"));
		table.row();
		table.add(ActorFactory.createTextButton("tFirst", "First", clickListener)).width(100);
		table.add(ActorFactory.createTextButton("tLast", "Last", clickListener)).width(100);
		table.row();
		table.add(ActorFactory.createTextButton("tStrong", "Strongest", clickListener)).width(100);
		table.add(ActorFactory.createTextButton("tWeak", "Weakest", clickListener)).width(100);
		table.row();
		table.add(ActorFactory.createTextButton("tClose", "Closest", clickListener)).width(100);
		table.add(ActorFactory.createTextButton("tFar", "Furthest", clickListener)).width(100);

		return table;
	}

	private Table addUpgradeTable(ClickListener clickListener) {
		Table table = new Table();

		table.setPosition(width - stageWidth, 50);
		table.setWidth(stageWidth);
		table.setHeight(450);

		table.add(ActorFactory.createLabel("Upgrade Tower:"));
		table.row();
		table.add(ActorFactory.createTextButton("uRadius", "Upgrade radius", clickListener));
		table.row();
		table.add(ActorFactory.createTextButton("uDamage", "Increase damage", clickListener));
		table.row();
		table.add(ActorFactory.createTextButton("uCooldown", "Reduce cooldown", clickListener));

		return table;
	}

	private TextArea addDescription() {
		description = ActorFactory.createTextArea("");
		description.setWidth(stageWidth);
		description.setHeight(50);
		description.setPosition(width - stageWidth, 500);

		description.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				description.setText(tower.getDescription());
			}
		});

		return description;
	}

	private Actor addNameLabel() {
		nameLabel = ActorFactory.createLabel("", width - stageWidth, 600);

		nameLabel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				nameLabel.setText(tower.getName());
			}
		});
		return nameLabel;
	}

	private Actor addRemoveButton(ClickListener clickListener) {
		return ActorFactory.createTextButton("remove", "---Garbage bin---",
				width - stageWidth, 100, clickListener);
	}

	public void setTower(Tower tower) {
		this.tower = tower;

		nameLabel.fire(new ChangeListener.ChangeEvent());
		description.fire(new ChangeListener.ChangeEvent());
	}
}
