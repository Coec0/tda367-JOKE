package com.example.illegalaliens.views.stages;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.utilities.DrawablesCollector;

/**
 * The Stage that is shown when selecting a Tower.
 * @author Johan Svennungsson
 */
public class SelectedTowerStage extends SelectedBoardObjectStage {

	private ClickListener clickListener;

	private Table mainTable;
	private Table targetTable;
	private Table infoTable;
	private Table upgradeTable;

	private TextArea description;
	private Label nameLabel;
	private Label damageLabel;
	private Label radiusLabel;
	private Label targetLabel;

	private Actor removeButton;

	private Tower tower;

	public SelectedTowerStage(ClickListener clickListener, DrawablesCollector DC) {
		this.clickListener = clickListener;

		this.addActor(addNameLabel());
		this.addActor(addDescription());

		this.addActor(addDamageLabel());
		this.addActor(addRadiusLabel());
		this.addActor(addTargetLabel());

		this.addActor(addInfoTable());
		this.addActor(addTargetTable(clickListener));
		this.addActor(addUpgradeTable(clickListener));

		this.addActor(addRemoveButton(clickListener));

		this.addActor(addMainTable());
	}

	private Table addMainTable() {
		mainTable = new Table();

		mainTable.setPosition(Gdx.graphics.getWidth() - stageWidth, 50);
		mainTable.setWidth(stageWidth);
		mainTable.setHeight(660);
//		mainTable.setDebug(true);

		mainTable.add(nameLabel).width(stageWidth);
		mainTable.row();
		mainTable.add(description).width(stageWidth).height(50f);
		mainTable.row();
		mainTable.add(infoTable).width(stageWidth);
		mainTable.row();
		mainTable.add(targetTable).width(stageWidth);
		mainTable.row();
		mainTable.add(upgradeTable).width(stageWidth);
		mainTable.row();
		mainTable.add().expand().fill();
		mainTable.row();
		mainTable.add(removeButton);

		return mainTable;
	}

	private Table addTargetTable(ClickListener clickListener) {
		targetTable = new Table();

		targetTable.add(ActorFactory.createLabel("Target enemy:"));
		targetTable.row();
		targetTable.add(ActorFactory.createTextButton("tFirst", "First", clickListener)).width(100);
		targetTable.add(ActorFactory.createTextButton("tLast", "Last", clickListener)).width(100);
		targetTable.row();
		targetTable.add(ActorFactory.createTextButton("tStrong", "Strongest", clickListener)).width(100);
		targetTable.add(ActorFactory.createTextButton("tWeak", "Weakest", clickListener)).width(100);
		targetTable.row();
		targetTable.add(ActorFactory.createTextButton("tClose", "Closest", clickListener)).width(100);
		targetTable.add(ActorFactory.createTextButton("tFar", "Furthest", clickListener)).width(100);

		return targetTable;
	}

	private Table addUpgradeTable(ClickListener clickListener) {
		upgradeTable = new Table();

		upgradeTable.add(ActorFactory.createLabel("Upgrade Tower:"));
		upgradeTable.row();
		upgradeTable.add(ActorFactory.createTextButton("uRadius", "Upgrade radius", clickListener));
		upgradeTable.row();
		upgradeTable.add(ActorFactory.createTextButton("uDamage", "Increase damage", clickListener));
		upgradeTable.row();
		upgradeTable.add(ActorFactory.createTextButton("uCooldown", "Reduce cooldown", clickListener));

		return upgradeTable;
	}

	private Table addInfoTable() {
		infoTable = new Table();

		infoTable.add(ActorFactory.createLabel("Information: "));
		infoTable.row();
		infoTable.add(ActorFactory.createLabel("Damage:")).width(100);
		infoTable.add(damageLabel).width(100);
		infoTable.row();
		infoTable.add(ActorFactory.createLabel("Radius:")).width(100);
		infoTable.add(radiusLabel).width(100);
		infoTable.row();
		infoTable.add(ActorFactory.createLabel("Target:")).width(100);
		infoTable.add(targetLabel).width(100);

		return infoTable;
	}

	private Actor addDamageLabel() {
		damageLabel = ActorFactory.createLabel("");

		damageLabel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				damageLabel.setText(String.valueOf(tower.getDamage()));
			}
		});

		return damageLabel;
	}

	private Actor addRadiusLabel() {
		radiusLabel = ActorFactory.createLabel("");

		radiusLabel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				radiusLabel.setText(String.valueOf(tower.getRadius()));
			}
		});

		return radiusLabel;
	}

	private Actor addTargetLabel() {
		targetLabel = ActorFactory.createLabel("");

		targetLabel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				targetLabel.setText(tower.getTargetState().toString());
			}
		});

		return targetLabel;
	}

	private TextArea addDescription() {
		description = ActorFactory.createTextArea("");
		description.setDisabled(true);
		description.setWidth(stageWidth);
		description.setHeight(50);
		description.setPosition(Gdx.graphics.getWidth() - stageWidth, 500);

		description.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				description.setText(tower.getDescription());
			}
		});

		return description;
	}

	private Actor addNameLabel() {
		nameLabel = ActorFactory.createLabel("");

		nameLabel.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				nameLabel.setText(tower.getName());
			}
		});
		return nameLabel;
	}

	private Actor addRemoveButton(ClickListener clickListener) {
		removeButton = ActorFactory.createTextButton("remove", "---Garbage bin---", clickListener);
		return removeButton;
	}

	public void setTower(Tower tower) {
		this.tower = tower;

		nameLabel.fire(new ChangeListener.ChangeEvent());
		description.fire(new ChangeListener.ChangeEvent());
		damageLabel.fire(new ChangeListener.ChangeEvent());
		radiusLabel.fire(new ChangeListener.ChangeEvent());
		targetLabel.fire(new ChangeListener.ChangeEvent());
	}
}
